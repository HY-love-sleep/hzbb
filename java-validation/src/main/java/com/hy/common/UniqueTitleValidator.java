package com.hy.common;

import com.hy.annotation.UniqueTitle;
import com.hy.entity.Post;
import com.hy.service.PostService;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * Description: UniqueTitle注解验证器类
 * Author: yhong
 * Date: 2024/2/27
 */
@Component
@RequiredArgsConstructor
public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {
    private final PostService postService;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Post> optionalPost = Optional.ofNullable(postService.findByTitle(title));
        return !optionalPost.isPresent();
    }
}
