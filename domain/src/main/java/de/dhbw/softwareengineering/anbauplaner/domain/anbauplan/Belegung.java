package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Frucht;
import jakarta.persistence.*;
import org.apache.commons.lang3.Validate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "belegung")
public class Belegung {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate beginn;
    private LocalDate ende;
    @OneToOne
    private Frucht frucht;
    @OneToMany
    private List<Anbauhinweis> anbauhinweise;

    protected Belegung() {
        //for jakarta.persistence
    }
    public Belegung(LocalDate beginn, LocalDate ende, Frucht frucht, List<Anbauhinweis> anbauhinweise) {
        Validate.isTrue(beginn.isBefore(ende), "'Beginn' must be before 'ende'");
        this.beginn = beginn;
        this.ende = ende;
        this.frucht = frucht;
        this.anbauhinweise = anbauhinweise;
    }

    protected UUID getId() {
        return id;
    }

    protected LocalDate getBeginn() {
        return beginn;
    }

    protected LocalDate getEnde() {
        return ende;
    }

    protected Frucht getFrucht() {
        return frucht;
    }

    protected List<Anbauhinweis> getAnbauhinweise() {
        return anbauhinweise;
    }

    protected void setBeginn(LocalDate beginn) {
        this.beginn = beginn;
    }

    protected void setEnde(LocalDate ende) {
        this.ende = ende;
    }

    protected void setFrucht(Frucht frucht) {
        this.frucht = frucht;
    }

    protected void setAnbauhinweise(List<Anbauhinweis> anbauhinweise) {
        this.anbauhinweise = anbauhinweise;
    }
}
