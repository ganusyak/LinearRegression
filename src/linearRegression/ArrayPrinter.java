package linearRegression;

/**
 * Created by yuriyganusyak on 10/26/15.
 */
public class ArrayPrinter {
    public static void printArray(double[][] array) {
        for (double[] n : array) {
            for (double item: n) {
                System.out.print(item + " ");
            }
            System.out.println("");
        }
    }

    public static void printArray(double[] array) {
        for (double n : array) {
            System.out.print(n + " ");
        }
        System.out.println("");
    }
}
