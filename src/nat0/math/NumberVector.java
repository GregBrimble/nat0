package nat0.math;

import java.util.*;

public class NumberVector implements Iterable<Double> {
  public final double[] values;

  public NumberVector(int size) {
    values = new double[size];
  }

  public NumberVector(NumberVector other) {
    values = Arrays.copyOf(other.values, other.values.length);
  }

  public NumberVector(List<Double> list) {
    values = new double[list.size()];
    for (int i = 0; i < values.length; i++)
      values[i] = list.get(i);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[ ");
    for (double num : values) {
      builder.append(num);
      builder.append(' ');
    }
    builder.append(']');
    return builder.toString();
  }

  public int size() {
    return values.length;
  }

  public void set(NumberVector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < values.length; i++)
      values[i] = other.values[i];
  }

  @Override
  public Iterator<Double> iterator() {
    return Arrays.stream(values).iterator();
  }

  public NumberVector add(NumberVector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < values.length; i++)
      values[i] += other.values[i];
    return this;
  }

  public NumberVector add(double other) {
    for (int i = 0; i < values.length; i++)
      values[i] += other;
    return this;
  }

  public NumberVector plus(double other) {
    return new NumberVector(this).add(other);
  }

  public NumberVector plus(NumberVector other) {
    return new NumberVector(this).add(other);
  }

  public NumberVector subtract(double other) {
    for (int i = 0; i < values.length; i++)
      values[i] -= other;
    return this;
  }

  public NumberVector subtract(NumberVector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < values.length; i++)
      values[i] -= other.values[i];
    return this;
  }

  public NumberVector minus(double other) {
    return new NumberVector(this).subtract(other);
  }

  public NumberVector minus(NumberVector other) {
    return new NumberVector(this).subtract(other);
  }

  public NumberVector multiply(NumberVector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < values.length; i++)
      values[i] *= other.values[i];
    return this;
  }

  public NumberVector times(NumberVector other) {
    return new NumberVector(this).multiply(other);
  }

  public NumberVector multiply(double other) {
    for (int i = 0; i < values.length; i++)
    values[i] *= other;
    return this;
  }

  public NumberVector times(double other) {
    return new NumberVector(this).multiply(other);
  }

  public double dot(NumberVector other) {
    double result = 0.0;
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < values.length; i++)
      result += values[i] * other.values[i];
    return result;
  }

  public static NumberVector uniform(Random random, int n) {
    NumberVector result = new NumberVector(n);
    for (int i = 0; i < result.values.length; i++)
      result.values[i] = random.nextDouble();
    return result;
  }
}
