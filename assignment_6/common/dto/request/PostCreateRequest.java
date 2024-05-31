package org.sopt.sopt.common.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PostCreateRequest(
    @NotEmpty
    @Size(min = 2, max = 400)
    String title,

    @NotEmpty
    @Size(min=1,max=2000)
    String content
) {

}
