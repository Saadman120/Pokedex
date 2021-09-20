package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private Pokemon bulbasaur = new Pokemon("Bulbasaur", 1, "Grass,Poison",
            "A strange seed was planted on its back at birth. The plant sprouts and grows with this Pokemon.")
            ;
    private Pokemon charmander = new Pokemon("Charmander", 4, "Fire",
            "Obviously prefers hot places. When it rains, steam is said to spout from the tip of its tail.");
    private Pokemon squirtle = new Pokemon("Squirtle", 7, "Water",
            "After birth, its back swells and hardens into a shell. Powerfully sprays foam from its mouth.");

    @Test
    void testGetName() {
        assertEquals("Bulbasaur", bulbasaur.getName());
    }

    @Test
    void testGetNationalDexNum() {
        assertEquals(7, squirtle.getNationalDexNum());
    }

    @Test
    void testGetType() {
        assertEquals("Fire", charmander.getType());
    }

    @Test
    void testGetDescription() {
        assertEquals("Obviously prefers hot places. When it rains, steam is said to spout from the tip of " +
                "its tail.", charmander.getDescription());
    }

    @Test
    void testToString() {
        assertEquals("Pokemon: Squirtle\nNational Dex #: 7\nType: Water\nDescription: " +
                "After birth, its back swells and hardens into a shell. Powerfully sprays foam from its mouth.",
                squirtle.toDescriptionString());
    }
}