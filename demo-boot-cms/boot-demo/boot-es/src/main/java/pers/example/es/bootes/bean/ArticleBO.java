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
@ApiModel(value = "文章入参")
public class ArticleBO implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "文章 title")
    private String title;

    @ApiModelProperty(value = "文章 内容")
    private String content;

    @ApiModelProperty(value = "是否 vip  ")
    private Boolean vip;

    @ApiModelProperty(value = "筛选文章开始字数")
    private Integer wordsBegin;

    @ApiModelProperty(value = "筛选文章结束字数")
    private Integer wordsEnd;
}
