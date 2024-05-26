package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import java.security.SecureRandom;

public class RandomTrackingGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomAlphanumeric(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int choice = secureRandom.nextInt(2);
            if (choice == 0) {
                sb.append((char) (secureRandom.nextInt(26) + 'A'));
            } else {
                sb.append(secureRandom.nextInt(10));
            }
        }
        return sb.toString();
    }

    public static String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateRandomUppercase(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (secureRandom.nextInt(26) + 'A'));
        }
        return sb.toString();
    }
}
