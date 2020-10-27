package nat0;

interface RegularizationFunction {
  public double output(double weight);
  public double der(double weight);
}