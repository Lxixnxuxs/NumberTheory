package collision;

import collision.detectors.FloydCollisionDetector;
import collision.functions.DoubleFunctionValue;
import collision.functions.PseudoRandomFunction;
import org.junit.Assert;
import org.junit.Test;

public class FloydCollisionDetectorTest {

    class PseudoDiscreteLogarithmFunction implements PseudoRandomFunction {
        @Override
        public DoubleFunctionValue calculate(DoubleFunctionValue V) {
            if ((V.value % 3) == 0) {
                V.parameters[2]++;
                return new DoubleFunctionValue((V.value * V.parameters[0]) % V.parameters[4], V.parameters);
            } else if ((V.value % 3) == 1) {
                V.parameters[3]++;
                return new DoubleFunctionValue((V.value * V.parameters[1]) % V.parameters[4], V.parameters);
            } else {
                V.parameters[2]*=2;
                V.parameters[3]*=2;
                return new DoubleFunctionValue((V.value * V.value) % V.parameters[4], V.parameters);
            }
        }
    }

    @Test
    public void testCollisionDetection() {
        PseudoDiscreteLogarithmFunction FDL = new PseudoDiscreteLogarithmFunction();

        FloydCollisionDetector collisionDetector = new FloydCollisionDetector(FDL, new DoubleFunctionValue(12, new double[]{5, 7, 1, 1, 23}));

        DoubleFunctionValue[] collisions = collisionDetector.findCollision();

        Assert.assertEquals(collisions[0].value, collisions[1].value, 0);
    }
}
