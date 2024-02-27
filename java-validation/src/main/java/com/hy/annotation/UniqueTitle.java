package com.hy.annotation;

import com.hy.common.UniqueTitleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueTitleValidator.class})
public @interface UniqueTitle {
    String message() default "标题必须是唯一的";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
