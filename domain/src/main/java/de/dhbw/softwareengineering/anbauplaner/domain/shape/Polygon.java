/*
package de.dhbw.softwareengineering.anbauplaner.domain.shape;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;


*/
/*
Dies ist als Platzhalter/Empfänger für eine geeignete Bibliothek im Abstraction-Layer zu sehen.
Problematisch ist die fehlende Validierung von "entarteten" Polygonzügen.
Problematisch ist die Umsetzung relativer Koordinatensysteme (Wo ist "position" --> bounding box?)
Zu deren Umsetzung sind allerdings aufwendige Algorithmen notwendig.
Dies umzusetzen sprengt den Rahmen dieser Projektarbeit. Daneben wird noch die einfachere Shape "Rectangle" angeboten,
deren Validierung simpler ist und die für User Acceptance Test der Kernfunktionalität genügt.
Es gibt ein weiteres Problem: Die Reihenfolge der Punkte ist wichtig. Diese wird von jakarta.persistence aber nicht zwingend erhalten.
ALSO: SO GEHTS NICHT :)
*//*



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
    public UUID getShapeId() {
        return super.getShapeId();
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public Shape translatePosition(Point position) {
        return null;
    }

    @Override
    public Shape subtractPosition(Point position) {
        return null;
    }

    @Override
    public Shape replacePosition(Point position) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polygon{");
        sb.append("points=").append(points);
        sb.append('}');
        return sb.toString();
    }
}
*/
