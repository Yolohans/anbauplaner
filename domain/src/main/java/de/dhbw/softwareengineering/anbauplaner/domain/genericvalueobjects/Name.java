package de.dhbw.softwareengineering.anbauplaner.domain.genericvalueobjects;

import java.util.regex.Pattern;


public final class Name {
    private static final int MAX_LENGTH = 120;
    private static final Pattern VALID_CHARACTERS = Pattern.compile("[\\p{L}\\p{N}_\\-\\. ]+");

    private final String value;

    public Name(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("name value cannot be null or empty");
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("name length exceeds the maximum limit of " + MAX_LENGTH);
        }
        if (!VALID_CHARACTERS.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid characters in name");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equals(name.value);
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
