package de.dhbw.softwareengineering.anbauplaner.domain.domainservices;

import de.dhbw.softwareengineering.anbauplaner.domain.shape.Point;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Rectangle;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
* Was wäre gutes Testdesign:
*  - schreibe einfachste Implementierung von Collidable durch Class coll inmpl coll {getShape = ...}
*  - beforeEach new coll l1000w1000p0 = ...
*  - konstruiere funktionierenden Case
*  - konstruiere alle edgeCases
* */

class CollidableTest {

    private Collidable firstCollidable;
    private Collidable secondCollidable;
    private Collidable thirdCollidable;

    private class FirstCollidable implements Collidable {
        @Override
        public Shape getShape() {
            return new Rectangle(1000.,1000.,new Point(0.,0.));
            }
    }

    private class SecondCollidable implements Collidable {
        @Override
        public Shape getShape() {
            return new Rectangle(100.,100.,new Point(0.,0.));
        }
    }

    private class ThirdCollidable implements Collidable {
        @Override
        public Shape getShape() {
            return new Rectangle(100.,100.,new Point(102.,102.));
        }
    }

    @BeforeEach
    public void setup() {
        firstCollidable = new FirstCollidable();
        secondCollidable = new SecondCollidable();
    }

    @Test
    void collidesWith_Itself() {
        //assert
        Assertions.assertEquals(firstCollidable, firstCollidable.collidesWith(firstCollidable));
    }

    @Test
    void collidesWith_DoesCollide() {
        //arrange
        Collidable thirdCollidable = new ThirdCollidable();

        //assert
        Assertions.assertTrue(firstCollidable.collidesWith(thirdCollidable) == thirdCollidable);
        Assertions.assertTrue(thirdCollidable.collidesWith(firstCollidable) == firstCollidable);
    }

    @Test
    void collidesWith_DoesNotCollide() {
        //arrange
        Collidable thirdCollidable = new ThirdCollidable();

        Assertions.assertTrue(secondCollidable.collidesWith(thirdCollidable) == null);
        Assertions.assertTrue(thirdCollidable.collidesWith(secondCollidable) == null);
    }

    @Test
    void doesNotFitInto_doesFit() {
        //assert
        Assertions.assertFalse(secondCollidable.doesNotFitInto(firstCollidable));
    }

    @Test
    void doesNotFitInto_doesNotFit() {
        //assert
        Assertions.assertTrue(firstCollidable.doesNotFitInto(secondCollidable));
    }

    @Test
    void doesNotFitInto_doesNotFit_disjoint() {
        //arrange
        Collidable thirdCollidable = new ThirdCollidable();
        //assert
        Assertions.assertTrue(firstCollidable.doesNotFitInto(thirdCollidable));
    }
}