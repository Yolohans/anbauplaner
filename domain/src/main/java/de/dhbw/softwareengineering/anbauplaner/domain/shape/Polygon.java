/*package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;


Dies ist als Platzhalter/Empfänger für eine geeignete Bibliothek im Abstraction-Layer zu sehen.
Problematisch ist die fehlende Validierung von "entarteten" Polygonzügen.
Zu deren Umsetzung sind allerdings aufwendige Algorithmen notwendig
Dies umzusetzen sprengt den Rahmen dieser Projektarbeit. Daneben wird noch die einfachere Shape "Rectangle" angeboten,
deren Validierung simpler ist und die für User Acceptance Test der Kernfunktionalität genügt.
Es gibt ein weiteres Problem: Die Reihenfolge der Punkte ist wichtig. Diese wird von jakarta.persistence aber nicht zwingend erhalten!


@Entity
@DiscriminatorValue("polygon")
public final class Polygon extends Shape {
    @ElementCollection
    private ArrayList<Point> points;
    public Polygon() {
        super();
    }

    public Polygon(ArrayList<Point> points) {
        super();
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        return Objects.equals(getShapeType(), polygon.getShapeType()) && Objects.equals(points, polygon.points);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getShapeType(), points);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polygon{");
        sb.append("points=").append(points);
        sb.append(", shapeType='").append(shapeType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
*/