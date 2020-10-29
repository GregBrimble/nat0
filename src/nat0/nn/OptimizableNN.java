package nat0.nn;

import nat0.TrainingPoint;
import nat0.math.NumberVector;
import nat0.pso.Optimizable;

import java.util.ArrayList;
import java.util.List;

public class OptimizableNN implements Optimizable {
  public final NN nn;
  public final List<TrainingPoint> trainingPoints;

  public OptimizableNN(NN nn, List<TrainingPoint> trainingPoints) {
    this.nn = nn;
    this.trainingPoints = trainingPoints;
  }

  @Override
  public double cost(NumberVector state) {
    int index = 0;
    for (ArrayList<Node> layer : nn.network) {
      for (Node node : layer) {
        for (Link link : node.inputLinks) {
          link.weight = state.values[index++];
        }
      }
    }
    double sse = 0.0;
    for (TrainingPoint point : trainingPoints) {
      nn.forwardProp(point.inputs.values);
      ArrayList<Double> outputs = new ArrayList<>();
      for (Node node : nn.network.get(nn.network.size() - 1))
        outputs.add(node.output);
      NumberVector error = new NumberVector(outputs).subtract(point.outputs);
      sse += error.dot(error);
    }
    return sse / trainingPoints.size();
  }

  @Override
  public NumberVector state() {
    ArrayList<Double> weigths = new ArrayList<>();
    for (ArrayList<Node> layer : nn.network) {
      for (Node node : layer) {
        for (Link link : node.inputLinks) {
          weigths.add(link.weight);
        }
      }
    }
    return new NumberVector(weigths);
  }
}
