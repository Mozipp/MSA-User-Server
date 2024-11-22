package com.mozipp.server.domain.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ModelPetImageDto {
    private Long modelId;
    private MultipartFile petImage;
}
