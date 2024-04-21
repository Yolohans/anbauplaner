package de.dhbw.softwareengineering.anbauplaner.domain.frucht;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters.ArtAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters.FamilieAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters.HerstellungsjahrAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.frucht.converters.SorteAttributeConverter;
import de.dhbw.softwareengineering.anbauplaner.domain.hersteller.Hersteller;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Entity
public final class Frucht {
    @Id
    private String fruchtId;
    @Convert(converter = FamilieAttributeConverter.class)
    private Familie familie;
    @Convert(converter = ArtAttributeConverter.class)
    private Art art;
    @Convert(converter = SorteAttributeConverter.class)
    private Sorte sorte;
    @Convert(converter = HerstellungsjahrAttributeConverter.class)
    private HerstellungsJahr herstellungsjahr;
    @OneToOne
    private Hersteller hersteller;

    protected Frucht(){};

    public Frucht(Familie familie, Art art, Sorte sorte, HerstellungsJahr herstellungsjahr, Hersteller hersteller) {
        if (familie == null && art == null && sorte == null) {
            throw new IllegalArgumentException("At least one of Familie, Art, or Sorte must be of a non-null value.");
        }
        this.familie = familie;
        this.art = art;
        this.sorte = sorte;
        this.herstellungsjahr = herstellungsjahr;
        this.hersteller = hersteller;
        this.fruchtId = generateFruchtId();
    }

    public String getFruchtId() {
        return fruchtId;
    }

    public Familie getFamilie() {
        return familie;
    }

    public Art getArt() {
        return art;
    }

    public Sorte getSorte() {
        return sorte;
    }

    public HerstellungsJahr getHerstellungsjahr() {
        return herstellungsjahr;
    }

    public Hersteller getHersteller() {
        return hersteller;
    }

    public void setFruchtId(String fruchtId) {
        this.fruchtId = fruchtId;
    }

    public void setFamilie(Familie familie) {
        this.familie = familie;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public void setSorte(Sorte sorte) {
        this.sorte = sorte;
    }

    public void setHerstellungsjahr(HerstellungsJahr herstellungsjahr) {
        this.herstellungsjahr = herstellungsjahr;
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Frucht{");
        sb.append("fruchtId='").append(fruchtId).append('\'');
        sb.append(", familie=").append(familie);
        sb.append(", art=").append(art);
        sb.append(", sorte=").append(sorte);
        sb.append(", herstellungsjahr=").append(herstellungsjahr);
        sb.append(", hersteller=").append(hersteller);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frucht)) return false;
        Frucht frucht = (Frucht) o;
        return getFruchtId().equals(frucht.getFruchtId());
} 

    @Override
    public int hashCode() {
        return Objects.hash(getFruchtId());
    }

    //TODO Catch NoSuchAlgo
    private String generateFruchtId() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = digest.digest(
                    (familie.getValue() + art.getValue() + sorte.getValue() + herstellungsjahr.getValue() +
                            hersteller.getHerstellerId()).getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // implement logging here - such that this case can be detected and fixed
            return "ERROR_GENERATING_ID";
        }
    }
}
