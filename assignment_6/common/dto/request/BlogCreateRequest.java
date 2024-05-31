package org.sopt.sopt.common.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record BlogCreateRequest(

    String title,
    String description,
    MultipartFile image
) {

}
