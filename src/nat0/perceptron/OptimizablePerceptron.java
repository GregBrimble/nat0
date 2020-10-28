package nat0.perceptron;

import nat0.TrainingPoint;
import nat0.math.Vector;
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
  public double cost(Vector state) {
    double sum = 0.0;
    perceptron.weights.set(state);
    for (TrainingPoint point : trainingPoints) {
      sum += Math.abs(point.outputs.get(0).value - perceptron.predict(point.inputs));
    }
    return sum / trainingPoints.size();
  }

  @Override
  public Vector state() {
    return perceptron.weights;
  }
}
