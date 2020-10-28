package nat0.perceptron;

import nat0.math.Vector;

public class Perceptron {
  public final Vector weights;

  public Perceptron(int inputCount) {
    weights = new Vector(inputCount + 1);
  }

  public double sum(Vector input) {
    if (weights.size() != input.size() + 1)
      throw new IllegalArgumentException("Input of wrong size");
    double result = weights.get(0).value;
    for (int i = 0; i < input.size(); i++)
      result += weights.get(i + 1).value * input.get(i).value;
    return result;
  }

  public double predict(Vector input) {
    return Math.tanh(sum(input)); // sum(input) > 0.0 ? 1.0 : -1.0;
  }
}
