package nat0.nn;

interface ActivationFunction {
  public double output(double input);
  public double der(double input);
}