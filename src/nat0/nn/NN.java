package nat0.nn;

import java.util.ArrayList;

public class NN {
  public ArrayList<ArrayList<Node>> network = new ArrayList<ArrayList<Node>>();

  public double forwardProp(int[] inputs) {
    ArrayList<Node> inputLayer = this.network.get(0);
    int numNodes = inputLayer.size();
    if (inputs.length != numNodes) {
      throw new Error("The number of inputs must match the number of nodes in the input layer");
    }
    for (int i = 0; i < numNodes; i++) {
      Node node = inputLayer.get(i);
      node.output = inputs[i];
    }
    for (ArrayList<Node> currentLayer : this.network) {
      for (Node node : currentLayer) {
        node.updateOutput();
      }
    }
    return this.network.get(this.network.size() - 1).get(0).output;
  }

  public void backProp(double target, ErrorFunction errorFunc) {
    int numLayers = this.network.size();
    Node outputNode = this.network.get(numLayers - 1).get(0);
    outputNode.outputDer = errorFunc.der(outputNode.output, target);

    for (int layerIdx = numLayers - 1; layerIdx >= 1; layerIdx--) {
      ArrayList<Node> currentLayer = network.get(layerIdx);
      for (Node node : currentLayer) {
        node.inputDer = node.outputDer * node.activation.der(node.totalInput);
        node.accInputDer += node.inputDer;
        node.numAccumulatedDers++;
      }

      for (Node node : currentLayer) {
        for (Link link : node.inputLinks) {
          if (link.isDead) {
            continue;
          }
          link.errorDer = node.inputDer * link.source.output;
          link.accErrorDer += link.errorDer;
          link.numAccumulatedDers++;
        }
      }

      if (layerIdx == 1) {
        continue;
      }

      ArrayList<Node> prevLayer = this.network.get(layerIdx - 1);

      for (Node node : prevLayer) {
        node.outputDer = 0;
        for (Link output : node.outputs) {
          node.outputDer += output.weight * output.dest.inputDer;
        }
      }
    }
  }

  public void updateWeights(double learningRate, double regularizationRate) {
    for (ArrayList<Node> currentLayer : this.network) {
      for (Node node : currentLayer) {
        if (node.numAccumulatedDers > 0) {
          node.bias -= learningRate * node.accInputDer / node.numAccumulatedDers;
          node.accInputDer = 0;
          node.numAccumulatedDers = 0;
        }

        for (Link link : node.inputLinks) {
          if (link.isDead) {
            continue;
          }

          double regulDer = link.regularization.der(link.weight);
          if (link.numAccumulatedDers > 0) {
            link.weight = link.weight - (learningRate / link.numAccumulatedDers) * link.accErrorDer;
            double newLinkWeight = link.weight - (learningRate * regularizationRate) * regulDer;
            if (link.regularization == Regularizations.L1 && link.weight * newLinkWeight < 0) {
              link.weight = 0;
              link.isDead = true;
            } else {
              link.weight = newLinkWeight;
            }
            link.accErrorDer = 0;
            link.numAccumulatedDers = 0;
          }
        }
      }
    }
  }

  public NN(int[] networkShape, ActivationFunction activation, ActivationFunction outputActivation, RegularizationFunction regularization, String[] inputIds) {
    int numLayers = networkShape.length;
    int id = 1;
    for (int layerIdx = 0; layerIdx < numLayers; layerIdx++) {
      boolean isOutputLayer = layerIdx == numLayers - 1;
      boolean isInputLayer = layerIdx == 0;
      ArrayList<Node> currentLayer = new ArrayList<Node>();
      this.network.add(currentLayer);
      int numNodes = networkShape[layerIdx];
      for (int i = 0; i < numNodes; i++) {
        String nodeId = String.valueOf(id);
        if (isInputLayer) {
          nodeId = inputIds[i];
        } else {
          id++;
        }
        Node node = new Node(nodeId, isOutputLayer ? outputActivation : activation);
        currentLayer.add(node);
        if (layerIdx >= 1) {
          ArrayList<Node> prevLayer = network.get(layerIdx - 1);
          int numNodesInPrevLayer = prevLayer.size();
          for (int j = 0; j < numNodesInPrevLayer; j++) {
            Node prevNode = prevLayer.get(j);
            Link link = new Link(prevNode, node, regularization);
            prevNode.outputs.add(link);
            node.inputLinks.add(link);
          }
        }
      }
    }
  }

  public NN(int[] networkShape, ActivationFunction activation, ActivationFunction outputActivation, RegularizationFunction regularization, String[] inputIds, boolean initZero) {
    int numLayers = networkShape.length;
    int id = 1;
    for (int layerIdx = 0; layerIdx < numLayers; layerIdx++) {
      boolean isOutputLayer = layerIdx == numLayers - 1;
      boolean isInputLayer = layerIdx == 0;
      ArrayList<Node> currentLayer = new ArrayList<Node>();
      this.network.add(currentLayer);
      int numNodes = networkShape[layerIdx];
      for (int i = 0; i < numNodes; i++) {
        String nodeId = String.valueOf(id);
        if (isInputLayer) {
          nodeId = inputIds[i];
        } else {
          id++;
        }
        Node node = new Node(nodeId, isOutputLayer ? outputActivation : activation, initZero);
        currentLayer.add(node);
        if (layerIdx >= 1) {
          ArrayList<Node> prevLayer = network.get(layerIdx - 1);
          int numNodesInPrevLayer = prevLayer.size();
          for (int j = 0; j < numNodesInPrevLayer; j++) {
            Node prevNode = prevLayer.get(j);
            Link link = new Link(prevNode, node, regularization, initZero);
            prevNode.outputs.add(link);
            node.inputLinks.add(link);
          }
        }
      }
    }
  }
}
