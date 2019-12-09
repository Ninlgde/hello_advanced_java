package com.ninlgde.advanced.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuzejian on 2017/5/18.
 * 注解String类型的字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString
{

	//对应数据库表的列名
	String name() default "";

	//列类型分配的长度，如varchar(30)的30
	int value() default 0;

	Constraints constraint() default @Constraints;
}