package com.ninlgde.advanced.annotation.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuzejian on 2017/5/18.
 * 约束注解
 */

@Target(ElementType.FIELD)//只能应用在字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints
{
	//判断是否作为主键约束
	boolean primaryKey() default false;

	//判断是否允许为null
	boolean allowNull() default false;

	//判断是否唯一åå
	boolean unique() default false;
}