package de.dhbw.softwareengineering.anbauplaner.adapters.ackertemplate.representations;

public class AckerTemplateRequestDTO {
    private String name;
    private Double xLength;
    private Double yLength;
    private Double x;
    private Double y;

    public AckerTemplateRequestDTO(String name, Double xLength, Double yLength, Double x, Double y) {
        this.name = name;
        this.xLength = xLength;
        this.yLength = yLength;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public Double getXLength() {
        return xLength;
    }

    public Double getYLength() {
        return yLength;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXLength(Double xLength) {
        this.xLength = xLength;
    }

    public void setYLength(Double yLength) {
        this.yLength = yLength;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }
}

