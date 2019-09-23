package pers.example.es.bootes.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author lt
 * @date 2019/9/9
 */
@Data
@Document(indexName = "test", type = "article")
@ApiModel(value = "文章")
public class Article implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "文章 title")
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @ApiModelProperty(value = "文章 内容")
    private String content;
}
