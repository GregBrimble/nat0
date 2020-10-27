package nat0;

import java.util.ArrayList;
import nat0.ActivationFunction;
import nat0.Link;

public class Node {
  String id;
  ArrayList<Link> inputLinks = new ArrayList<Link>();
  double bias = 0.1;
  ArrayList<Link> outputs = new ArrayList<Link>();
  double totalInput;
  double output;
  double outputDer = 0;
  double inputDer = 0;
  double accInputDer = 0;
  double numAccumulatedDers = 0;
  ActivationFunction activation;

  public Node(String id, ActivationFunction activation) {
    this.id = id;
    this.activation = activation;
  }

  public Node(String id, ActivationFunction activation, boolean initZero) {
    this.id = id;
    this.activation = activation;
    if (initZero) {
      this.bias = 0;
    }
  }

  public double updateOutput() {
    this.totalInput = this.bias;
    for (Link link : this.inputLinks) {
      this.totalInput += link.weight * link.source.output;
    }
    this.output = this.activation.output(this.totalInput);
    return this.output;
  }
}