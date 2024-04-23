package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BelegungRepository {
    List<Belegung> findAllBelegungen();

    Optional<Belegung> findBelegungById(UUID belegungId);

    Belegung save(Belegung belegung);
}
