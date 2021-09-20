package ui;

import model.Pokedex;
import model.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Builds the GUI for adding a new Pokemon to the Pokedex
public class AddPokemonWindow extends JFrame implements ActionPerform {

    JTextField pokemonName;
    JTextField pokemonNdex;
    JTextField pokemonType;
    JTextField pokemonDescription;

    JLabel pokemonNameLabel;
    JLabel pokemonNdexLabel;
    JLabel pokemonTypeLabel;
    JLabel pokemonDescriptionLabel;

    static JButton submitNewPokemon;

    private Pokedex pokedex = GUI.pokedex;

    //EFFECTS: adds a new frame and hold components to add a new Pokemon
    public AddPokemonWindow() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Add Pokemon");
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(214, 96, 93));
        addPokemon();
    }

    //MODIFIES: this
    //EFFECTS: creates and adds label and textfield to input new Pokemon name
    private void addPokemonName() {
        pokemonNameLabel = new JLabel("Name");
        pokemonNameLabel.setBounds(10, 0, 150, 30);
        this.add(pokemonNameLabel);
        pokemonName = new JTextField();
        pokemonName.setBounds(120, 0, 350, 30);
        this.add(pokemonName);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds label and textfield to input new Pokemon Ndex
    private void addPokemonNdex() {
        pokemonNdexLabel = new JLabel("Ndex");
        pokemonNdexLabel.setBounds(10, 50, 150, 30);
        this.add(pokemonNdexLabel);
        pokemonNdex = new JTextField();
        pokemonNdex.setBounds(120, 50, 350, 30);
        this.add(pokemonNdex);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds label and textfield to input new Pokemon Type
    private void addPokemonType() {
        pokemonTypeLabel = new JLabel("Type");
        pokemonTypeLabel.setBounds(10, 100, 150, 30);
        this.add(pokemonTypeLabel);
        pokemonType = new JTextField();
        pokemonType.setBounds(120, 100, 350, 30);
        this.add(pokemonType);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds label and textfield to input new Pokemon Description
    private void addPokemonDescription() {
        pokemonDescriptionLabel = new JLabel("Description");
        pokemonDescriptionLabel.setBounds(10, 150, 150, 30);
        this.add(pokemonDescriptionLabel);
        pokemonDescription = new JTextField();
        pokemonDescription.setBounds(120, 150, 350, 30);
        this.add(pokemonDescription);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds a new button to add the new Pokemon to Pokedex
    private void setSubmitNewPokemon() {
        submitNewPokemon = new JButton();
        submitNewPokemon.setBounds(210, 200, 100, 30);
        submitNewPokemon.addActionListener(this);
        submitNewPokemon.setText("Submit");
        submitNewPokemon.setFocusable(false);
        this.add(submitNewPokemon);
    }

    //MODIFIES: this
    //EFFECTS: adds necessary components to frame to add a new Pokemon to Pokedex
    private void addPokemon() {
        addPokemonName();
        addPokemonNdex();
        addPokemonType();
        addPokemonDescription();
        setSubmitNewPokemon();
    }

    //MODIFIES: this
    //EFFECTS: processes command to add a new Pokemon and prints out an updated list of Pokemon
    public void processAddPokemonCommand() {
        String addPokemonName;
        int addPokemonNdex;
        String addPokemonType;
        String addPokemonDescription;

        addPokemonName = pokemonName.getText();

        addPokemonNdex = Integer.parseInt(pokemonNdex.getText());

        addPokemonType = pokemonType.getText();

        addPokemonDescription = pokemonDescription.getText();

        Pokemon newPokemon = new Pokemon(addPokemonName, addPokemonNdex, addPokemonType, addPokemonDescription);
        pokedex.addPokemon(newPokemon);
        JOptionPane.showMessageDialog(null, newPokemon.toDescriptionString());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitNewPokemon) {
            processAddPokemonCommand();
            GUI.getModel().removeAllElements();
            for (Pokemon p : pokedex.getPokedex()) {
                GUI.getModel().addElement(p);
            }
        }
    }
}
