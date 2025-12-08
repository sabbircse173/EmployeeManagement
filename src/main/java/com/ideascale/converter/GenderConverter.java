package com.ideascale.converter;

import com.ideascale.data.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Long> {
    @Override
    public Long convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.getId() : null;
    }

    @Override
    public Gender convertToEntityAttribute(Long dbData) {
        return dbData != null ? Gender.getById(dbData) : null;
    }
}
