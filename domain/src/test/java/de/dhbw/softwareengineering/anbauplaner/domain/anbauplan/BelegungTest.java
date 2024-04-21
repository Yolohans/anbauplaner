package de.dhbw.softwareengineering.anbauplaner.domain.anbauplan;

import de.dhbw.softwareengineering.anbauplaner.domain.frucht.Frucht;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BelegungTest {

    private Belegung belegung;
    private LocalDate beginn;
    private LocalDate ende;
    private Frucht frucht;
    private List<Anbauhinweis> anbauhinweise;

    @BeforeEach
    void setUp() {
        beginn = LocalDate.now();
        ende = LocalDate.now().plusDays(1);
        frucht = Mockito.mock(Frucht.class); 
        anbauhinweise = Arrays.asList(new Anbauhinweis(), new Anbauhinweis()); 

        belegung = new Belegung(beginn, ende, frucht, anbauhinweise);
    }

    @Test
    void testConstructor() {
        assertAll(
                () -> assertEquals(beginn, belegung.getBeginn()),
                () -> assertEquals(ende, belegung.getEnde()),
                () -> assertEquals(frucht, belegung.getFrucht()),
                () -> assertEquals(anbauhinweise, belegung.getAnbauhinweise())
        );
    }

    @Test
    void testConstructorWithBeginnAfterEnde() {
        LocalDate beginnAfterEnde = LocalDate.now().plusDays(1);
        LocalDate endeBeforeBeginn = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Belegung(beginnAfterEnde, endeBeforeBeginn, frucht, anbauhinweise);
        }, "'Beginn' must be before 'ende'");
    }
}