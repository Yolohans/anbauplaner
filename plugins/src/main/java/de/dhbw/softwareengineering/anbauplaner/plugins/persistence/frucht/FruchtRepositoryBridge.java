package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.frucht;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Frucht;
import de.dhbw.softwareengineering.anbauplaner.domain.frucht.FruchtRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FruchtRepositoryBridge implements FruchtRepository {

    private final SpringDataFruchtRepository springDataFruchtRepository;

    @Autowired
    public FruchtRepositoryBridge(final SpringDataFruchtRepository springDataFruchtRepository) {
        this.springDataFruchtRepository = springDataFruchtRepository;
    }

    @Override
    public List<Frucht> findAllFruchts() {
        return this.springDataFruchtRepository.findAll();
    }

    @Override
    public Optional<Frucht> findFruchtById(String fruchtId) {
        return this.springDataFruchtRepository.findById(fruchtId);
    }

    @Override
    public Frucht save(Frucht frucht) {
        return this.springDataFruchtRepository.save(frucht);
    }
}
