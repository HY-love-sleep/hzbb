package com.hy.entity;

import com.hy.annotation.MustLetter;
import com.hy.annotation.UniqueTitle;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description:
 * Author: yhong
 * Date: 2024/2/27
 */
@Data
public class PostDTO {
    @UniqueTitle
    private String title;

    @NotNull
    @MustLetter
    private String content;
}
