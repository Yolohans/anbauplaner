package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.hersteller;

import de.dhbw.softwareengineering.anbauplaner.domain.hersteller.Hersteller;
import de.dhbw.softwareengineering.anbauplaner.domain.hersteller.HerstellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HerstellerRepositoryBridge implements HerstellerRepository {
    private final SpringDataHerstellerRepository springDataHerstellerRepository;

    @Autowired
    public HerstellerRepositoryBridge(final SpringDataHerstellerRepository springDataHerstellerRepository) {
        this.springDataHerstellerRepository = springDataHerstellerRepository;
    }

    @Override
    public List<Hersteller> findAllHerstellers() {
        return this.springDataHerstellerRepository.findAll();
    }

    @Override
    public Optional<Hersteller> findHerstellerById(final UUID herstellerId) {
        return this.springDataHerstellerRepository.findById(herstellerId);
    }

    @Override
    public Hersteller save(final Hersteller hersteller) {
        return this.springDataHerstellerRepository.save(hersteller);
    }
}