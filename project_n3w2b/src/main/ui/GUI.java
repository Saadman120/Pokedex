package ui;

import model.Pokedex;
import model.Pokemon;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Builds the GUI for the Pokedex
public class GUI extends JFrame implements MouseListener, ActionPerform {

    JPanel infoPanel;
    JSplitPane splitPane;
    private static DefaultListModel<Pokemon> model = new DefaultListModel<>();
    JPanel descriptionPanel = new JPanel();
    JTextArea descriptionText = new JTextArea();
    JList<Pokemon> pokemonName = new JList<>();
    JScrollPane nameList = new JScrollPane(pokemonName);

    JButton saveButton;
    JButton loadButton;
    JButton searchButton;
    JButton addPokemonButton;
    JTextField searchPokemonText;

    static Pokedex pokedex = new Pokedex();
    private static final String JSON_STORE = "./data/pokedex.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    //EFFECTS: adds a new frame and hold components for the Pokedex
    public GUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pokedex");
        this.setLayout(null);
        this.setBounds(0,0,500, 500);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(4, 96, 93));
        setPanels();
        this.setVisible(true);
    }

    //EFFECTS: creates a new JPanel and adds to the frame
    public void mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 300, 500, 200);
        mainPanel.setBackground(new Color(214, 96, 93));
        mainPanelFeatures();
        this.add(mainPanel);
    }

    //EFFECTS: creates a new Jpanel to hold Pokemon information
    public void infoPanel() {
        infoPanel = new JPanel();
        infoPanel.setBounds(0, 0, 500, 300);
        infoPanel.setBackground(Color.blue);
        splitPane();
        this.add(infoPanel);
    }

    //MODIFIES: this
    //EFFECTS:  adds a splitPane that holds a list of Pokemon and description of Pokemon to the infoPanel
    public void splitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBounds(0, 0, 500, 300);
        splitPane.setDividerLocation(0.3);
        splitPane.setBackground(new Color(4, 96, 93));
        addPokemonToList();
        splitPane.setLeftComponent(nameList);
        setDescriptionText();
        splitPane.setRightComponent(descriptionPanel);
        pokemonName.setModel(model);
        pokemonName.getSelectionModel().addListSelectionListener(e -> {
            Pokemon p = pokemonName.getSelectedValue();
            if (p != null) {
                descriptionText.setText(p.toDescriptionString());
            }
        });
        this.add(splitPane);
    }

    //MODIFIES: this
    //EFFECTS: adds Pokemon from Pokedex to the model
    private void addPokemonToList() {
        for (Pokemon p : pokedex.getPokedex()) {
            model.addElement(p);
        }
    }

    // EFFECTS: adds a text area to the desscriptionPanel
    private void setDescriptionText() {
        descriptionPanel.add(descriptionText);
        descriptionText.setBounds(150,0,350,300);
        descriptionText.setFont(new Font("Arial", Font.PLAIN, 20));
        descriptionText.setWrapStyleWord(true);
        descriptionText.setBackground(new Color(114,206,227));
        descriptionText.setLineWrap(true);
    }

    //EFFECTS: returns model
    public static DefaultListModel getModel() {
        return model;
    }


    //MODIFIES: this
    //EFFECTS: adds JPanels to the frame
    public void setPanels() {
        mainPanel();
        infoPanel();
    }

    //EFFECTS: adds buttons,labels,textfields to the mainPanel
    public void mainPanelFeatures() {
        setSaveButton();
        setLoadButton();
        searchPokemon();
        addPokemonButton();
        loadPokedexLabel();
        welcomeLabel();
    }

    //MODIFIES: this
    //EFFECTS: creates a button to save the Pokedex
    public void setSaveButton() {
        saveButton = new JButton();
        saveButton.setBounds(0, 445, 500, 30);
        saveButton.addActionListener(this);
        saveButton.setText("Save Pokedex");
        saveButton.setFocusable(false);
        saveButton.addMouseListener(this);
        this.add(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a button to load the Pokedex
    public void setLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(0, 422, 500, 30);
        loadButton.addMouseListener(this);
        loadButton.setText("Load Pokedex");
        loadButton.setFocusable(false);
        this.add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: adds label and textfield to search for Pokemon
    public void searchPokemon() {
        searchPokemonTextField();
        searchPokeonButton();
    }

    //MODIFIES: this
    //EFFECTS: creates a button to search for Pokemon
    private void searchPokeonButton() {
        searchButton = new JButton();
        searchButton.setBounds(350, 398, 150, 30);
        searchButton.setText("Search Pokemon");
        searchButton.setFocusable(false);
        searchButton.addActionListener(this);
        searchButton.addMouseListener(this);
        this.add(searchButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a textfield to input Pokemon name to search for
    private void searchPokemonTextField() {
        searchPokemonText = new JTextField();
        searchPokemonText.setBounds(6, 398, 350, 30);
        searchPokemonText.addActionListener(this);
        this.add(searchPokemonText);
    }

    //MODIFIES: this
    //EFFECTS: creates a button to add a new Pokemon to the Pokedex
    public void addPokemonButton() {
        addPokemonButton = new JButton();
        addPokemonButton.setBounds(0, 375, 500, 30);
        addPokemonButton.setText("Add new Pokemon");
        addPokemonButton.addActionListener(this);
        addPokemonButton.addMouseListener(this);
        setFocusable(false);
        this.add(addPokemonButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a label to inform the user to load the pokedex
    public void loadPokedexLabel() {
        JLabel loadPokemonLabel = new JLabel();
        loadPokemonLabel.setBounds(97, 335, 500, 30);
        loadPokemonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        loadPokemonLabel.setText("Remember to load the Pokedex!");
        loadPokemonLabel.setForeground(new Color(251,241,170));
        setFocusable(false);
        this.add(loadPokemonLabel);
    }

    //MODIFIES: this
    //EFFECTS: creates and adds a welcome label
    public void welcomeLabel() {
        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setBounds(38, 310, 500, 30);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setText("Welcome to your personal Pokedex Trainer!");
        welcomeLabel.setForeground(new Color(255,200,9));
        setFocusable(false);
        this.add(welcomeLabel);
    }

    //MODIFIES: this
    //EFFECTS: processes command to search for a Pokemon
    public void searchPokemonCommand() {
        Pokemon foundPokemon = pokedex.findPokemon(searchPokemonText.getText().toLowerCase());
        if (foundPokemon != null) {
            JOptionPane.showMessageDialog(null, foundPokemon.toDescriptionString());
        } else {
            JOptionPane.showMessageDialog(null, "Pokemon not found");
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPokemonButton) {
            new AddPokemonWindow();
        }
        if (e.getSource() == searchPokemonText || e.getSource() == searchButton) {
            searchPokemonCommand();
            searchPokemonText.setText("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == saveButton) {
            savePokedex();
        }
        if (e.getSource() == loadButton) {
            loadPokedex();
            model.removeAllElements();
            addPokemonToList();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == saveButton) {
            ButtonSound.clickSound("buttonClick.wav");
        }
        if (e.getSource() == loadButton) {
            ButtonSound.clickSound("buttonClick.wav");
        }
        if (e.getSource() == addPokemonButton) {
            ButtonSound.clickSound("buttonClick.wav");
        }
        if (e.getSource() == searchButton) {
            ButtonSound.clickSound("buttonClick.wav");
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // EFFECTS: saves the pokedex to file
    private void savePokedex() {
        try {
            jsonWriter.open();
            jsonWriter.write(pokedex);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Pokedex saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pokedex from file
    public void loadPokedex() {
        try {
            pokedex = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Pokedex loaded");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }
}
