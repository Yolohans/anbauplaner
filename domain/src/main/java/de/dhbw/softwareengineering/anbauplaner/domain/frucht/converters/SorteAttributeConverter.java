package de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Sorte;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class SorteAttributeConverter implements AttributeConverter<Sorte,String>{

    @Override
    public String convertToDatabaseColumn(Sorte attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Sorte convertToEntityAttribute(String dbData) {
            return dbData == null ? null : Sorte.of(dbData);
    }
}

