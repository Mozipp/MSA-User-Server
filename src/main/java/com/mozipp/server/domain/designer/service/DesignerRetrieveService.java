package com.mozipp.server.domain.designer.service;

import com.mozipp.server.domain.designer.converter.DesignerConverter;
import com.mozipp.server.domain.designer.dto.DesignerProfileResponse;
import com.mozipp.server.domain.designer.dto.PetGroomingImageDto;
import com.mozipp.server.domain.designer.entity.Designer;
import com.mozipp.server.domain.designer.repository.DesignerRepository;
import com.mozipp.server.domain.petgroomingimage.entity.PetGroomingImage;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PetGroomingImageDto> getPetGroomingImages(Long designerId) {
        Designer designer = designerRepository.findById(designerId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DESIGNER));
        List<PetGroomingImage> petGroomingImages = designer.getPetGroomingImages();
        List<PetGroomingImageDto> petGroomingImageDtos = DesignerConverter.toPetGroomingImageDtos(petGroomingImages);
        return petGroomingImageDtos;
    }
}
