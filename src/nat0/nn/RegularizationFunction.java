package nat0.nn;

interface RegularizationFunction {
  public double output(double weight);
  public double der(double weight);
}