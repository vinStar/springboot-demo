package com.example.springboot.junit;

import com.example.springboot.utils.EnumOrderState;
import com.example.springboot.utils.EnumTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by vin on 2019/3/22.
 */
@RunWith(SpringRunner.class)
public class LongLengthTest {

    /**
     * valueOf get a enum element
     */
    @Test
    public void getText() {

        //Long = 19
        Long lTest = 1234567890123456789L;

        boolean isA = EnumTest.A.isA();
        System.out.println("EnumTest.A :  " + isA);

        EnumTest e = EnumTest.valueOf("A");
        System.out.println("valueOf(\"A\") : " + e.isA());

        EnumTest e2 = EnumTest.fromString("text1");
        System.out.println("fromString(\"text1\") : " + e2.isA());


    }

}
