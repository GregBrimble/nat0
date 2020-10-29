package nat0;

import nat0.math.NumberVector;

import java.lang.Math;
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

  public static List<TrainingPoint> makeSpiralDataset(Random random, int count, double noise) {
    ArrayList<TrainingPoint> points = new ArrayList<TrainingPoint>();
    double n = count / 2;
    
    for (int i = 0; i < count; i++) {
      double r = i / n * 5;
      double tPos = 1.75 * i / n * Math.PI;
      double tNeg = 1.75 * i / n * Math.PI + Math.PI;
      double xPos = r * Math.sin(tPos) + (random.nextDouble() * 2 - 1) * noise;
      double xNeg = r * Math.sin(tNeg) + (random.nextDouble() * 2 - 1) * noise;
      double yPos = r * Math.cos(tPos) + (random.nextDouble() * 2 - 1) * noise;
      double yNeg = r * Math.cos(tNeg) + (random.nextDouble() * 2 - 1) * noise;
      NumberVector posInput = new NumberVector(2);
      NumberVector negInput = new NumberVector(2);
      NumberVector posOutput = new NumberVector(1);
      NumberVector negOutput = new NumberVector(1);
      posInput.values[0] = xPos;
      posInput.values[1] = yPos;
      posOutput.values[0] = 1;
      negInput.values[0] = xNeg;
      negInput.values[1] = yNeg;
      negOutput.values[0] = -1;
      points.add(new TrainingPoint(posInput, posOutput));
      points.add(new TrainingPoint(negInput, negOutput));
    }
    
    return points;
  }
}
