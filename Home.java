import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("=== Computer Graphics Programs ===");

        // Loop until user chooses to exit
        do {
            // Display menu
            displayMenu();

            // Read user input
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next(); // consume invalid input
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            // Execute corresponding algorithm
            switch (choice) {
                case 1 -> {
                    // Run DDA Line Drawing Algorithm
                    DDA_Line_Algorithm dda = new DDA_Line_Algorithm();
                    dda.DDA_Line_Alg();
                }
                case 2 -> {
                    // Run Midpoint Drawing Algorithm
                    MidPointDrawing mid = new MidPointDrawing();
                    mid.MidPointDrawingAlg();
                }
                case 3 -> {
                    // Run Cohen-Sutherland Line Clipping Algorithm
                    CohenSutherlandLineClipping clip = new CohenSutherlandLineClipping();
                    clip.lineClipping();
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println(); // Print a blank line for spacing

        } while (choice != 0);

        sc.close(); // Close the scanner
    }

    // Helper method to display menu
    private static void displayMenu() {
        System.out.println("1. DDA Line Drawing Algorithm");
        System.out.println("2. Mid Point Drawing Algorithm");
        System.out.println("3. Cohen-Sutherland Line Clipping");
        System.out.println("0. Exit");
    }
}
