package de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Year;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class YearAttributeConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new Year(dbData);
    }
}
