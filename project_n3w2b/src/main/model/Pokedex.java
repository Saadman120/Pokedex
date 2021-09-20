package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Creates a Pokedex
public class Pokedex implements Writable {
    private ArrayList<Pokemon> pokedex;
    private String name;
    private ArrayList<String> nameList;

    //EFFECTS: initializes a new Pokedex
    public Pokedex() {
        pokedex = new ArrayList<Pokemon>();
    }

    //MODIFIES: this
    //EFFECTS: adds Pokemon to a list of Pokemons
    public void addPokemon(Pokemon p) {
        pokedex.add(p);
    }

    //EFFECTS: finds pokemon based on name input and returns the corresponding Pokemon object;
    //         otherwise returns null
    public Pokemon findPokemon(String input) {
        for (Pokemon p : pokedex) {
            if (input.equals(p.getName().toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    //EFFECTS: returns true if pokemon based on name input is found;
    //         otherwise returns false
    public Boolean pokemonFound(String input) {
        for (Pokemon p : pokedex) {
            if (input.equals(p.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the index of the Pokemon by input string of Pokemon name
    public int getPokemonIndex(String name) {
        return pokedex.indexOf(findPokemon(name));
    }

    // EFFECTS: returns pokemon by index
    public Pokemon getPokemonByIndex(int i) throws IndexOutOfBoundsException {
        if (!(i <= pokedex.size())) {
            throw new IndexOutOfBoundsException();
        }
        return pokedex.get(i);
    }

    //EFFECTS: prints the Pokedex
    public String printPokedex() {
        for (Pokemon p : pokedex) {
            System.out.println(p);
        }
        return null;
    }

    // EFFECTS: returns Pokemon in a Pokedex as a JSON array
    private JSONArray pokedexToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon p : pokedex) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pokedex", pokedexToJson());
        return json;
    }

    //EFFECTS: prints the names of all Pokemon in Pokedex
    public void printPokemonName() {
        for (Pokemon p : pokedex) {
            System.out.println(p.getName());
        }
    }

    // EFFECTS: returns the Pokedex
    public ArrayList<Pokemon> getPokedex() {
        return pokedex;
    }

    // EFFECTS: returns the size of the Pokedex list
    public int length() {
        return pokedex.size();
    }
}
