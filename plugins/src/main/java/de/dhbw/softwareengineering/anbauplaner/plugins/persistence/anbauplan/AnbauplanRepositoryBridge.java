package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Anbauplan;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.AnbauplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AnbauplanRepositoryBridge implements AnbauplanRepository {
    private final SpringDataAnbauplanRepository springDataAnbauplanRepository;

    @Autowired
    public AnbauplanRepositoryBridge(final SpringDataAnbauplanRepository springDataAnbauplanRepository) {
        this.springDataAnbauplanRepository = springDataAnbauplanRepository;
    }

    @Override
    public List<Anbauplan> findAllAnbauplans() {
        return this.springDataAnbauplanRepository.findAll();
    }

    @Override
    public Optional<Anbauplan> findAnbauplanById(final UUID anbauplanId) {
        return this.springDataAnbauplanRepository.findById(anbauplanId);
    }

    @Override
    public Anbauplan save(final Anbauplan anbauplan) {
        return this.springDataAnbauplanRepository.save(anbauplan);
    }
}