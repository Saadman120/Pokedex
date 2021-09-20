package ui;

import model.Pokedex;
import model.Pokemon;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Builds the ui of the Pokedex
public class PokedexApp {
    private Pokedex pokedex;
    private Scanner input;
    private static final String JSON_STORE = "./data/pokedex.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the Pokedex
    public PokedexApp() {
        runPokedex();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runPokedex() {
        pokedex = new Pokedex();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            welcome();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processWelcomeCommand(command);
            }
        }
        System.out.println(ConsoleColour.ANSI_CYAN + "\nGood luck Trainer!" + ConsoleColour.ANSI_RESET);
    }

    //EFFECTS: initializes Scanner
    private void init() {
        input = new Scanner(System.in);
    }

    //EFFECTS: displays the welcome screen
    private void welcome() {
        System.out.println("\nWelcome to your personal Pokedex Trainer!");
        System.out.println(ConsoleColour.ANSI_YELLOW + "\nPlease remember to load the Pokedex first"
                + ConsoleColour.ANSI_RESET);
        System.out.println("\tPress p to checkout the list of available Pokemon");
        System.out.println("\tPress s to search for a Pokemon by name");
        System.out.println("\tPress a to add a new Pokemon to the Pokedex");
        System.out.println("\tPress z to save the Pokedex");
        System.out.println("\tPress x to load the Pokedex");
        System.out.println("\tPress q to quit");
    }

    //MODIFIES: this
    //EFFECTS:  processes user command
    private void processWelcomeCommand(String command) {
        if (command.equals("p")) {
            processPokemonSelectionCommand();
        } else if (command.equals("s")) {
            processPokemonSearchCommand();
        } else if (command.equals("a")) {
            processAddPokemonCommand();
        } else if (command.equals("z")) {
            savePokedex();
        } else if (command.equals("x")) {
            loadPokedex();
        } else {
            System.out.println(ConsoleColour.ANSI_RED + "Invalid input" + ConsoleColour.ANSI_RESET);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command for pokemon selection
    private void processPokemonSelectionCommand() {
        String pokemonCommand = "";

        if (pokedex.length() != 0) {

            while (!(pokedex.pokemonFound(pokemonCommand))) {
                System.out.println("\nSelect a Pokemon!");
                System.out.println("\nInsert the name of the Pokemon to learn more about it");
                pokedex.printPokemonName();
                pokemonCommand = input.next();
                pokemonCommand = pokemonCommand.toLowerCase();
            }

            if (pokedex.pokemonFound(pokemonCommand)) {
                System.out.println(ConsoleColour.ANSI_GREEN + pokedex.findPokemon(pokemonCommand).toDescriptionString()
                        + ConsoleColour.ANSI_RESET);
                moveToNextPokemon(pokedex.findPokemon(pokemonCommand));
            } else {
                System.out.println(ConsoleColour.ANSI_RED + "Invalid input" + ConsoleColour.ANSI_RESET);
            }
        } else {
            System.out.println(ConsoleColour.ANSI_RED + "No Pokemon currently registered in Pokedex"
                    + ConsoleColour.ANSI_RESET);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes command to search for a Pokemon
    private void processPokemonSearchCommand() {
        String searchCommand = "";
        System.out.println("\nPlease enter the name of the Pokemon");
        searchCommand = input.next();

        Pokemon foundPokemon = pokedex.findPokemon(searchCommand.toLowerCase());
        if (foundPokemon != null) {
            System.out.println(ConsoleColour.ANSI_GREEN + foundPokemon.toDescriptionString()
                    + ConsoleColour.ANSI_RESET);
        } else {
            System.out.println(ConsoleColour.ANSI_RED + "Pokemon not found" + ConsoleColour.ANSI_RESET);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes command to add a new Pokemon and prints out an updated list of Pokemon
    public void processAddPokemonCommand() {
        String addPokemonName;
        int addPokemonNdex;
        String addPokemonType;
        String addPokemonDescription;

        System.out.println("\nPlease enter the name of the Pokemon");
        String addNameCommand = input.next();
        addPokemonName = addNameCommand;

        System.out.println("\nPlease enter its National Dex number");
        int addNdexCommand = input.nextInt();
        addPokemonNdex = addNdexCommand;

        System.out.println("\nPlease enter the type(s) of the Pokemon");
        String addTypeCommand = input.next();
        addPokemonType = addTypeCommand;

        System.out.println("\nPlease enter a description of the Pokemon");
        input.nextLine();
        String addDescriptionCommand = input.nextLine();
        addPokemonDescription = addDescriptionCommand;

        Pokemon newPokemon = new Pokemon(addPokemonName, addPokemonNdex, addPokemonType, addPokemonDescription);
        pokedex.addPokemon(newPokemon);
        System.out.println(ConsoleColour.ANSI_GREEN + newPokemon.toDescriptionString() + ConsoleColour.ANSI_RESET);
    }

    //MODIFIES: this
    //EFFECTS: reads user input about moving to next Pokemon
    private void moveToNextPokemon(Pokemon current) {
        String goToNextPokemon = "";
        int currentIndex = pokedex.getPokemonIndex(current.getName().toLowerCase());

        while (currentIndex < pokedex.length() - 1) {
            System.out.println("\nWould you like to move to the next Pokemon?");
            System.out.println("y for Yes");
            System.out.println("n for No");
            goToNextPokemon = input.next();
            goToNextPokemon = goToNextPokemon.toLowerCase();

            if (goToNextPokemon.equals("y")) {
                try {
                    current = pokedex.getPokemonByIndex(currentIndex + 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index");
                }
                currentIndex = pokedex.getPokemonIndex(current.getName().toLowerCase());
                System.out.println(ConsoleColour.ANSI_GREEN + current.toDescriptionString() + ConsoleColour.ANSI_RESET);
            } else {
                break;
            }
        }
        if (currentIndex == pokedex.length() - 1) {
            System.out.println("\nYou've reached the end of the Pokedex!");
        }
    }

    // EFFECTS: saves the pokedex to file
    private void savePokedex() {
        try {
            jsonWriter.open();
            jsonWriter.write(pokedex);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pokedex from file
    public void loadPokedex() {
        try {
            pokedex = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
