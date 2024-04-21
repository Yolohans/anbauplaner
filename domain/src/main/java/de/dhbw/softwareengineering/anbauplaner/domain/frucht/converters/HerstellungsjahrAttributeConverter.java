package de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.HerstellungsJahr;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HerstellungsjahrAttributeConverter implements AttributeConverter<HerstellungsJahr,Integer> {
    @Override
    public Integer convertToDatabaseColumn(HerstellungsJahr attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public HerstellungsJahr convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : HerstellungsJahr.of(dbData);
    }
}