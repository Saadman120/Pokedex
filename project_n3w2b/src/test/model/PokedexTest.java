package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PokedexTest {
    private Pokedex testPokedex;
    private Pokemon caterpie = new Pokemon("Caterpie", 10,
            "Bug", "Its short feet are tipped with suction pads that enable it to tirelessly " +
            "climb slopes and walls.");
    private Pokemon charizard = new Pokemon("Charizard", 6, "Fire,Flying",
            "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.");

    @BeforeEach
    void runBefore() {
        testPokedex = new Pokedex();
    }

    @Test
    void testAddStarterPokemon() {
        testPokedex.addPokemon(caterpie);
        assertEquals(caterpie, testPokedex.findPokemon("caterpie"));
    }

    @Test
    void testFindPokemon() {
        testPokedex.addPokemon(caterpie);
        assertEquals(caterpie, testPokedex.findPokemon("caterpie"));
        assertEquals(null, testPokedex.findPokemon("Charizard"));
    }

    @Test
    void testPokedex() {
        testPokedex.addPokemon(caterpie);
        testPokedex.addPokemon(charizard);
        assertEquals(caterpie, testPokedex.findPokemon("caterpie"));
        assertEquals(charizard, testPokedex.findPokemon("charizard"));
    }

    @Test
    void testPrintPokedex() {
        assertEquals(null, testPokedex.printPokedex());
        testPokedex.addPokemon(caterpie);
        assertEquals(null , testPokedex.printPokedex());
    }

    @Test
    void testPokemonFound() {
        testPokedex.addPokemon(charizard);
        assertTrue(testPokedex.pokemonFound("charizard"));
        assertFalse(testPokedex.pokemonFound("Dialga"));
    }

    @Test
    void testGetPokemonByIndexNotInList() {
        assertEquals(0,testPokedex.length());

        try {
            testPokedex.getPokemonByIndex(0);
            fail("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
        }

        testPokedex.addPokemon(caterpie);
        testPokedex.addPokemon(charizard);
        assertEquals(2,testPokedex.length());

        try {
            testPokedex.getPokemonByIndex(10);
            fail("Expected exception not thrown");
        } catch (IndexOutOfBoundsException e) {
        }
        assertEquals(0, testPokedex.getPokemonIndex("caterpie"));
        assertEquals(1, testPokedex.getPokemonIndex("charizard"));
    }

    @Test
    void testGetPokemonByIndexInList() {
        assertEquals(0, testPokedex.length());
        testPokedex.addPokemon(caterpie);
        testPokedex.addPokemon(charizard);

        try {
            testPokedex.getPokemonByIndex(0);
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected exception caught");
        }
        assertEquals(0, testPokedex.getPokemonIndex("caterpie"));
    }

    @Test
    void testGetPokedex() {
        testPokedex.addPokemon(caterpie);
        ArrayList<Pokemon> testPokemon = new ArrayList<>();
        testPokemon.add(caterpie);
        assertEquals(testPokemon, testPokedex.getPokedex());
    }

    @Test
    void testPrintPokemonName() {
        testPokedex.printPokemonName();
        testPokedex.addPokemon(caterpie);
        testPokedex.printPokemonName();
    }
}
