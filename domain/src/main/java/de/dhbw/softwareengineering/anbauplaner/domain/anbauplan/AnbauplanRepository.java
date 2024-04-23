package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnbauplanRepository {

    List<Anbauplan> findAllAnbauplans();

    Optional<Anbauplan> findAnbauplanById(UUID anbauplanId);

    Anbauplan save(Anbauplan anbauplan);
}
