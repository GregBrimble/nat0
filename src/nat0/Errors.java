package nat0;

import java.lang.Math;
import nat0.ErrorFunction;

class SQUARE implements ErrorFunction {
  public double error(double output, double target) {
    return 0.5 * Math.pow(output - target, 2);
  }
  public double der(double output, double target) {
    return output - target;
  }
}

public class Errors {
  public static ErrorFunction SQUARE = new SQUARE();
}