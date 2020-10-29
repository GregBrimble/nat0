package nat0.perceptron;

import nat0.math.NumberVector;

public class Perceptron {
  public final NumberVector weights;

  public Perceptron(int inputCount) {
    weights = new NumberVector(inputCount + 1);
  }

  public double sum(NumberVector input) {
    if (weights.size() != input.size() + 1)
      throw new IllegalArgumentException("Input of wrong size");
    double result = weights.values[0];
    for (int i = 0; i < input.size(); i++)
      result += weights.values[i + 1] * input.values[i];
    return result;
  }

  public double predict(NumberVector input) {
    return Math.tanh(sum(input)); // sum(input) > 0.0 ? 1.0 : -1.0;
  }
}
