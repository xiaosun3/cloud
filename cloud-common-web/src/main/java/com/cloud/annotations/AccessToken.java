package com.cloud.annotations;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessToken {

    boolean required() default true;

    boolean guestEnabled() default false;
}
