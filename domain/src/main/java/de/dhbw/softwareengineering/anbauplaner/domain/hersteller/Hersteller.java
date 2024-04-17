package de.dhbw.softwareengineering.anbauplaner.domain.hersteller;

import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericValueObjects.NameAttributeConverter;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Hersteller {
    @Id
    @GeneratedValue()
    private UUID herstellerId;
    @Convert(converter = NameAttributeConverter.class)
    private Name name;

    protected Hersteller() {
    }

    public Hersteller(Name name) {
        this.name = name;
    }

    public UUID getHerstellerId() {
        return herstellerId;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
