package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnbauhinweisRepository {

    List<Anbauhinweis> findAllAnbauhinweise();

    Optional<Anbauhinweis> findAnbauhinweisById(UUID anbauhinweisId);

    Anbauhinweis save(Anbauhinweis anbauhinweis);
}