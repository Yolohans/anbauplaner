package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Anbauhinweis;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.AnbauhinweisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AnbauhinweisRepositoryBridge implements AnbauhinweisRepository {
    private final SpringDataAnbauhinweisRepository springDataAnbauhinweisRepository;

    @Autowired
    public AnbauhinweisRepositoryBridge(final SpringDataAnbauhinweisRepository springDataAnbauhinweisRepository) {
        this.springDataAnbauhinweisRepository = springDataAnbauhinweisRepository;
    }

    @Override
    public List<Anbauhinweis> findAllAnbauhinweise() {
        return this.springDataAnbauhinweisRepository.findAll();
    }

    @Override
    public Optional<Anbauhinweis> findAnbauhinweisById(final UUID anbauhinweisId) {
        return this.springDataAnbauhinweisRepository.findById(anbauhinweisId);
    }

    @Override
    public Anbauhinweis save(final Anbauhinweis anbauhinweis) {
        return this.springDataAnbauhinweisRepository.save(anbauhinweis);
    }
}
