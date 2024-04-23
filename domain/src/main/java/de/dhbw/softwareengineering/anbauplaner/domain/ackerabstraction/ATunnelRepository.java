package de.dhbw.softwareengineering.anbauplaner.domain.ackerabstraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ATunnelRepository {

    List<ATunnel> findAllATunnels();

    Optional<ATunnel> findATunnelById(UUID atunnelId);

    ATunnel save(ATunnel atunnel);
}