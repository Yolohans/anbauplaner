package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataShapeRepository extends JpaRepository<Shape, UUID> {


}
