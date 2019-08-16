package com.example.demo.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lt
 * @date 2019/7/24
 */
@Data
@ApiModel(value = "商品信息")
public class GoodsBO {


    public void setId(String id) {
        this.id = Long.parseLong(id);
    }

    @ApiModelProperty("部门id")
    private Long id;


    @ApiModelProperty("部门名称-添加必传")
    private String deptName;
}
