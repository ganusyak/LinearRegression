package linearRegression;

public final class LinearRegression {

    private Problem problem;
    private double[] theta;
    private int iterations;
    private double alpha;
    private ArrayPrinter ap = new ArrayPrinter();

    public LinearRegression (Problem problem, int iterations){
        this.problem = problem;
        this.iterations = iterations;
        theta = new double[problem.variablesCount()];


    }

    private void updateTheta() {
        double[]dTheta = problem.derivatives(theta);
        for (int j = 0; j < theta.length; j++) {
            theta[j] -= dTheta[j] * alpha;
        }
    }

    public void learn() {
        theta = new double[problem.variablesCount()];
        alpha = optimalAlpha();
        for (int i = 0; i < iterations; i++) {
            updateTheta();
        }
    }


    public double[] getTheta() {
        return theta;
    }

    private double optimalAlpha () {
        double a = 1;
        while (!converge(a)) {
            a = a / 3;
        }
        return a;
    }

    private boolean converge (double alpha) {
        theta = new double[problem.variablesCount()];
        double initialCost = problem.evaluate(theta);
        this.alpha = alpha;
        for (int i = 0; i < 10; i++) {
            updateTheta();
        }

        if (problem.evaluate(theta) < initialCost) {
            return true;
        } else {
            return false;
        }
    }



}
