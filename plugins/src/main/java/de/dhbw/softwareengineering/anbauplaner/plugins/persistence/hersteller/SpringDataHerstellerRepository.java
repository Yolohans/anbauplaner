package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.hersteller;

import de.dhbw.softwareengineering.anbauplaner.domain.hersteller.Hersteller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataHerstellerRepository extends JpaRepository<Hersteller, UUID> {
}
