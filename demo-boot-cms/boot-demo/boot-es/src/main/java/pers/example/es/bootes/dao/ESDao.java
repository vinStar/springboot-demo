package pers.example.es.bootes.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pers.example.es.bootes.bean.Article;

/**
 * @author lt
 * @date 2019/9/9
 */
public interface ESDao extends ElasticsearchRepository<Article,Long> {
}
