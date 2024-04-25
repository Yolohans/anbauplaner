package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ABeetRepository {

    List<ABeet> findAllABeets();

    Optional<ABeet> findABeetById(UUID abeetId);

    ABeet save(ABeet abeet);
}
