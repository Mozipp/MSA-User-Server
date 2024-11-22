package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelRetrieveService {

    public PetProfileResponse getPetProfile(Model model) {
        return ModelConverter.toPetProfileResponse(model);
    }

    public ModelProfileResponse getModelProfile(Model model) {
        return ModelConverter.toModelProfileResponse(model);
    }
}
