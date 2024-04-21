package de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Familie;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FamilieAttributeConverter implements AttributeConverter<Familie,String> {
    @Override
    public String convertToDatabaseColumn(Familie attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Familie convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Familie.of(dbData);
    }
}
