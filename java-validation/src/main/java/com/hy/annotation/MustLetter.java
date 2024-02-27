package com.hy.annotation;

import com.hy.common.MustLetterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Description: 必须是英雄连招
 * Author: yhong
 * Date: 2024/2/27
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MustLetterValidator.class})
public @interface MustLetter {
    String message() default "连招必须是QWERDF的组合！";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
