package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;

// klasa drogi na mapie
public class Road {
    
    private List<Field> fields = new ArrayList<>();

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    
    public void addField(int x, int y) {
        this.fields.add(new Field(x, y));
    }
    
    public void addField(Field field) {
        this.fields.add(field);
    }
}
