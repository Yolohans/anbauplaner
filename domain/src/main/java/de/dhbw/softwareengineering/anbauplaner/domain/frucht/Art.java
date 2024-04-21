package de.dhbw.softwareengineering.anbauplaner.domain.frucht;

public final class Art {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 100;

    private final String value;

    private Art(String value) {
         if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Art length exceeds the maximum limit of " + MAX_LENGTH);
        } else if (value.length() < MIN_LENGTH) {
            throw new IllegalArgumentException("Art length must be at least " + MIN_LENGTH);
        }
        this.value = value;
    }

    public static Art of(String value) {
        if (value == null || value.isEmpty()) {
            return null;
            // implement logging here - such that this case can be detected and fixed
        } else {
            return new Art(value);
        }
    }


    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Art art = (Art) o;
        return value.equals(art.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
