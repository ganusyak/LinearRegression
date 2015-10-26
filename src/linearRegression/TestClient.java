package linearRegression;

import java.io.IOException;

public final class TestClient {
    public static void main(String[] args) {

        try {
            LinearRegression lr = new LinearRegression("input.txt");
        } catch (IOException e) {

        }
    }
}