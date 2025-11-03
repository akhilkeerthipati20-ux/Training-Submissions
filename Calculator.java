import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Basic Calculator ===");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter operation (add, sub, multiply, divide): ");
        String operation = scanner.next().toLowerCase();  // normalize input

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        double result;

        switch (operation) {
            case "add" -> {
                result = num1 + num2;
                System.out.println("Result: " + result);
            }

            case "sub" -> {
                result = num1 - num2;
                System.out.println("Result: " + result);
            }

            case "multiply" -> {
                result = num1 * num2;
                System.out.println("Result: " + result);
            }

            case "divide" -> {
                if (num2 != 0) {
                    result = num1 / num2;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                }
            }

            default -> System.out.println("Error: Invalid operation. Please use add, sub, multiply, or divide.");
        }

        scanner.close();
    }
}
