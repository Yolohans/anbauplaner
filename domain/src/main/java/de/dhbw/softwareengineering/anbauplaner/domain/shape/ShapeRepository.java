package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShapeRepository {

    List<Shape> findAllShapes();

    Optional<Shape> findShapeById(UUID shapeId);

    Shape save(Shape shape);
}
