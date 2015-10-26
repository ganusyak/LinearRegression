package linearRegression;

/**
 * Created by yuriyganusyak on 10/26/15.
 */
public interface OptimizationProblem {
    //! Returns the number of variables in this problem
    int variablesCount();

    //! Returns number of observations for given problem
    int observationsCount();

    //! Evaluates cost-function value for given parameters
    double evaluate(double[] x);

    //! Evaluates partial derivatives for given parameters
    double[] derivatives(double[] x);

    //! Computes vector of residuals as a difference
    //! between observation and model-function value for given parameters
    double[] residuals(double[] x);
}
