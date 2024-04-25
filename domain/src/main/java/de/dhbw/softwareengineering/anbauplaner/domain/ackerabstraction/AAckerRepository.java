package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AAckerRepository {

    List<AAcker> findAllAAckers();

    Optional<AAcker> findAAckerById(UUID ackerId);

    AAcker save(AAcker acker);
}