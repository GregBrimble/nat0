package nat0;

import nat0.math.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingPoint {
  public final Vector inputs, outputs;

  TrainingPoint(Vector inputs, Vector outputs) {
    this.inputs = inputs;
    this.outputs = outputs;
  }

  public static List<TrainingPoint> makeGaussianDataset(Random random, int count, double center, double sdev) {
    ArrayList<TrainingPoint> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      Vector input = new Vector(2),
          output = new Vector(1);
      output.get(0).value = i < count / 2 ? -1 : 1;
      input.get(0).value = i < count / 2 ? -center : center;
      input.get(1).value = input.get(0).value;
      input.get(0).value += random.nextGaussian() * sdev;
      input.get(1).value += random.nextGaussian() * sdev;
      result.add(new TrainingPoint(input, output));
    }
    return result;
  }
}
