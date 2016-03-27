package app.service.linearsystem;


import app.service.linearsystem.calculator.CholeskyDecompositionCalculator;
import app.service.linearsystem.calculator.FixedPointIterationCalculator;
import app.service.linearsystem.calculator.GaussianEliminationCalculator;
import app.service.linearsystem.calculator.SquareRootAlgorithmsCalculator;

public class LinearSystemService {
    GaussianEliminationCalculator gaussianCalculator = new GaussianEliminationCalculator();
    CholeskyDecompositionCalculator choleskyCalculator = new CholeskyDecompositionCalculator();
    SquareRootAlgorithmsCalculator squareRootCalculator = new SquareRootAlgorithmsCalculator();
    FixedPointIterationCalculator fixedPointCalculator = new FixedPointIterationCalculator();


    public String gaussianElimination() {
        return gaussianCalculator.calculate();
    }

    public String CholeskyDecomposition() {
        return choleskyCalculator.calculate();
    }


    public String squareRootAlgorithms() {
        return squareRootCalculator.calculate();
    }

    public String fixedPointIteration() {
        return fixedPointCalculator.calculate();
    }
}
