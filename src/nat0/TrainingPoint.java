package nat0;

import nat0.math.NumberVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingPoint {
  public final NumberVector inputs, outputs;

  TrainingPoint(NumberVector inputs, NumberVector outputs) {
    this.inputs = inputs;
    this.outputs = outputs;
  }

  public static List<TrainingPoint> makeGaussianDataset(Random random, int count, double center, double sdev) {
    ArrayList<TrainingPoint> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      NumberVector input = new NumberVector(2),
          output = new NumberVector(1);
      output.values[0] = i < count / 2 ? -1 : 1;
      input.values[0] = i < count / 2 ? -center : center;
      input.values[1] = input.values[0];
      input.values[0] += random.nextGaussian() * sdev;
      input.values[1] += random.nextGaussian() * sdev;
      result.add(new TrainingPoint(input, output));
    }
    return result;
  }
}
