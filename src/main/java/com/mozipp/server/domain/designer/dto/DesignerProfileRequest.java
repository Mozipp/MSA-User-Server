package com.mozipp.server.domain.designer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerProfileRequest{
    private Long designerId;
    private String petShopName;
    private String address;
    private String addressDetail;
    private String career;
}
