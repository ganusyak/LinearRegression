package linearRegression;

import java.io.IOException;

public final class TestClient {
    public static void main(String[] args) {

        try {
            ArrayPrinter ap = new ArrayPrinter();

            Problem problem = new Problem("input.txt");
            double[] theta = new double[problem.variablesCount()];
            ap.printArray(problem.derivatives(theta));
            System.out.println("Cost function = " + problem.evaluate(theta));
            LinearRegression lr = new LinearRegression(problem, 100000);
            lr.learn();
            ap.printArray(lr.getTheta());
            ap.printArray(problem.derivatives(lr.getTheta()));
        } catch (IOException e) {
            // handle error
        }
    }
}