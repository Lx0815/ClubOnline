package com.sgqn.clubonline.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiParameterBody {

    /**
     * {@link #name()} 的别名
     */
    String value() default "";

    /**
     * 参数的别名
     */
    String name() default "";

    /**
     * 参数是否为必须的
     */
    boolean required() default true;

    /**
     * 当 value 的值或者参数名不匹配时，是否允许解析最外层属性得到该对象
     */
    boolean parseAllFields() default true;

}