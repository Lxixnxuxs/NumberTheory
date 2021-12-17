package collision.detectors;

import collision.functions.DoubleFunctionValue;
import collision.functions.PseudoRandomFunction;

public class FloydCollisionDetector extends CollisionDetector {

    public FloydCollisionDetector(PseudoRandomFunction F, DoubleFunctionValue startValue) {
        super(F, startValue);
    }

    public DoubleFunctionValue singeCalculationStep(DoubleFunctionValue value) {
        return F.calculate(value);
    }

    public DoubleFunctionValue doubleCalculationStep(DoubleFunctionValue value) {
        return F.calculate(F.calculate(value));
    }

    @Override
    public DoubleFunctionValue[] findCollision() {
        DoubleFunctionValue tortoise = new DoubleFunctionValue(startValue), hare = new DoubleFunctionValue(startValue);

        do {
            tortoise = singeCalculationStep(tortoise);
            hare = doubleCalculationStep(hare);
        } while (!tortoise.equals(hare));

        return new DoubleFunctionValue[] {tortoise, hare};
    }
}
