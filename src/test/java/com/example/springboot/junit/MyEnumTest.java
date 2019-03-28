package com.example.springboot.junit;

import lombok.experimental.var;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springboot.utils.*;

/**
 * Created by vin on 2019/3/22.
 */
@RunWith(SpringRunner.class)
public class MyEnumTest {

    /**
     * valueOf get a enum element
     */
    @Test
    public void getText() {

        for (EnumTest e : EnumTest.values()) {

            System.out.println(e.toString());
            System.out.println(e.getText());
            System.out.println(e.ordinal());

        }

        boolean isA = EnumTest.A.isA();
        System.out.println("EnumTest.A :  " + isA);

        EnumTest e = EnumTest.valueOf("A");
        System.out.println("valueOf(\"A\") : " + e.isA());

        EnumTest e2 = EnumTest.fromString("text1");
        System.out.println("fromString(\"text1\") : " + e2.isA());


    }

    /**
     * extend enum description & value
     */
    @Test
    public void getDesOrValue() {

        System.out.println("===single enum begin ===");
        System.out.println(EnumOrderState.CANCEL.toString());
        System.out.println(EnumOrderState.CANCEL.des());
        System.out.println(EnumOrderState.CANCEL.value());
        System.out.println("=== single enum end === ");

        System.out.println("=== enum foreach begin ===");
        for (EnumOrderState e : EnumOrderState.values()) {

            System.out.print(StringUtils.rightPad(e.toString(), 10, ""));
            System.out.print("," + e.des());
            System.out.print("," + e.value() + "\n");

        }

        String strDes = EnumOrderState.valueOf("UNPAY").des();
        System.out.println("valueOf(\"UNPAY\").des() : " + strDes);

    }
}
