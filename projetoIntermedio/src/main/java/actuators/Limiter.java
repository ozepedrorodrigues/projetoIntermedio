package actuators;

public class Limiter implements Actuator{
    private Boolean state;
    private int min;
    private int max;
    public Limiter(int min, int maxim) {
        this.min = Math.min(min,maxim);
        this.max = Math.max(min,maxim);
        this.state = false;}
    public boolean isActive() {return this.state;}
    @Override
    public void activate() {
        this.state = true;
    }
    @Override
    public void deactivate() {
        this.state = false;
    }
}
