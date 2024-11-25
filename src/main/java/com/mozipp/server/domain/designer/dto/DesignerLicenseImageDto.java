package com.mozipp.server.domain.designer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DesignerLicenseImageDto {
    private MultipartFile licenseImage;
}
