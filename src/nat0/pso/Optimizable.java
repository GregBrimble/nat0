package nat0.pso;

import nat0.math.NumberVector;

public interface Optimizable {
  double cost(NumberVector state);

  NumberVector state();
}
