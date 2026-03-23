//James Burlinson, this program tests credit card numbers with a mod-10 check, 3/18/22

package labs.example.arrays;

public class CreditCardValidator {

    // Test randomly generated card numbers
    public static void main(String[] args) {
        String[] testCards = {
            "4111111111111111", // Valid
            "4111111111111112", // Invalid
            "5555555555554444", // Valid Mastercard
            "5555555555554445"  // Invalid
        };

        for (String card : testCards) {
            boolean isValid = isValidCreditCard(card);
            System.out.println("Credit Card " + card + " is " + (isValid ? "" : "not ") + "valid.");
        }
    }

    public static boolean isValidCreditCard(String cardNumber) {
        cardNumber = cardNumber.replaceAll("[\\s-]", "");

        if (!cardNumber.matches("\\d+")) {
            return false;
        }

        // Implement Luhn algorithm
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = cardNumber.charAt(i) - '0';
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1; // or digit - 9
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }
}