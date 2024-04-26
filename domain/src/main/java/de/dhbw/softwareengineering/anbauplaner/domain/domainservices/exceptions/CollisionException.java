package de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;

import java.util.List;

public class CollisionException extends RuntimeException {
    private Collidable source;
    private List<Collidable> colliders;

    public CollisionException(Collidable source, List<Collidable> colliders, String message) {
        super(message);
        this.source = source;
        this.colliders = colliders;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Collision of " + source + " with:" + colliders.toString();
    }

    public List<Collidable> getCollision() {
        return this.colliders;
    }

    public Collidable getSource() {
        return this.source;
    }
}