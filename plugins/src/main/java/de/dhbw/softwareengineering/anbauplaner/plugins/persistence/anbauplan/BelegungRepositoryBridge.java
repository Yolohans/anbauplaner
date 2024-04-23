package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.Belegung;
import de.dhbw.softwareengineering.anbauplaner.domain.anbauplan.BelegungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BelegungRepositoryBridge implements BelegungRepository {
    private final SpringDataBelegungRepository springDataBelegungRepository;

    @Autowired
    public BelegungRepositoryBridge(final SpringDataBelegungRepository springDataBelegungRepository) {
        this.springDataBelegungRepository = springDataBelegungRepository;
    }

    @Override
    public List<Belegung> findAllBelegungen() {
        return this.springDataBelegungRepository.findAll();
    }

    @Override
    public Optional<Belegung> findBelegungById(final UUID belegungId) {
        return this.springDataBelegungRepository.findById(belegungId);
    }

    @Override
    public Belegung save(final Belegung belegung) {
        return this.springDataBelegungRepository.save(belegung);
    }
}