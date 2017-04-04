package pl.krawczyk.restaurant.controller;

import pl.krawczyk.restaurant.enums.FieldType;
import pl.krawczyk.restaurant.model.Field;
import pl.krawczyk.restaurant.model.Map;
import pl.krawczyk.restaurant.model.Restaurant;
import pl.krawczyk.restaurant.model.Road;

public class MapController {

    private static MapController mapController = null;

    private static Map map;

    public synchronized static MapController getInstance() {
        if (mapController == null) {
            map = new Map();
            mapController = new MapController();
        }
        return mapController;
    }

    private MapController() {
        clearMap();
        initRoads();
        initAddresses();
        initRestaurant();
    }

    private void clearMap() {
        for (int i = 0; i < Map.SIZE; ++i) {
            for (int j = 0; j < Map.SIZE; ++j) {
                map.setField(i, j, FieldType.EMPTY);
            }
        }
    }

    private void initRestaurant() {
        Restaurant restaurant = Restaurant.getInstance();
        map.setField(restaurant.getX(), restaurant.getY(), FieldType.RESTAURANT);
        map.setField(restaurant.getX(), restaurant.getY(), FieldType.RESTAURANT);
    }

    private void initRoads() {
        int centerX = Map.SIZE / 2;
        int centerY = Map.SIZE / 2;

        Road road;

        road = new Road();
        for (int i = centerX; i > 0; --i) {
            road.addField(i, centerY);
        }
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerX; i < Map.SIZE - 1; ++i) {
            road.addField(i, centerY);
        }
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerY; i > 0; --i) {
            road.addField(centerX, i);
        }
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerY; i < Map.SIZE - 1; ++i) {
            road.addField(centerX, i);
        }
        map.addRoad(road);
        
        
        
        road = new Road();
        for (int i = centerX; i < Map.SIZE - 8; ++i) {
            road.addField(i, centerY);
        }
        for (int i = centerY; i > 2; --i) {
            road.addField(Map.SIZE - 8, i);
        } 
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerX; i < Map.SIZE - 4; ++i) {
            road.addField(i, centerY);
        }
        for (int i = centerY; i < Map.SIZE - 3; ++i) {
            road.addField(Map.SIZE - 4, i);
        } 
        map.addRoad(road);
        
        
        
        road = new Road();
        for (int i = centerY; i > 9; --i) {
            road.addField(centerX, i);
        }
        for (int i = centerX - 1; i > 4; --i) {
            road.addField(i, centerY - 6);
        }
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerY; i > 3; --i) {
            road.addField(centerX, i);
        }
        for (int i = centerX - 1; i > 4; --i) {
            road.addField(i, centerY - 12);
        }
        map.addRoad(road);
        
        road = new Road();
        for (int i = centerY; i < Map.SIZE - 10; ++i) {
            road.addField(centerX, i);
        }
        for (int i = centerX - 1; i > 4; --i) {
            road.addField(i, Map.SIZE - 11);
        }
        map.addRoad(road);

        road = new Road();
        for (int i = centerY; i < Map.SIZE - 4; ++i) {
            road.addField(centerX, i);
        }
        for (int i = centerX - 1; i > 4; --i) {
            road.addField(i, Map.SIZE - 5);
        }
        map.addRoad(road);

        for (Road r : map.getRoads()) {
            for (Field f : r.getFields()) {
                map.setField(f.getX(), f.getY(), FieldType.ROAD);
            }
        }
    }

    public Map getMap() {
        return map;
    }

    private void initAddresses() {
        for (int i = 1; i < Map.SIZE - 1; ++i) {
            for (int j = 1; j < Map.SIZE - 1; ++j) {
                if (FieldType.ROAD.equals(map.getField(i, j))) {
                    if (FieldType.EMPTY.equals(map.getField(i - 1, j))) {
                        map.setField(i - 1, j, FieldType.ADDRESS);
                    }
                    if (FieldType.EMPTY.equals(map.getField(i + 1, j))) {
                        map.setField(i + 1, j, FieldType.ADDRESS);
                    }
                    if (FieldType.EMPTY.equals(map.getField(i, j - 1))) {
                        map.setField(i, j - 1, FieldType.ADDRESS);
                    }
                    if (FieldType.EMPTY.equals(map.getField(i, j + 1))) {
                        map.setField(i, j + 1, FieldType.ADDRESS);
                    }
                }
            }
        }
    }
}
