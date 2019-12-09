package com.example.demo.inf.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lt
 * @date 2019/10/30
 */

@Data
@ApiModel("feign test int long 测试long 转 string")
public class FeignBean {

    @ApiModelProperty(name = "s1", value = "value : string 字符串测试")
    String s1;
    Long l1;
    Long l2;
    Integer int1;
    Integer int2;
    List<Integer> integerList;
    List<Integer> integerList2;
    List<Long> longList;
    List<Long> longList2;
    Date date1;
    Date date2;
}
