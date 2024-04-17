package de.dhbw.softwareengineering.anbauplaner.domain.ackerTemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction.ABeet;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class BeetTemplate extends ABeet {
    private final LocalDateTime createdAt;

    public BeetTemplate() {
        createdAt = LocalDateTime.now();
    }
}
