package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Anbauhinweis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAnbauhinweisRepository extends JpaRepository<Anbauhinweis, UUID> {
}