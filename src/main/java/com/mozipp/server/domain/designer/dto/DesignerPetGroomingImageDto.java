package com.mozipp.server.domain.designer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DesignerPetGroomingImageDto {
    private Long designerId;
    private MultipartFile petGroomingImage;
}
