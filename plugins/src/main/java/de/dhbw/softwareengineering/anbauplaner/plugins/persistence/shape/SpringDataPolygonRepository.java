package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Polygon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPolygonRepository extends JpaRepository<Polygon, UUID> {

    //Polygon findPolygonById(UUID polygonId);
}
