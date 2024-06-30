package org.sopt.sopt.blog.presentation.dto.request;


import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(

    @Size(max  = 5, message = "메세지 길이가 5를 초과했습니다.")
    String title
) {
}
