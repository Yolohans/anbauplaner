package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Anbauhinweis {
    @Id
    @GeneratedValue
    private UUID hinweisId;
    private String kurztext;
    private String beschreibung;

    protected Anbauhinweis() {
    };

    public Anbauhinweis(String kurztext, String beschreibung) {
        this.kurztext = kurztext;
        this.beschreibung = beschreibung;
    }

    public UUID getHinweisId() {
        return hinweisId;
    }

    public String getKurztext() {
        return kurztext;
    }

    public void setKurztext(String kurztext) {
        this.kurztext = kurztext;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anbauhinweis{");
        sb.append("hinweisId=").append(hinweisId);
        sb.append(", kurztext='").append(kurztext).append('\'');
        sb.append(", beschreibung='").append(beschreibung).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
