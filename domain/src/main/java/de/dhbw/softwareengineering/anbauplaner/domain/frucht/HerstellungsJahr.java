package de.dhbw.softwareengineering.anbauplaner.domain.frucht;

public final class HerstellungsJahr {
    private static final int MIN_YEAR = 2000;
    private static final int MAX_YEAR = 2100;

    private final int year;

    private HerstellungsJahr(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            throw new IllegalArgumentException("Year must be between " + MIN_YEAR + " and " + MAX_YEAR);
        }
        this.year = year;
    }

    public static HerstellungsJahr of(Integer year) {
        if (year == null) {
            return null;
            // implement logging here - such that this case can be detected and fixed
        } else {
            return new HerstellungsJahr(year);
        }
    }

    public Integer getValue() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HerstellungsJahr that = (HerstellungsJahr) o;
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
