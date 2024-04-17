package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "belegung")
public class Belegung {
    @Id
    private String id;


    public Belegung(String id, String name) {
        this.id = id;
    }

    protected Belegung() {
    }

    public String getId() {
        return id;
    }

}
