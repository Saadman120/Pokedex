package persistence;

import model.Pokedex;
import model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Pokedex from JSON data stored in a file
// Citation: JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read form source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads Pokedex from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Pokedex read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsesPokedex(jsonObject);
    }

    //MODIFIES: pokedex
    //EFFECTS: parses pokemon from JSON object and adds it to pokedex
    private void addPokemon(Pokedex pokedex, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int ndex = jsonObject.getInt("ndex");
        String type = jsonObject.getString("type");
        String description = jsonObject.getString("description");

        Pokemon pokemon = new Pokemon(name, ndex, type, description);
        pokedex.addPokemon(pokemon);
    }

    // MODIFIES: pokedex
    // EFFECTS: parses pokemons from JSON object and adds them to pokedex
    private void addPokemons(Pokedex pokedex, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pokedex");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addPokemon(pokedex, nextPokemon);
        }
    }

    // EFFECTS: parses pokedex from JSON object and returns it
    private Pokedex parsesPokedex(JSONObject jsonObject) {
        Pokedex pokedex = new Pokedex();
        addPokemons(pokedex, jsonObject);
        return pokedex;
    }
}
