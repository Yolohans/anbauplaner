package de.dhbw.softwareengineering.anbauplaner.domain.acker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acker")
public class Tunnel {
    @Id
    private String id;
    @Column
    private String name;


    public Tunnel(String id, String name) {

        this.id = id;
        this.name = name;

    }

    protected Tunnel() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
