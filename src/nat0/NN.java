package nat0;

import java.util.ArrayList;
import nat0.Node;
import nat0.Activations;

public class NN {
  public ArrayList<ArrayList<Node>> network = new ArrayList<ArrayList<Node>>();

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
