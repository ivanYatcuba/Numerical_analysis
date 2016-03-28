package app.dto;

public class PolinomialAnswer {

    private final double[] xi;
    private final double[] p;

    private final int size;

    private int currentSize = 0;

    private final String polinomName;

    private Double sigma;

    public PolinomialAnswer(int size, String polinomName) {
        this.size = size;
        this.xi = new double[size];
        this.p =  new double[size];
        this.polinomName = polinomName;
    }


    public void addAnswer(double xi, double p) {
        if(currentSize < size) {
            this.xi[currentSize] = xi;
            this.p[currentSize] = p;
            currentSize++;
        }
    }

    public String getPolinomName() {
        return polinomName;
    }

    public double getXiAt(int i) {
        return xi[i];
    }

    public double getPAt(int i) {
        return p[i];
    }

    public int getSize() {
        return size;
    }

    public Double getSigma() {
        return sigma;
    }

    public void setSigma(Double sigma) {
        this.sigma = sigma;
    }
}
