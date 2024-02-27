package com.hy.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface UniqueTitle {
    String message() default "标题必须是唯一的";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
