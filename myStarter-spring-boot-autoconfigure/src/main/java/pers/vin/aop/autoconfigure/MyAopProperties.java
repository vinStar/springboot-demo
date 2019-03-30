package pers.vin.aop.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by vin on 2019/3/29.
 */

@ConfigurationProperties(prefix = MyAopProperties.PREFIX)
public class MyAopProperties {

    public static final String PREFIX = "my.aop";
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
