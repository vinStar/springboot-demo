package pers.example.es.bootes.service;

import pers.example.es.bootes.bean.Article;
import pers.example.es.bootes.bean.ArticleBO;

import java.util.List;
import java.util.Optional;

/**
 * @author lt
 * @date 2019/9/9
 */
public interface ESService {


    Iterable<Article> findAll();

    long getCount();

    void add(Article article);

    void delete(long l);

    void addSome(List<Article> list);

    Optional<Article> findOne(Long id);

    Iterable<Article> findArticleByTitle(Article article);

    Iterable<Article> findArticle(Article article);

    Iterable<Article> findBoolArticle(ArticleBO articleBO);
}
