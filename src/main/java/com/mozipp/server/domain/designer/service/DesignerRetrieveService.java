package com.mozipp.server.domain.designer.service;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesignerRetrieveService {

    private final DesignerRepository designerRepository;

    public DesignerProfileResponse getDesignerProfile(Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        DesignerProfileResponse designerProfileResponse = DesignerConverter.toDesignerProfileResponse(designer);
        return designerProfileResponse;
    }
}
