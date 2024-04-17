package de.dhbw.softwareengineering.anbauplaner.domain.ackerTemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction.AAcker;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class AckerTemplate extends AAcker {
    private final LocalDateTime createdAt;

    public AckerTemplate() {
        createdAt = LocalDateTime.now();
    }
}
