package model;

import org.json.JSONObject;

//Contains properties of a Pokemon
public class Pokemon implements persistence.Writable {
    private String name;
    private int nationalDexNum;
    private String type;
    private String description;

    //EFFECTS: instantiates a new pokemon
    public Pokemon(String name, int ndex, String type, String description) {
        this.name = name;
        this.nationalDexNum = ndex;
        this.type = type;
        this.description = description;
    }

    //EFFECTS: returns the name of the pokemon
    public String getName() {
        return name;
    }

    //EFFECTS: returns the nationalDexNum of the pokemon
    public int getNationalDexNum() {
        return nationalDexNum;
    }

    //EFFECTS: returns the type of the pokemon
    public String getType() {
        return type;
    }

    //EFFECTS: returns the description of the pokemon
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns a string containing pokemon details
    public String toDescriptionString() {
        return "Pokemon: " + name + "\nNational Dex #: " + nationalDexNum + "\nType: " + type + "\nDescription: "
                + description;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("ndex", nationalDexNum);
        json.put("type", type);
        json.put("description", description);
        return json;
    }

    @Override
    public String toString() {
        return name;
    }
}
