package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

@Entity
@DiscriminatorValue("rectangle")
public final class Rectangle extends Shape {

    private Double xLength;
    private Double yLength;
    @Embedded
    private Point position;

    public Rectangle() {super();}

    public Rectangle(Double xLength, Double yLength, Point position) {
        super();
        Objects.requireNonNull(xLength, "xLength cannot be null");
        Objects.requireNonNull(yLength, "yLength cannot be null");
        Objects.requireNonNull(position, "Position cannot be null");
        Validate.isTrue(xLength > 0, "xLength must be larger than 0");
        Validate.isTrue(yLength > 0, "yLength must be larger than 0");

        this.xLength = xLength;
        this.yLength = yLength;
        this.position = position;
    }

    public Double getXLength() {
        return xLength;
    }

    public Double getYLength() {
        return yLength;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Shape translatePosition(Point position) {
        double x = this.getPosition().getX() + position.getX();
        double y = this.getPosition().getY() + position.getY();
        Point point = new Point(x,y);
        return new Rectangle(this.xLength,this.yLength,point);
    }

    @Override
    public Shape subtractPosition(Point position) {
        double x = this.getPosition().getX() - position.getX();
        double y = this.getPosition().getY() - position.getY();
        Point point = new Point(x,y);
        return new Rectangle(this.xLength,this.yLength,point);
    }

    @Override
    public Shape replacePosition(Point position) {
        return new Rectangle(this.xLength, this.yLength, position);
    }

    @Override
    public boolean collidesWith(Shape other) {
        return other.collidesWithRectangle(this);
    }

    @Override
    public boolean collidesWithRectangle(Rectangle other) {
        boolean hasNoCollision =
                this.getPosition().getX() > other.getPosition().getX() + other.getXLength() ||
                this.getPosition().getX() + this.getXLength() < other.getPosition().getX() ||
                this.getPosition().getY() > other.getPosition().getY() + other.getYLength() ||
                this.getPosition().getY() + this.getYLength() < other.getPosition().getY();
        return !hasNoCollision;
    }

    @Override
    public boolean fitsIn(Shape other) {
        return other.coversRectangle(this);
    }

    @Override
    public boolean fitInRectangle(Rectangle other) {
        boolean itFits =
                this.getPosition().getX() >= other.getPosition().getX() &&
                this.getPosition().getY() >= other.getPosition().getY() &&
                this.getPosition().getX() + this.getXLength() <= other.getPosition().getX() + other.getXLength() &&
                this.getPosition().getY() + this.getYLength() <= other.getPosition().getY() + other.getYLength();
        return itFits;
    }

    @Override
    public boolean covers(Shape other) {
        return other.fitInRectangle(this);
    }

    @Override
    public boolean coversRectangle(Rectangle other) {
        boolean itCovers =
                this.getPosition().getX() <= other.getPosition().getX() &&
                this.getPosition().getY() <= other.getPosition().getY() &&
                this.getPosition().getX() + this.getXLength() >= other.getPosition().getX() + other.getXLength() &&
                this.getPosition().getY() + this.getYLength() >= other.getPosition().getY() + other.getYLength();
        return itCovers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(getXLength(), rectangle.getXLength()) && Objects.equals(getYLength(), rectangle.getYLength()) && Objects.equals(getPosition(), rectangle.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getXLength(), getYLength(), getPosition());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rectangle{");
        sb.append("xLength=").append(xLength);
        sb.append(", yLength=").append(yLength);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }
}
