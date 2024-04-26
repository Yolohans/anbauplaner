package de.dhbw.softwareengineering.anbauplaner.domain.domainservices.exceptions;

import de.dhbw.softwareengineering.anbauplaner.domain.domainservices.Collidable;

public class ChildDoesNotFitException extends RuntimeException{
    private Collidable child;
    private Collidable parent;

    public ChildDoesNotFitException(Collidable child, Collidable parent, String message) {
        super(message);
        this.child = child;
        this.parent = parent;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Child " + child.toString() + " out of parent " + parent.toString();
    }

    public Collidable getCollision() {
        return this.parent;
    }

    public Collidable getSource() {
        return this.child;
    }
}
