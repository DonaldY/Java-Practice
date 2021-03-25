package com.donaldy.annotation;

import java.lang.annotation.*;

/**
 * AllowPath
 * <p>
 * 打上此注解，则不会进行登录判断；表示允许路径
 *
 * @author donald
 * @date 2021/3/25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AllowPath {
}
