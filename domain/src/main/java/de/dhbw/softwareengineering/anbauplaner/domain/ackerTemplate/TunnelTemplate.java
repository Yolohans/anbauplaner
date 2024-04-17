package de.dhbw.softwareengineering.anbauplaner.domain.ackerTemplate;

import de.dhbw.softwareengineering.anbauplaner.domain.ackerAbstraction.ATunnel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
public class TunnelTemplate extends ATunnel {

    private final LocalDateTime createdAt;

    public TunnelTemplate() {
        createdAt = LocalDateTime.now();
    }
}
