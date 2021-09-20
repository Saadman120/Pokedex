package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

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
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/testNoFile.json");
        try {
            testPokedex = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptyPokedex() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPokedex.json");
        try {
            testPokedex = reader.read();
            assertEquals(0, testPokedex.length());
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }

    @Test
    void testReaderPokedex() {
        JsonReader reader = new JsonReader("./data/testReaderPokedex.json");
        try {
            testPokedex = reader.read();
            assertEquals(2, testPokedex.length());
            assertEquals("Caterpie", testPokedex.getPokemonByIndex(0).getName());
            assertEquals("Charizard", testPokedex.getPokemonByIndex(1).getName());
        } catch (IOException e) {
            fail("Unable to read from file");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected exception thrown");
        }
    }
}
