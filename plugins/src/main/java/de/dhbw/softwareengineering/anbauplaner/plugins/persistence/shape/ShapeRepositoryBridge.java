package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.ShapeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ShapeRepositoryBridge implements ShapeRepository {
    private final SpringDataShapeRepository springDataShapeRepository;

    @Autowired
    public ShapeRepositoryBridge(final SpringDataShapeRepository springDataShapeRepository) {
        this.springDataShapeRepository = springDataShapeRepository;
    }

    @Override
    public List<Shape> findAllShapes() {
        return this.springDataShapeRepository.findAll();
    }

    @Override
    public Optional<Shape> findShapeById(final UUID shapeId) {
        return this.springDataShapeRepository.findById(shapeId);
    }

    @Override
    public Shape save(final Shape shape) {
        return this.springDataShapeRepository.save(shape);
    }
}

