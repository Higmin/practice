package com.practice.spring.customizeValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Higmin
 * @date 2019/12/25 15:29
 * 自定义注解 注解类 => 检验 mac 地址（格式：080027004C44）
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MacValidator.class)
@Documented
public @interface Mac {

	String message() default "不合法的MAC地址";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
