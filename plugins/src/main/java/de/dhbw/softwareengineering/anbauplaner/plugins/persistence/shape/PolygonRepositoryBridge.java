package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Polygon;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.PolygonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PolygonRepositoryBridge implements PolygonRepository {
    private final SpringDataPolygonRepository springDataPolygonRepository;

    @Autowired
    public PolygonRepositoryBridge(final SpringDataPolygonRepository springDataPolygonRepository) {
        this.springDataPolygonRepository = springDataPolygonRepository;
    }

    @Override
    public List<Polygon> findAllPolygons() {
        return this.springDataPolygonRepository.findAll();
    }

    @Override
    public Optional<Polygon> findPolygonById(final UUID polygonId) {
        return this.springDataPolygonRepository.findById(polygonId);
    }

    @Override
    public Polygon save(final Polygon polygon) {
        return this.springDataPolygonRepository.save(polygon);
    }
}

