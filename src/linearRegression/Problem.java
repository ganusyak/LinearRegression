package linearRegression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriyganusyak on 10/28/15.
 */
public class Problem implements OptimizationProblem {

    private double[][] x;
    private double[] y;
    private ArrayPrinter ap = new ArrayPrinter();

    public Problem (String fileName) throws IOException{
        try {
            readData(fileName);
        } catch (IOException e) {
            // handle error
        }
    }

    public double[] residuals(double[] x) {
        //implement later
        return new double[0];
    }

    public double[] derivatives(double[] theta) {
        double[]dTheta = new double[theta.length];

        for (int j=0; j < theta.length; j++) {

            for (int i = 0; i < x.length; i++) {
                dTheta[j] = dTheta[j] + (hTheta(x[i], theta) - y[i]) * x[i][j] / x.length;
            }

        }

        return dTheta;
    }

    public int variablesCount() {
        return x[0].length;
    }

    public int observationsCount() {
        return x.length;
    }

    private double hTheta(double[] xi, double[] theta) {
        double h = 0;
        for (int j = 0; j < theta.length; j++) {
            h += xi[j] * theta[j];
        }
        return h;
    }

    public double evaluate(double[] theta) {
        double J = 0;
        for (int i = 0; i < x.length; i++) {
            J += (hTheta(x[i], theta) - y[i]) * (hTheta(x[i], theta) - y[i]);
        }
        J = J / (2 * x.length);
        return J;
    }

    private void readData(String fileName) throws IOException {
        InputStream input = this.getClass().getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        List<String> lines = new ArrayList<>();

        String newLine;
        while ((newLine = reader.readLine()) != null) {
            lines.add(newLine);
        }

        String firstLine = lines.get(0);
        String[] firstLineArguments = firstLine.split(",");
        x = new double[lines.size()][firstLineArguments.length];
        y = new double[lines.size()];

        int index = 0;
        for (String line : lines) {
            String[] arguments = line.split(",");
            x[index][0] = 1.0;
            for (int j = 1; j < arguments.length; j++) {
                x[index][j] = Double.parseDouble(arguments[j - 1]);
            }
            y[index] = Double.parseDouble(arguments[arguments.length - 1]);
            index++;
        }

        //ap.printArray(x);
    }
}
