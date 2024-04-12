package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PolygonRepository {

    List<Polygon> findAllPolygons();

    Optional<Polygon> findPolygonById(UUID shapeId);

    Polygon save(Polygon polygon);
}
