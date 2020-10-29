package nat0.perceptron;

import nat0.TrainingPoint;
import nat0.math.NumberVector;
import nat0.pso.Optimizable;

import java.util.List;

public class OptimizablePerceptron implements Optimizable {
  public final Perceptron perceptron;
  public final List<TrainingPoint> trainingPoints;

  public OptimizablePerceptron(Perceptron perceptron, List<TrainingPoint> trainingPoints) {
    this.perceptron = perceptron;
    this.trainingPoints = trainingPoints;
  }

  @Override
  public double cost(NumberVector state) {
    double sum = 0.0;
    perceptron.weights.set(state);
    for (TrainingPoint point : trainingPoints) {
      sum += Math.abs(point.outputs.values[0] - perceptron.predict(point.inputs));
    }
    return sum / trainingPoints.size();
  }

  @Override
  public NumberVector state() {
    return perceptron.weights;
  }
}
