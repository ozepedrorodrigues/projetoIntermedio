package Domain;
import Factories.SensorFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Device {
    private String name;
    private String type;
    private boolean active;
    private List<Sensor> sensorList = new ArrayList<>();
    private HashSet<String> functionalityList = new HashSet<>();

    public Device(String name, String type) {
        if(name==null||type==null) throw new NullPointerException("Invalid Parameters");
        if (name.isEmpty() || type.isEmpty()) throw new IllegalArgumentException("Empty parameter(s)");
        this.name = name;
        this.type = type;
        this.active = true;
    }
    public String getName() {
        return this.name;}
    public String getType() {
        return this.type;}
    public Sensor addSensor(String sensorName, String typeOfSensor,Catalogue catalogue) {
        if (sensorName.isEmpty() || typeOfSensor.isEmpty()) return null;
        Sensor sensor = catalogue.getSensor(sensorName);
        if(sensor==null) return null;
        sensorList.add(sensor);
        if(typeOfSensor.strip().equalsIgnoreCase("TEMPERATURE"))
            functionalityList.add("TEMPERATURE");
        if(typeOfSensor.strip().equalsIgnoreCase("HUMIDITY"))
            functionalityList.add("HUMIDITY");
        return sensor;}
    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;}
        return false;}
    public List<Sensor> getDeviceSensors() {
        return new ArrayList<>(sensorList);}
    public List<String> getFunctionalities() {
        return new ArrayList<>(functionalityList);
    }
}