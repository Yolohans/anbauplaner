package de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NameAttributeConverter implements AttributeConverter<Name, String> {
    @Override
    public String convertToDatabaseColumn(Name attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Name convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new Name(dbData);
    }
}
