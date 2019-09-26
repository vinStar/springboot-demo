package pers.example.es.bootes.service.impl;

import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pers.example.es.bootes.bean.Article;
import pers.example.es.bootes.bean.ArticleBO;
import pers.example.es.bootes.dao.ESDao;
import pers.example.es.bootes.service.ESService;

import java.util.List;
import java.util.Optional;


/**
 * @author vi
 * @description
 * @since 2019-05-19 12:31
 */
@Service
public class ESServiceImpl implements ESService {


    @Autowired
    private ESDao esDao;

    @Override
    public Iterable<Article> findAll() {

        return esDao.findAll();
    }

    @Override
    public long getCount() {
        return esDao.count();
    }

    @Override
    public void add(Article article) {
        esDao.save(article);

    }

    @Override
    public void delete(long l) {
        esDao.deleteById(l);
    }

    @Override
    public void addSome(List<Article> list) {
        esDao.saveAll(list);
    }

    @Override
    public Optional<Article> findOne(Long id) {
        return esDao.findById(id);
    }

    /**
     * 单条件查询
     * match query
     */

    @Override
    public Iterable<Article> findArticleByTitle(Article article) {
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("title", article.getTitle());
        //MatchQuery matchQuery1 = QueryBuilders.

        return esDao.search(matchQuery);
    }

    /**
     * 复合查询
     * multi  field match query
     */
    @Override
    public Iterable<Article> findArticle(Article article) {

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(
                article.getTitle(), "title", "content");

        return esDao.search(multiMatchQueryBuilder);
    }

    /**
     * 复合查询
     * multi  bool query
     */
    @Override
    public Iterable<Article> findBoolArticle(ArticleBO articleBO) {

        // 复合查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 以下为查询条件, 使用 must query 进行查询组合
        MultiMatchQueryBuilder matchQuery =
                QueryBuilders.multiMatchQuery(articleBO.getTitle(), "title", "content");
        boolQuery.must(matchQuery);

        // 以下为过滤筛选条件，使用 filter 比使用 must query 性能要好
        TermQueryBuilder vipQuery = QueryBuilders.termQuery("vip", articleBO.getVip());
        boolQuery.filter(vipQuery);
        RangeQueryBuilder wordsQuery =
                QueryBuilders.rangeQuery("wordAmount").gt(articleBO.getWordsBegin()).lt(articleBO.getWordsEnd());
        boolQuery.filter(wordsQuery);

        Sort sort = Sort.by(Sort.Direction.DESC, "wordAmount");
        // 分页 同时根据 点击数 click 进行降序排列
        PageRequest pageRequest = PageRequest.of(1 - 1, 3, sort);

        return esDao.search(boolQuery, pageRequest);
    }

}

