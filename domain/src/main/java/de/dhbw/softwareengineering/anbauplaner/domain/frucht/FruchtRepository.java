package de.dhbw.softwareengineering.anbauplaner.domain.frucht;

import java.util.List;
import java.util.Optional;

public interface FruchtRepository {
    List<Frucht> findAllFruchts();

    Optional<Frucht> findFruchtById(String fruchtId);

    Frucht save(Frucht frucht);
}
