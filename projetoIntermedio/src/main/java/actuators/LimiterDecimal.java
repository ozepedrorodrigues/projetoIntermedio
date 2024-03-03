package actuators;

/**
 * The LimiterDecimal class represents an actuator that can set a decimal value within a certain range and with a predefined precision.
 * The value can be set by the user after the actuator is activated.
 */
public class LimiterDecimal implements Actuator {
   /**
     * The state of the actuator.
     */
    private boolean state;

    /**
     * The value of the actuator.
     */
    private double value;

    /**
     * The lower limit for the value.
     */
    private double lowerLimit;

    /**
     * The upper limit for the value.
     */
    private double upperLimit;

    /**
     * The precision of the value.
     */
    private int precision;

    /**
     * Constructs a new LimiterDecimal with the specified lower limit, upper limit, and precision.
     * @param lowerLimit The lower limit for the value.
     * @param upperLimit The upper limit for the value.
     * @param precision The precision of the value.
     */

    public LimiterDecimal(double lowerLimit, double upperLimit, int precision) {
        this.state = false;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.precision = precision;
    }

    /**
     * Returns whether the actuator is active.
     * @return true if the actuator is active, false otherwise.
     */
    @Override
    public boolean isActive() {
        return this.state;
    }

    /**
     * Activates the actuator.
     * @throws IllegalStateException if the actuator is already active.
     */
    public void activate() {
        if (this.state) {
            throw new IllegalStateException();
        }
        this.state = true;

    }
    /**
     * Deactivates the actuator.
     * @throws IllegalStateException if the actuator is not active.
     */
    @Override
    public void deactivate() {
        if (!this.state) {
            throw new IllegalStateException();
        }
        this.state = false;

    }}

//  private void setValue(double value) {
//        if (value < this.lowerLimit || value > this.upperLimit) {
//            throw new IllegalArgumentException();
//        }
//        this.value = Math.round(value * Math.pow(10, this.precision)) / Math.pow(10, this.precision);
//    }

//    public void setLowerLimit(double lowerLimit) {
//        this.lowerLimit = lowerLimit;
//    }
//
//    public void setUpperLimit(double upperLimit) {
//        this.upperLimit = upperLimit;
//    }
//
//    public void setPrecision(int precision) {
//        this.precision = precision;
//    }

//    public double getLowerLimit() {
//        return this.lowerLimit;
//    }
//
//    public double getUpperLimit() {
//        return this.upperLimit;
//    }
//
//    public int getPrecision() {
//        return this.precision;

