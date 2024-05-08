package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import java.util.Random;

public class RandomTrackingGenerator{
    public static String generateRandomAlphanumeric(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int choice = random.nextInt(2);
            if (choice == 0) {
                sb.append((char) (random.nextInt(26) + 'A'));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    public static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateRandomUppercase(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'A'));
        }
        return sb.toString();
    }
}