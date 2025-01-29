import java.util.Scanner;

public class Theater {
    private static final int ROWS = 30; // Number of rows
    private static final int COLUMNS = 12; // Number of columns
    private boolean[][] seats; // 2D array representing the seating arrangement

    // Constructor: Initializes all seats as non-booked (false)
    public Theater() {
        seats = new boolean[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seats[i][j] = false; // All seats are initially not booked
            }
        }
    }

    // Method to book a seat
    public void book(char column, int row) {
        int colIndex = column - 'A'; // Convert column letter to index (0-based)
        int rowIndex = row - 1; // Convert row number to index (0-based)

        if (isValidSeat(column, row)) {
            if (!seats[rowIndex][colIndex]) {
                seats[rowIndex][colIndex] = true; // Mark the seat as booked
                System.out.printf("Seat %c%d has been successfully booked.%n", column, row);
            } else {
                System.out.printf("Seat %c%d is already booked.%n", column, row);
            }
        } else {
            System.out.println("Invalid seat! Please enter a valid seat (e.g., A1, B2).\n");
        }
    }

    // Method to cancel a booking
    public void cancel(char column, int row) {
        int colIndex = column - 'A'; // Convert column letter to index (0-based)
        int rowIndex = row - 1; // Convert row number to index (0-based)

        if (isValidSeat(column, row)) {
            if (seats[rowIndex][colIndex]) {
                seats[rowIndex][colIndex] = false; // Mark the seat as non-booked
                System.out.printf("Seat %c%d has been successfully canceled.%n", column, row);
            } else {
                System.out.printf("Seat %c%d is not booked yet.%n", column, row);
            }
        } else {
            System.out.println("Invalid seat! Please enter a valid seat (e.g., A1, B2).\n");
        }
    }

    // Method to display the seating chart
    public void displaySeating() {
        System.out.println("Theater Seating Chart:");
        System.out.print("   ");
        for (char c = 'A'; c < 'A' + COLUMNS; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print((seats[i][j] ? "X " : "O "));
            }
            System.out.println();
        }
    }

    // Helper method to validate a seat
    private boolean isValidSeat(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;
        return colIndex >= 0 && colIndex < COLUMNS && rowIndex >= 0 && rowIndex < ROWS;
    }

    public static void main(String[] args) {
        Theater theater = new Theater();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            theater.displaySeating();
            System.out.println("Choose an option:");
            System.out.println("1. Book a seat");
            System.out.println("2. Cancel a booking");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the seat to book (e.g., A1): ");
                        char bookColumn = scanner.next().toUpperCase().charAt(0);
                        int bookRow = scanner.nextInt();
                        theater.book(bookColumn, bookRow);
                        break;
                    case 2:
                        System.out.print("Enter the seat to cancel (e.g., A1): ");
                        char cancelColumn = scanner.next().toUpperCase().charAt(0);
                        int cancelRow = scanner.nextInt();
                        if (theater.isValidSeat(cancelColumn, cancelRow) && theater.seats[cancelRow - 1][cancelColumn - 'A']) {
                            theater.cancel(cancelColumn, cancelRow);
                        } else {
                            System.out.printf("Seat %c%d is not booked yet.%n", cancelColumn, cancelRow);
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Please select a valid option.");
                }
            } else {
                System.out.println("Please select a valid option.");
                scanner.next(); // Clear invalid input
            }
        }

        scanner.close();
        System.out.println("Thank you for using the theater booking system.");
    }
}
