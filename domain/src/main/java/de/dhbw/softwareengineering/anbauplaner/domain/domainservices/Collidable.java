package de.dhbw.softwareengineering.anbauplaner.domain.domainservices;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions.ChildDoesNotFitException;
import de.dhbw.softwareengineering.anbauplaner.domain.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public interface Collidable {
    Shape getShape();

    default Collidable collidesWith(Collidable other) {
        if (this.getShape().collidesWith(other.getShape())) {
            return other;
        } else {
            return null;
        }
    }

    default List<Collidable> collidesWith(List<Collidable> others) {
        List<Collidable> colliders = new ArrayList<>();

        for (Collidable elem : others) {
            Collidable collider = this.collidesWith(elem);
            if (collider != null) {
                colliders.add(this.collidesWith(elem));
            }
        }
        return colliders;
    }

    default boolean doesNotFitInto(Collidable other) {
        if (!this.getShape().doesNotFitIn(other.getShape())) {
            return true;
        } else {
            return false;
        }
    }

    default List<Collidable> doesNotCover(List<Collidable> others) {
        List<Collidable> colliders = new ArrayList<>();

        for (Collidable collidable : others) {
            if (collidable.doesNotFitInto(this)) {
                colliders.add(collidable);
            };
        }
        return colliders;
    }
}
