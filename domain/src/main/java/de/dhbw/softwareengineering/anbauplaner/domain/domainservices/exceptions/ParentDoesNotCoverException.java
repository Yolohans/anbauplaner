package de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;

import java.util.List;

public class ParentDoesNotCoverException extends RuntimeException {
    private Collidable source;
    private List<Collidable> colliders;

    public ParentDoesNotCoverException(Collidable source, List<Collidable> colliders, String message) {
        super(message);
        this.source = source;
        this.colliders = colliders;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + source.toString() + " does not cover " + colliders.toString();
    }

    public List<Collidable> getCollision() {
        return this.colliders;
    }
    public Collidable getSource() {
        return this.source;
    }
}