package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.model.repository.ModelRepository;
import com.mozipp.server.global.handler.BaseException;
import com.mozipp.server.global.handler.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelRetrieveService {

    private final ModelRepository modelRepository;

    public PetProfileResponse getPetProfile(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        return ModelConverter.toPetProfileResponse(model);
    }

    public ModelProfileResponse getModelProfile(Long modelId) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MODEL));
        return ModelConverter.toModelProfileResponse(model);
    }
}
