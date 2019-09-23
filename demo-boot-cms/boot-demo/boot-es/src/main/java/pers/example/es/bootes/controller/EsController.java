package pers.example.es.bootes.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.example.es.bootes.bean.Article;
import pers.example.es.bootes.service.ESService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
            article.setId((long) i);
            article.setTitle("测试" + i);
            article.setContent("测试1文字啊啊" + i);
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

    @PostMapping("queryAllByArticleTitle")
    @ApiOperation(value = "es queryAllByArticleTitle")
    public Iterable<Article> queryAllByArticleTitle(
            @ApiParam(name = "按文章title查询", value = "出入json", required = true)
            @RequestBody Article article) {
        Iterable<Article> all = esService.findArticleByTitle(article);
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
        esService.add(article);
    }

    @GetMapping("put")
    @ApiOperation(value = "es put 修改测试数据")
    public void put() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("测试1");
        article.setContent("测试1修改啊啊");
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
