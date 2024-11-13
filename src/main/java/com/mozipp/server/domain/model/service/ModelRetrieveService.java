package com.mozipp.server.domain.model.service;

import com.mozipp.server.domain.model.converter.ModelConverter;
import com.mozipp.server.domain.model.dto.ModelProfileResponse;
import com.mozipp.server.domain.model.dto.PetProfileResponse;
import com.mozipp.server.domain.model.entity.Model;
import com.mozipp.server.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelRetrieveService {

    public PetProfileResponse getPetProfile(User user) {
        Model model = (Model) user;
        return ModelConverter.toPetProfileResponse(model);
    }

    public ModelProfileResponse getModelProfile(User user) {
        return ModelConverter.toModelProfileResponse(user);
    }
}
