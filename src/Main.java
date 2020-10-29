import nat0.TrainingPoint;
import nat0.nn.*;
import nat0.perceptron.*;
import nat0.pso.*;
import java.util.Random;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    NN nn = new NN(new int[] {1, 2, 3, 1}, Activations.TANH, Activations.TANH, Regularizations.L1, new String[] {"test"});
    List<TrainingPoint> list = TrainingPoint.makeSpiralDataset(new Random(), 50, 0.0);
    for (TrainingPoint tp : list) {
      System.out.println(tp.inputs);
      System.out.println(tp.outputs);
    }

    Perceptron perceptron = new Perceptron(2);
    Optimizable optimizable = new OptimizablePerceptron(
        perceptron, TrainingPoint.makeGaussianDataset(new Random(), 50, 2.0, 0.5));
    ParticleSwarmOptimizer pso = new ParticleSwarmOptimizer(
        optimizable, 0.7, 2.0, 2.0);
    pso.initParticles(20);
    for (int i = 0; i < 10; i++) {
      pso.update();
      System.out.println(pso.getGlobalBestCost());
    }
  }
}