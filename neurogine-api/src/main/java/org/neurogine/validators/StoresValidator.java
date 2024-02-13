package org.neurogine.validators;

import lombok.RequiredArgsConstructor;
import org.neurogine.dto.StoresDTO;
import org.neurogine.service.IStoresService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class StoresValidator implements Validator {

    private final IStoresService storesService;

    @Override
    public boolean supports(Class<?> clazz) {
        return StoresDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StoresDTO dto = (StoresDTO) target;
    }
}
