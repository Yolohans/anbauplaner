package de.dhbw.softwareengineering.anbauplaner.domain.frucht;

import de.dhbw.softwareengineering.anbauplaner.domain.hersteller.Hersteller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public final class Frucht {
    private final String fruchtId;
    private final Familie familie;
    private final Art art;
    private final Sorte sorte;
    private final HerstellungsJahr herstellungsjahr;
    private final Hersteller hersteller;

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
            return "ERROR_GENERATING_ID";
        }
    }

}
