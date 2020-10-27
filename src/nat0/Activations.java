package nat0;

import java.lang.Math;
import nat0.ActivationFunction;

class TANH implements ActivationFunction {
  public double output(double input) {
    return Math.tanh(input);
  }
  public double der(double input) {
    double output = Activations.TANH.output(input);
    return 1 - output * output;
  }
}

public class Activations {
  public static ActivationFunction TANH = new TANH();
  // TODO: RELU
  // TODO: SIGMOID
  // TODO: LINEAR
}