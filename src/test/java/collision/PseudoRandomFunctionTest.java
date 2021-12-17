package collision;

import collision.functions.DoubleFunctionValue;
import collision.functions.PseudoRandomFunction;
import org.junit.Assert;
import org.junit.Test;

public class PseudoRandomFunctionTest {

    @Test
    public void testPseudoRandomFunctionCalculationWithoutParameters() {

        PseudoRandomFunction F = new PseudoRandomFunction() {
            @Override
            public DoubleFunctionValue calculate(DoubleFunctionValue V) {
                return new DoubleFunctionValue((V.value + 10) % 9, V.parameters);
            }
        };

        DoubleFunctionValue startValue = new DoubleFunctionValue(190, null);

        startValue = F.calculate(startValue);
        Assert.assertEquals(2, startValue.value, 0);
        Assert.assertEquals(null, startValue.parameters);

        startValue = F.calculate(startValue);
        Assert.assertEquals(3, startValue.value, 0);
        Assert.assertEquals(null, startValue.parameters);
    }

    @Test
    public void testPseudoRandomFunctionCalculationWithParameters() {

        PseudoRandomFunction F = new PseudoRandomFunction() {
            @Override
            public DoubleFunctionValue calculate(DoubleFunctionValue V) {
                return new DoubleFunctionValue((V.value * V.parameters[0] * V.parameters[1]) % 7, new double[]{V.parameters[0]+1, V.parameters[1]+2});
            }
        };

        DoubleFunctionValue startValue = new DoubleFunctionValue(97, new double[]{6, 4});

        startValue = F.calculate(startValue);
        Assert.assertEquals(4, startValue.value, 0);
        Assert.assertArrayEquals(new double[]{7, 6}, startValue.parameters, 0);

        startValue = F.calculate(startValue);
        Assert.assertEquals(0, startValue.value, 0);
        Assert.assertArrayEquals(new double[]{8, 8}, startValue.parameters, 0);
    }

    @Test
    public void testPseudoRandomFunctionRealCase() {
        PseudoRandomFunction F = new PseudoRandomFunction() {
            @Override
            public DoubleFunctionValue calculate(DoubleFunctionValue V) {
                if (V.value % 3 == 0) {
                    V.parameters[2]++;
                    return new DoubleFunctionValue((V.value * V.parameters[0]) % V.parameters[4], V.parameters);
                } else if (V.value % 3 == 1) {
                    V.parameters[3]++;
                    return new DoubleFunctionValue((V.value * V.parameters[1]) % V.parameters[4], V.parameters);
                } else {
                    V.parameters[2]++;
                    V.parameters[3]++;
                    return new DoubleFunctionValue((V.value * V.parameters[0] * V.parameters[1]) % V.parameters[4],
                            V.parameters);
                }
            }
        };

        DoubleFunctionValue startValue = new DoubleFunctionValue(7, new double[]{2, 12, 1, 1, 17});

        double[] expectedResultsValues = new double[]{16, 5, 1};
        double[][] expectedResultsParameters = new double[][]{{2, 12, 1, 2, 17}, {2, 12, 1, 3, 17}, {2, 12, 2, 4, 17}};

        for (int i = 0; i < expectedResultsParameters.length; i++) {
            startValue = F.calculate(startValue);

            Assert.assertEquals(expectedResultsValues[i], startValue.value, 0);
            Assert.assertArrayEquals(expectedResultsParameters[i], startValue.parameters, 0);
        }
    }
}