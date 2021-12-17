package collision.functions;

import java.util.Arrays;

/**
 * Defines a function value representing the input (and output) to (of) a PseudoRandomFunction
 * The value field represents the actual value of the FunctionValue
 * The parameters fields defines a set of parameters that "led" to the current value
 * @param <T> Type of the value
 */
public class FunctionValue<T> {

    public T value;
    public T[] parameters;

    public FunctionValue(T value, T[] parameters) {
        this.value = value;
        this.parameters = parameters;
    }

    public FunctionValue(FunctionValue<T> other) {
        this.value = other.value;
        this.parameters = Arrays.copyOf(other.parameters, other.parameters.length);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FunctionValue) {
            return ((FunctionValue<?>) o).value.equals(this.value);
        } else {
            return false;
        }
    }
}
