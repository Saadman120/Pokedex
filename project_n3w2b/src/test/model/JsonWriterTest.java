package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
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
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyPokedex() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPokedex.json");
            writer.open();
            writer.write(testPokedex);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPokedex.json");
            testPokedex = reader.read();
            assertEquals(0, testPokedex.length());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    void testWriterPokedex() {
        try {
            testPokedex.addPokemon(caterpie);
            testPokedex.addPokemon(charizard);
            JsonWriter writer = new JsonWriter("./data/testWriterPokedex.json");
            writer.open();
            writer.write(testPokedex);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterPokedex.json");
            testPokedex = reader.read();
            assertEquals(2, testPokedex.length());
            assertEquals("Caterpie", testPokedex.getPokemonByIndex(0).getName());
            assertEquals("Charizard", testPokedex.getPokemonByIndex(1).getName());
        } catch (IOException e) {
            fail("Unexpected exception thrown");
        } catch (IndexOutOfBoundsException e) {
            fail("Unexpected exception thrown");
        }
    }
}
