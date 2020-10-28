package nat0.pso;

import nat0.math.Number;
import nat0.math.Vector;
import java.util.ArrayList;
import java.util.Random;

public class ParticleSwarmOptimizer {
  private final class Particle {
    private double localBestCost = Double.POSITIVE_INFINITY;
    public final Vector localBest;
    public final Vector x, v;

    public Particle(Vector x) {
      this.x = x;
      localBest = new Vector(x);
      v = new Vector(x.size());
    }

    public void update(Vector r1, Vector r2) {
      v.multiply(omega)
          .add(r1.times(alpha1).multiply(localBest.minus(x)))
          .add(r2.times(alpha2).multiply(globalBest.minus(x)));
      x.add(v);
      final double cost = optimizable.cost(x);
      if (cost < localBestCost) {
        localBestCost = cost;
        localBest.set(x);
      }
      if (cost < globalBestCost) {
        globalBestCost = cost;
        globalBest.set(x);
      }
    }
  }

  private final Optimizable optimizable;
  private final ArrayList<Particle> particles;
  private final Vector globalBest;
  private double globalBestCost = Double.POSITIVE_INFINITY;
  public final Random random; // public so that it can be seeded from outside

  private final double omega, alpha1, alpha2;

  public ParticleSwarmOptimizer(Optimizable optimizable, double omega, double alpha1, double alpha2) {
    this.optimizable = optimizable;
    this.omega = omega;
    this.alpha1 = alpha1;
    this.alpha2 = alpha2;
    globalBest = new Vector(optimizable.state());
    particles = new ArrayList<>();
    random = new Random();
  }

  public Vector getGlobalBest() { return new Vector(globalBest); }

  public double getGlobalBestCost() { return globalBestCost; }

  public void initParticles(int n) {
    particles.clear();
    final int size = globalBest.size();
    for (int i = 0; i < n; i++)
      particles.add(new Particle(Vector.uniform(random, size)));
  }

  public void update() {
    for (Particle particle : particles) {
      Vector r1 = Vector.uniform(random, particle.v.size()),
          r2 = Vector.uniform(random, particle.v.size());
      particle.update(r1, r2);
    }
  }
}
