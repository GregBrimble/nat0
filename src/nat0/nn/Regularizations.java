package nat0.nn;

import java.lang.Math;

class L1 implements RegularizationFunction {
  public double output(double weight) {
    return Math.abs(weight);
  }
  public double der(double weight) {
    return weight < 0 ? -1 : (weight > 0 ? 1 : 0);
  }
}

class L2 implements RegularizationFunction {
  public double output(double weight) {
    return 0.5 * weight * weight;
  }
  public double der(double weight) {
    return weight;
  }
}

public class Regularizations {
  public static RegularizationFunction L1 = new L1();
  public static RegularizationFunction L2 = new L2();
}