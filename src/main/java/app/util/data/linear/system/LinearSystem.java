package app.util.data.linear.system;

public interface LinearSystem {

    double[] newInstanceFMatrix();

    double[][] newInstanceAMatrix();

    int getId();

    String toString();
}
