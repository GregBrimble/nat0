import nat0.NN;
import nat0.Activations;
import nat0.Regularizations;

public class Main {
  public static void main(String[] args) {
    NN nn = new NN(new int[] {1, 2, 3, 1}, Activations.TANH, Activations.TANH, Regularizations.L1, new String[] {"test"});
  }
}