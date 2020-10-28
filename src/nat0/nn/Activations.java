package nat0.nn;

import java.lang.Math;

class TANH implements ActivationFunction {
  public double output(double input) {
    return Math.tanh(input);
  }
  public double der(double input) {
    double output = Activations.TANH.output(input);
    return 1 - output * output;
  }
}

class RELU implements ActivationFunction {
  public double output(double input) {
    return Math.max(0, input);
  }
  public double der(double input) {
    return input <= 0 ? 0 : 1;
  }
}

class SIGMOID implements ActivationFunction {
  public double output(double input) {
    return 1 / (1 + Math.exp(-input));
  }
  public double der(double input) {
    double output = Activations.SIGMOID.output(input);
    return output * (1 - output);
  }
}

class LINEAR implements ActivationFunction {
  public double output(double input) {
    return input;
  }
  public double der(double input) {
    return 1;
  }
}

public class Activations {
  public static ActivationFunction TANH = new TANH();
  public static ActivationFunction RELU = new RELU();
  public static ActivationFunction SIGMOID = new SIGMOID();
  public static ActivationFunction LINEAR = new LINEAR();
}