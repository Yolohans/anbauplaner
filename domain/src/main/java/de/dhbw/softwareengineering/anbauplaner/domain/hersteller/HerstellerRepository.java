package de.dhbw.softwareengineering.anbauplaner.domain.hersteller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HerstellerRepository {

    List<Hersteller> findAllHerstellers();

    Optional<Hersteller> findHerstellerById(UUID herstellerId);

    Hersteller save(Hersteller hersteller);
}