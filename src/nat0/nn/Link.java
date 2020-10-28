package nat0.nn;

import java.util.Random;

public class Link {
  String id;
  Node source;
  Node dest;
  double weight = new Random().nextDouble() - 0.5;
  boolean isDead = false;
  double errorDer = 0;
  double accErrorDer = 0;
  double numAccumulatedDers = 0;
  RegularizationFunction regularization;

  public Link(Node source, Node dest, RegularizationFunction regularization) {
    this.id = source.id + '-' + dest.id;
    this.source = source;
    this.dest = dest;
    this.regularization = regularization;
  }

  public Link(Node source, Node dest, RegularizationFunction regularization, boolean initZero) {
    this.id = source.id + '-' + dest.id;
    this.source = source;
    this.dest = dest;
    this.regularization = regularization;
    if (initZero) {
      this.weight = 0;
    }
  }
}