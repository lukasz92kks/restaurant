package pl.krawczyk.restaurant.model;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.krawczyk.restaurant.controller.MapController;
import pl.krawczyk.restaurant.enums.FieldType;
import pl.krawczyk.restaurant.enums.VehicleType;

// klasa dostawcy
public class Deliverer extends DrawableThread {

    private String name;            // imię
    private String lastname;        // nazwisko
    private String pesel;           // PESEL
    private List<WorkTime> workTimes = new ArrayList<>();           // godziny pracy
    private List<VehicleType> vehicleTypes = new ArrayList<>();     // type obsługiwanych pojazdów
    private Vehicle vehicle;        // pojazd
    private Road road = null;       // trasa
    private Order order = null;     // zamówinie

    private final Random random = new Random();
    private final Restaurant restaurant = Restaurant.getInstance();
    private final MapController mapController = MapController.getInstance();

    public Deliverer(int x, int y) {
        super(x, y);
        VehicleType type = vehicleTypes.contains(VehicleType.CAR) ? VehicleType.CAR : VehicleType.SCOOTER;
        vehicle = new Car(random.nextInt(50) + 50, random.nextInt(100), "DW", Map.SIZE * 2, type);
        vehicle.setFuel(vehicle.getTankSize());
    }

    // głowna funkcja (pętla) wątku dostawcy
    @Override
    public void run() {
        while (isActive()) {
            try {
                // jeśli brak trasy, to weż kolejne zamówienie i znajdź trasę
                if (road == null) {
                    order = restaurant.popFirstOrder();
                    if (order != null) {
                        road = findRoadToTarget(order.getCustomer().getX(), order.getCustomer().getY());
                    }
                } else {    // idź do celu
                    move();

                    if (road != null) {
                        Field lastField = road.getFields().get(road.getFields().size() - 1);
                        // jeśli dostawca dostarczył zamówienie, to wraca do restauracji
                        if (getX() == lastField.getX() && getY() == lastField.getY()) {
                            if (order.getCustomer() != null) {
                                order.getCustomer().setOrder(null);
                            }
                            restaurant.removeOrder(order);
                            order = null;

                            road = reverseRoad();
                        }
                        // zawraca jeśli zgłoszono awarię
                        if (vehicle.isDamage() && order == null) {
                            road = reverseRoad();
                            vehicle.setDamage(false);
                        }
                    }
                }

                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(CompanyCustomer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // szuka drogi do celu
    private Road findRoadToTarget(int targetX, int targetY) {

        Road road = new Road();  // droga zawierająca docelowy adres
        Field lastField = null;  // pole na drodze graniczące z docelowym adresem

        // wyszukanie odpowiedniej drogi na mapie i ostatniego punktu, do którego musi dojść dostawca
        for (Road r : mapController.getMap().getRoads()) {
            for (Field f : r.getFields()) {
                if (f.getX() - 1 == targetX && f.getY() == targetY
                        || f.getX() + 1 == targetX && f.getY() == targetY
                        || f.getX() == targetX && f.getY() - 1 == targetY
                        || f.getX() == targetX && f.getY() + 1 == targetY) {

                    road = r;
                    lastField = f;
                    break;
                }
            }
        }

        // utworzenie drogi z restauracji do adresu docelowego
        Road resultRoad = new Road();
        for (Field f : road.getFields()) {
            resultRoad.addField(f);
            if (f.equals(lastField)) {
                break;
            }
        }
        return resultRoad;
    }

    // wykonuje ruch dostawcy
    private void move() {
        final Map map = mapController.getMap();

        Field currentField = new Field(getX(), getY()); // aktualna pozycja
        Field nextField = findNextField();              // następna pozycja

        // jeśli droga się nie skończyła, to idzie dalej
        if (nextField != null) {
            if (!FieldType.RESTAURANT.equals(map.getField(currentField))) {
                // czyści poprzednie pole
                map.setField(this.getX(), this.getY(), FieldType.ROAD);
            }
            if (!FieldType.RESTAURANT.equals(map.getField(nextField))) {
                // zajmuje następne pole
                setPosition(nextField.getX(), nextField.getY());
                map.setField(nextField, FieldType.DELIVERER);
            } else {
                // koniec drogi
                road = null;
                // tankuje paliwo
                vehicle.setFuel(vehicle.getTankSize());
                // usuwa ewentualne usterki
                vehicle.setDamage(false);
                // wraca do restauracji
                setPosition(nextField.getX(), nextField.getY());
            }
        }
        
        vehicle.setFuel(vehicle.getFuel() - 1);
    }

    private Field findNextField() {
        // odnalezienie kolejnego pola na drodze
        int roadLength = road.getFields().size();
        Field nextField = null;
        for (Field f : road.getFields()) {
            int currentFieldIndex = road.getFields().indexOf(f);
            // jeśli aktualne pole nie jest ostatnim na drodze
            if (f.getX() == this.getX() && f.getY() == this.getY()
                    && currentFieldIndex != roadLength - 1) {

                nextField = road.getFields().get(currentFieldIndex + 1); // następny ruch
            }
        }
        return nextField;
    }

    // odwrócenie drogi - zawracanie do restauracji
    private Road reverseRoad() {
        Road reversedRoad = new Road();
        List<Field> fields = Lists.reverse(road.getFields());
        reversedRoad.setFields(fields);
        return reversedRoad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(List<WorkTime> workTimes) {
        this.workTimes = workTimes;
    }

    public List<VehicleType> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
