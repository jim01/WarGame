package com.jameshackett.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jim on 10/27/16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MysqlTable {
    String name();
}
