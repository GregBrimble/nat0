package nat0.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Vector implements Iterable<Number> {
  private final ArrayList<Number> values;

  public Vector(int size) {
    values = new ArrayList<>(size);
    for (int i = 0; i < size; i++)
      values.add(new Number());
  }

  public Vector(Vector other) {
    values = new ArrayList<>(other.size());
    for (Number num : other)
      values.add(new Number(num));
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[ ");
    for (Number num : values) {
      builder.append(num.value);
      builder.append(' ');
    }
    builder.append(']');
    return builder.toString();
  }

  public int size() {
    return values.size();
  }

  public Number get(int index) {
    return values.get(index);
  }

  double getDouble(int index) {
    return values.get(index).value;
  }

  public void set(Vector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < size(); i++)
      values.get(i).value = other.get(i).value;
  }

  public void set(int index, double value) {
    values.get(index).value = value;
  }

  public void set(int index, Number value) {
    values.get(index).value = value.value;
  }

  @Override
  public Iterator<Number> iterator() {
    return values.iterator();
  }

  public Vector add(Vector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < size(); i++)
      get(i).add(other.get(i));
    return this;
  }

  public Vector add(double other) {
    for (Number number : this)
      number.add(other);
    return this;
  }

  public Vector add(Number other) {
    for (Number number : this)
      number.add(other);
    return this;
  }

  public Vector plus(double other) {
    return new Vector(this).add(other);
  }

  public Vector plus(Number other) {
    return new Vector(this).add(other);
  }

  public Vector plus(Vector other) {
    return new Vector(this).add(other);
  }

  public Vector subtract(double other) {
    for (Number number : this)
      number.subtract(other);
    return this;
  }

  public Vector subtract(Number other) {
    for (Number number : this)
      number.subtract(other);
    return this;
  }

  public Vector subtract(Vector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < size(); i++)
      get(i).subtract(other.get(i));
    return this;
  }

  public Vector minus(double other) {
    return new Vector(this).subtract(other);
  }

  public Vector minus(Number other) {
    return new Vector(this).subtract(other);
  }

  public Vector minus(Vector other) {
    return new Vector(this).subtract(other);
  }

  public Vector multiply(Vector other) {
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < size(); i++)
      get(i).multiply(other.get(i));
    return this;
  }

  public Vector times(Vector other) {
    return new Vector(this).multiply(other);
  }

  public Vector multiply(double other) {
    for (Number number : this)
      number.multiply(other);
    return this;
  }

  public Vector multiply(Number other) {
    for (Number number : this)
      number.multiply(other);
    return this;
  }

  public Vector times(double other) {
    return new Vector(this).multiply(other);
  }

  public Vector times(Number other) {
    return new Vector(this).multiply(other);
  }

  public Number dot(Vector other) {
    Number result = new Number();
    if (size() != other.size())
      throw new IllegalArgumentException();
    for (int i = 0; i < size(); i++)
      result.value += getDouble(i) * other.getDouble(i);
    return result;
  }

  public static Vector uniform(Random random, int n) {
    Vector result = new Vector(n);
    for (Number num : result)
      num.value = random.nextDouble();
    return result;
  }
}
