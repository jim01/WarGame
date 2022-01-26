package com.jameshackett.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jim on 10/27/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MysqlField {
    String label();
    String columnType() default "";
    int id() default -1;
    boolean generated() default false;
    boolean allowMissing() default false; // ignore column missing in the result set
    boolean sumValueOnDuplicateKey() default false;
}
