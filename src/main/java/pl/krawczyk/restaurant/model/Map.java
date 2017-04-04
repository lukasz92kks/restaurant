package pl.krawczyk.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import pl.krawczyk.restaurant.enums.FieldType;

// klasa mapy
public class Map {
    
    public static final int SIZE = 32;
    
    private FieldType[][] fields = new FieldType[SIZE][SIZE];   // tablica dwuwymiarowa pól
    private List<Road> roads = new ArrayList<>();               // lista możliwych tras na mapie

    public FieldType[][] getFields() {
        return fields;
    }

    public void setFields(FieldType[][] fields) {
        this.fields = fields;
    }
    
    public void setField(int x, int y, FieldType fieldType) {
        fields[x][y] = fieldType;
    }
    
    public void setField(Field field, FieldType fieldType) {
        fields[field.getX()][field.getY()] = fieldType;
    }
    
    public FieldType getField(int x, int y) {
        return fields[x][y];
    }
    
    public FieldType getField(Field field) {
        return fields[field.getX()][field.getY()];
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
    
    public void addRoad(Road road) {
        this.roads.add(road);
    }
}
