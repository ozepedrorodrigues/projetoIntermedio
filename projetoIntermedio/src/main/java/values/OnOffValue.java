package values;


public class OnOffValue implements Value {

    private boolean onOffValue;

    public OnOffValue() {
    }

    public boolean getOnOffValue() {
        return onOffValue;
    }

    @Override
    public String valueToString() {
        return String.valueOf(onOffValue);
    }

}
