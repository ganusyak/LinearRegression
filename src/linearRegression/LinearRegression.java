package linearRegression;
import java.io.*;

import java.util.ArrayList;
import java.util.List;


public final class LinearRegression implements OptimizationProblem {

    double[] theta;
    double[][] x;
    double[] y;
    int iterations;
    double alpha;
    ArrayPrinter ap = new ArrayPrinter();

    public LinearRegression (String fileName) throws IOException{
        try {
            readData(fileName);
            iterations = 1000;
            alpha = 0.003;
            learn();
        } catch (IOException e) {
            // handle error
        }
    }

    public int variablesCount() {
        return y.length;
    }

    public int observationsCount() {
        // implement later
        return 0;
    }

    public double evaluate(double[] theta) {
        return costFunction(theta);
    };

    //! Evaluates partial derivatives for given parameters
    public double[] derivatives(double[] theta) {
        double[]dTheta = new double[theta.length];

        for (int i = 0; i < x.length; i++) {
            double h = 0;
            for (int j = 0; j < theta.length; j++) {
                h += x[i][j] * theta[j];
            }
            for (int j = 0; j < theta.length; j++) {
                dTheta[j] = dTheta[j] + (h - y[i]) * x[i][j];
            }

        }
        for (int j = 0; j < theta.length; j++) {
            dTheta[j] *= alpha / x.length;
        }
        return dTheta;
    }

    //! Computes vector of residuals as a difference
    //! between observation and model-function value for given parameters
    public double[] residuals(double[] theta) {
        // implement later
        double[]dTheta = new double[theta.length];

        return dTheta;
    }

    public double costFunction(double[] theta) {
        double J = 0;
        for (int i = 0; i < x.length; i++) {
            J += (hTheta(x[i]) - y[i]) * (hTheta(x[i]) - y[i]);
        }
        J = J / (2 * x.length);
        return J;
    }


    double hTheta(double[] xi) {
        double h = 0;
        for (int j = 0; j < theta.length; j++) {
            h += xi[j] * theta[j];
        }
        return h;
    }

    private void updateTheta() {
        double[]dTheta = new double[theta.length];
        int thetaLength = theta.length;
        int xLength = x.length;

        for (int j = 0; j < thetaLength; j++) {

            double integral = 0;
            //System.out.println("h(x)= " + h);
            for (int i = 0; i < xLength; i++) {
                integral = integral + (hTheta(x[i]) - y[i]) * x[i][j];
            }
            dTheta[j] = integral * alpha / xLength;


        }


        //System.out.println(dTheta0 + "" + dTheta1);
        for (int j = 0; j < thetaLength; j++) {
            theta[j] -= dTheta[j] * alpha / xLength;
        }
    }

    void readData(String fileName) throws IOException {
        InputStream input = this.getClass().getResourceAsStream(fileName);
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);
        List<String> lines = new ArrayList<String>();
        String newLine = null;
        while ((newLine = reader.readLine()) != null) {
            lines.add(newLine);
            //log(line);
        }
        String firstLine = lines.get(0);
        String[] firstLineArguments = firstLine.split(",");
        x = new double[lines.size()][firstLineArguments.length];
        y = new double[lines.size()];
        theta = new double[firstLineArguments.length];
        for (double t : theta) {
            t = 0.0;
        }

        System.out.println("Theta array: ");
        for (double t : theta) {
            System.out.print(t + " ");
        }
        System.out.println();

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
        for (int i = 0; i < x.length; i++) {
            //x[i][2] *= 500;
        }
        ap.printArray(x);
        //ap.printArray(y);
    }

    public void learn() {

        for (int i = 0; i < iterations; i++) {
            updateTheta();
            //System.out.println(costFunction(theta));
            //ap.printArray(theta);
            //ap.printArray(derivatives(theta));
        }
        System.out.println("Variables: " + variablesCount());
        ap.printArray(derivatives(theta));
        updateTheta();
        System.out.println("Final theta");
        ap.printArray(theta);
        System.out.println("Final cost function: " + costFunction(theta));
    }

}
