package de.dhbw.softwareengineering.anbauplaner.plugins.persistence.shape;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.ShapeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

//import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShapeRepositoryBridge.class)
class ShapeRepositoryBridgeTest {

    //@Autowired
    //private TestEntityManager entityManager;

    @Autowired
    private ShapeRepository shapeRepository;

    @Test
    public void testSaveAndFindRectangle() {
        // Create a new Rectangle
        Rectangle rectangle = new Rectangle(5.0, 10.0, new Point(0.0, 0.0));

        // Save the Rectangle to the database
        Shape savedShape = shapeRepository.save(rectangle);

        // Retrieve the Rectangle from the database
        Optional<Shape> foundShape = shapeRepository.findShapeById(savedShape.getShapeId());

        // Verify that the Rectangle was correctly saved and retrieved
        Assertions.assertTrue(foundShape.isPresent());
        Assertions.assertEquals(rectangle, foundShape.get());
    }
}