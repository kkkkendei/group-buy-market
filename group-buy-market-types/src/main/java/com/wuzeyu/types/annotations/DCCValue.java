package com.wuzeyu.types.annotations;

import java.lang.annotation.*;

/**
 * @author wuzeyu
 * @description 动态配置中心标志注解
 * @github github.com/kkkkendei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DCCValue {

    String value() default "";

}
