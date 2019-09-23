package pers.example.es.bootes.service.impl;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.example.es.bootes.bean.Article;
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

    @Override
    public Iterable<Article> findArticleByTitle(Article article) {
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("title", article.getTitle());
        //MatchQuery matchQuery1 = QueryBuilders.

        return esDao.search(matchQuery);
    }


}

