package nat0.nn;

interface ErrorFunction {
  public double error(double output, double target);
  public double der(double output, double target);
}