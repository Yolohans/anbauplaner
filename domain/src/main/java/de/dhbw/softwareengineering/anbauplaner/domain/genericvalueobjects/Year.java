package de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects;

public final class Year {
    private static final int MIN_YEAR = 2000;
    private static final int MAX_YEAR = 2100;

    private final int year;

    public Year(Integer year) {
        if (year == null) {
            throw new IllegalArgumentException("Year cannot be null");
        } else if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Year must be between " + MIN_YEAR + " and " + MAX_YEAR);
        }
        this.year = year;
    }

    public int getValue() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year that = (Year) o;
        return year == that.year;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(year);
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }
}
