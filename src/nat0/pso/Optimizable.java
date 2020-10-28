package nat0.pso;

import nat0.math.Vector;

public interface Optimizable {
  double cost(Vector state);

  Vector state();
}
