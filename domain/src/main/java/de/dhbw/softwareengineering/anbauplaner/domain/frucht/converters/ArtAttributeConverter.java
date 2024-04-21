package de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Art;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ArtAttributeConverter implements AttributeConverter<Art,String> {

    @Override
    public String convertToDatabaseColumn(Art attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Art convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Art.of(dbData);
    }
}
