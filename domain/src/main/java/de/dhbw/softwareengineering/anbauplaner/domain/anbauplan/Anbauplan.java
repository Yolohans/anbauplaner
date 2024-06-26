package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Name;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.NameAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.Year;
import de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects.converters.YearAttributeConverter;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Anbauplan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID anbauplanId;

    @Convert(converter = NameAttributeConverter.class)
    private Name name;

    @Basic
    private boolean productive;

    @Convert(converter = YearAttributeConverter.class)
    private Year businessYear;

    @OneToMany(mappedBy = "anbauplan")
    private List<Acker> ackers;

    protected Anbauplan() {}

    public Anbauplan(Name name, boolean productive, Year businessYear) {
        this.name = name;
        this.productive = productive;
        this.businessYear = businessYear;
    }

    public UUID getAnbauplanId() {
        return anbauplanId;
    }

    public Name getName() {
        return name;
    }

    public boolean isProductive() {
        return productive;
    }

    public Year getBusinessYear() {
        return businessYear;
    }

    public List<Acker> getAckers() {
        return ackers;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setProductive(boolean productive) {
        this.productive = productive;
    }

    public void setBusinessYear(Year businessYear) {
        this.businessYear = businessYear;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anbauplan{");
        sb.append("id=").append(anbauplanId);
        sb.append(", name=").append(name);
        sb.append(", productive=").append(productive);
        sb.append(", businessYear=").append(businessYear);
        sb.append(", ackers=").append(ackers);
        sb.append('}');
        return sb.toString();
    }
}
