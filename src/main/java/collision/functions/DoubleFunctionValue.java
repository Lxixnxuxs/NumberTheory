package collision.functions;

import java.util.Arrays;

public class DoubleFunctionValue {

    public double value;
    public double[] parameters;

    public DoubleFunctionValue(double value, double[] parameters) {
        this.value = value;
        this.parameters = parameters;
    }

    public DoubleFunctionValue(DoubleFunctionValue other) {
        this.value = other.value;
        this.parameters = Arrays.copyOf(other.parameters, other.parameters.length);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DoubleFunctionValue) {
            return ((DoubleFunctionValue) other).value == this.value;
        } else {
            return false;
        }
    }
}
