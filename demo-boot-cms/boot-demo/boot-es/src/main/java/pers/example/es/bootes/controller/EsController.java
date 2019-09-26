package pers.example.es.bootes.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.example.es.bootes.bean.Article;
import pers.example.es.bootes.bean.ArticleBO;
import pers.example.es.bootes.service.ESService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("es")
@Slf4j
@Api(tags = "es")
/**
 * @author vi
 * @description
 * @since 2019-05-19 12:26
 */
public class EsController {


    @Autowired
    private ESService esService;

    @GetMapping("addSome")
    @ApiOperation(value = "es 添加 10 条测试数据")
    public void addSome() {
        List<Article> list = new ArrayList<>();
        long max = getCount();

        for (int i = 0; i < max + 10; i++) {
            Article article = new Article();
            article.setId(max + i);
            article.setTitle("测试" + i);
            article.setContent("测试1文字啊啊" + i);
            if (i % 2 == 1) {
                article.setVip(true);
            } else {
                article.setVip(false);
            }
            article.setWordAmount(i * 10);

            list.add(article);
        }
        esService.addSome(list);
    }

    @GetMapping("getCount")
    @ApiOperation(value = "es getCount")
    public long getCount() {
        return esService.getCount();
    }


    @GetMapping("get")
    @ApiOperation(value = "es getAll数据")
    public Iterable<Article> getAll() {
        Iterable<Article> all = esService.findAll();
        all.forEach(one -> {
            log.info(one.toString());
        });
        return all;
    }

    @PostMapping("matchQueryAllByArticleTitle")
    @ApiOperation(value = "es queryAllByArticleTitle  / match query ")
    public Iterable<Article> matchQueryAllByArticleTitle(
            @ApiParam(name = "按文章title查询 match query ", value = "出入json", required = true)
            @RequestBody Article article) {
        Iterable<Article> all = esService.findArticleByTitle(article);
        all.forEach(one -> {
            log.info(one.toString());
        });
        return all;
    }

    @PostMapping("matchQueryArticle")
    @ApiOperation(value = "es queryArticle / multi match query", httpMethod = "POST")
    public Iterable<Article> matchQueryArticle(
            @ApiParam(name = "按文章title查询 multi match query", value = "出入json", required = true)
            @RequestBody Article article) {
        Iterable<Article> all = esService.findArticle(article);
        all.forEach(one -> {
            log.info(one.toString());
        });
        return all;
    }

    @PostMapping("boolQueryArticle")
    @ApiOperation(value = "es queryArticle / multi bool query")
    public Iterable<Article> boolQueryArticle(
            @ApiParam(name = "按文章title查询 multi bool query", value = "出入json", required = true)
            @RequestBody ArticleBO articleBO) {
        Iterable<Article> all = esService.findBoolArticle(articleBO);
        all.forEach(one -> {
            log.info(one.toString());
        });
        return all;
    }


    @GetMapping("add")
    @ApiOperation(value = "es add")
    public void add() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("测试1");
        article.setContent("测试1文字啊啊");
        article.setVip(true);
        article.setWordAmount(100);
        esService.add(article);
    }

    @PostMapping("addArticle")
    @ApiOperation(value = "es add article", httpMethod = "POST")
    public void addArticle(
            @ApiParam(name = "添加文章", value = "自定义添加文章 json 对象")
            @RequestBody Article article
    ) {
        Random random = new Random();
        article.setId(random.nextLong());
        esService.add(article);
    }

    @GetMapping("put")
    @ApiOperation(value = "es put 修改测试数据")
    public void put() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("测试1");
        article.setContent("测试1修改啊啊");

        article.setVip(true);
        article.setWordAmount(1010);
        esService.add(article);
    }

    @GetMapping("delete")
    @ApiOperation(value = "es delete  测试数据")
    public void delete() {
        esService.delete(1L);
    }


    @GetMapping("getOne/{id}")
    @ApiOperation(value = "es getOne/{id} 测试数据")
    public Optional<Article> getOne(@PathVariable("id") Long id) {
        return esService.findOne(id);
    }

}
