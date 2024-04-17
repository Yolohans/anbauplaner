package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Year;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.YearAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;

import java.util.List;

public class Anbauplan {
    @Id
    private long id;
    @Convert(converter = NameAttributeConverter.class)
    private Name name;
    @Basic
    private boolean productive;
    @Convert(converter = YearAttributeConverter.class)
    private Year businessYear;

    private List<Acker> ackers;

}
