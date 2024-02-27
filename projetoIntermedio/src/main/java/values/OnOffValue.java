package values;

import values.Value;

public class OnOffValue implements Value {

    private int onOffValue;

    public OnOffValue() {
        this.onOffValue = onOffValue;}

    public int getOnOffValue() {
        return onOffValue;}

    @Override
    public String valueToString() {
        String value = "On";
        if (onOffValue == 0) {value = "Off";}
        return "OnOffValue{" +
                "value=" + value +
                '}';}

}
