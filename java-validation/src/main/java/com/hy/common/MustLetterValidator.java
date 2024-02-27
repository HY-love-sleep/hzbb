package com.hy.common;

import com.hy.annotation.MustLetter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: MustLetter注解验证器类
 * Author: yhong
 * Date: 2024/2/27
 */
public class MustLetterValidator implements ConstraintValidator<MustLetter, String> {
    private static final Set<Character> letterSet;
    static {
        letterSet = new HashSet<>();
        letterSet.add('Q');
        letterSet.add('W');
        letterSet.add('E');
        letterSet.add('R');
        letterSet.add('D');
        letterSet.add('F');
        letterSet.add('A');
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!letterSet.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
