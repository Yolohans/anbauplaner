package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.frucht;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Frucht;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataFruchtRepository extends JpaRepository<Frucht, String> {
}
