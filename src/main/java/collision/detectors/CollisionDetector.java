package collision.detectors;

import collision.functions.DoubleFunctionValue;
import collision.functions.PseudoRandomFunction;

public class CollisionDetector {

    protected PseudoRandomFunction F;
    protected DoubleFunctionValue startValue;

    public CollisionDetector(PseudoRandomFunction F, DoubleFunctionValue startValue) {
        this.F = F;
        this.startValue = startValue;
    }

    public DoubleFunctionValue[] findCollision() {
        return null;
    }
}
