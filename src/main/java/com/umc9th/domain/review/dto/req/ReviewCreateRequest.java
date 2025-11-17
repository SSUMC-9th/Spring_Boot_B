package com.example.umc9th.domain.review.dto.req;

import com.example.umc9th.global.annotation.ExistStore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreateRequest {

    @NotBlank(message = "리뷰 내용을 작성해주세요.")
    private String content;

    @Min(1)
    @Max(5)
    private int star;

    private String photoUrl;

}
