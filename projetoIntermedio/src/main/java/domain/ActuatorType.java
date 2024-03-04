package domain;

import java.util.ArrayList;
import java.util.List;
public enum ActuatorType {
    BLINDSMANAGER("Blinders", new ArrayList<String>() {
        {add("OpenBlinders");
        add("CloseBlinders");}}),
    LIMITER("Limiter", new ArrayList<String>() {
        {add("SetMinimum");
        add("SetMaximum");}}),
    LIMITER_DECIMAL("LimiterDecimal", new ArrayList<String>() {
        {add("SetMinimum");
        add("SetMaximum");
        add("SetPrecision");}}),
    ONOFFSWITCH("OnOffSwitch", new ArrayList<String>() {
        {add("TurnOn");
        add("TurnOff");}});
    private final String name;
    private final List<String> commandList;
    ActuatorType(String name, List<String> commandList) {
        this.name = name;
        this.commandList = commandList;}
}
