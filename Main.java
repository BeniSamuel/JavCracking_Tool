import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Cracking System");

        // Configuring the scanner
        Scanner sc = new Scanner(System.in);

        // Get the hashed value from the user
        System.out.println("Enter the hashed value to crack (MD5): ");
        String hashedValue = sc.next();

        // Hardcoded array of possible characters in the password
        char[] letterValues = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '7', '8', '9', '0', '@'};
        String guessedValue = "";

        // Loop to build potential passwords
        for (char letter : letterValues) {
            guessedValue = guessedValue + letter;
            String guessedHash = hashMD5(guessedValue);

            // Print the guessed value and its hash for debugging
            System.out.println("Guessed Value: " + guessedValue + " | Hashed Value: " + guessedHash);

            // Check if the guessed hash matches the provided hash
            if (guessedHash.equals(hashedValue)) {
                System.out.println("Password cracked! The original value is: " + guessedValue);
                break;
            }
        }

        sc.close(); // Close scanner
    }

    // Helper method to hash a string using MD5
    public static String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
