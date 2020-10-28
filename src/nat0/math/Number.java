package nat0.math;

public class Number {
  public double value;

  public Number() { value = 0.0; }

  public Number(double value) { this.value = value; }

  public Number(Number value) { this.value = value.value; }

  public Number add(double other) {
    value += other;
    return this;
  }

  public Number add(Number other) {
    value += other.value;
    return this;
  }

  public Number plus(double other) {
    return new Number(value + other);
  }

  public Number plus(Number other) {
    return new Number(value + other.value);
  }

  public Number subtract(double other) {
    value -= other;
    return this;
  }

  public Number subtract(Number other) {
    value -= other.value;
    return this;
  }

  public Number minus(double other) {
    return new Number(value - other);
  }

  public Number minus(Number other) {
    return new Number(value - other.value);
  }

  public Number multiply(double other) {
    value *= other;
    return this;
  }

  public Number multiply(Number other) {
    value *= other.value;
    return this;
  }

  public Number times(double other) {
    return new Number(value * other);
  }

  public Number times(Number other) {
    return new Number(value * other.value);
  }

  @Override
  public Object clone() {
    return new Number(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Number)
      return ((Number) o).value == value;
    if (o instanceof Double)
      return ((Double) o) == value;
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(value);
  }

  @Override
  public String toString() {
    return Double.toString(value);
  }
}
