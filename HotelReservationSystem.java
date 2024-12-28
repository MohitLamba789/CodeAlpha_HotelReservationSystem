
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    // Class to represent a hotel room
    static class Room {
        private int roomNumber;
        private String category;
        private boolean isAvailable;
        private double price;

        // Constructor to initialize the room
        public Room(int roomNumber, String category, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.isAvailable = true; // Room is available initially
        }

        // Method to display room details
        public void displayRoomDetails() {
            System.out.println("Room Number: " + roomNumber);
            System.out.println("Category: " + category);
            System.out.println("Price: $" + price);
            System.out.println("Availability: " + (isAvailable ? "Available" : "Booked"));
            System.out.println();
        }

        // Method to book the room
        public boolean bookRoom() {
            if (isAvailable) {
                isAvailable = false; // Room is now booked
                return true;
            }
            return false;
        }

        // Method to check if the room is available
        public boolean isAvailable() {
            return isAvailable;
        }

        // Getter for room price
        public double getPrice() {
            return price;
        }

        // Getter for room number
        public int getRoomNumber() {
            return roomNumber;
        }
    }

    // Class to represent a reservation
    static class Reservation {
        private String guestName;
        private Room room;
        private double totalPrice;

        // Constructor to create a reservation
        public Reservation(String guestName, Room room) {
            this.guestName = guestName;
            this.room = room;
            this.totalPrice = room.getPrice(); // Set the total price for the reservation
        }

        // Method to display reservation details
        public void displayReservationDetails() {
            System.out.println("\nBooking Details:");
            System.out.println("Guest Name: " + guestName);
            room.displayRoomDetails();
            System.out.println("Total Price: $" + totalPrice);
        }

        // Method to process payment
        public boolean processPayment(double paymentAmount) {
            if (paymentAmount >= totalPrice) {
                System.out.println("Payment Successful! Total Amount Paid: $" + paymentAmount);
                return true;
            } else {
                System.out.println("Insufficient payment. Please pay the full amount.");
                return false;
            }
        }
    }

    // Main class for hotel reservation system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize a list of rooms in the hotel
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Standard", 100.00));
        rooms.add(new Room(102, "Deluxe", 150.00));
        rooms.add(new Room(103, "Suite", 250.00));
        rooms.add(new Room(104, "Standard", 100.00));
        rooms.add(new Room(105, "Deluxe", 150.00));
        rooms.add(new Room(106, "Suite", 250.00));

        // List to store reservations
        ArrayList<Reservation> reservations = new ArrayList<>();

        // Main menu
        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservation Details");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // View Available Rooms
                    System.out.println("\n--- Available Rooms ---");
                    boolean availableRoomsFound = false;
                    for (Room room : rooms) {
                        if (room.isAvailable()) {
                            room.displayRoomDetails();
                            availableRoomsFound = true;
                        }
                    }
                    if (!availableRoomsFound) {
                        System.out.println("No rooms available at the moment.");
                    }
                    break;

                case 2:
                    // Make a Reservation
                    System.out.print("Enter your name: ");
                    String guestName = scanner.nextLine();

                    System.out.print("Enter room number you want to book: ");
                    int roomNumber = scanner.nextInt();
                    boolean roomFound = false;

                    // Search for the room and book it
                    for (Room room : rooms) {
                        if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                            roomFound = true;
                            if (room.bookRoom()) {
                                Reservation reservation = new Reservation(guestName, room);
                                reservations.add(reservation); // Store the reservation
                                reservation.displayReservationDetails();
                                System.out.print("Enter payment amount: $");
                                double paymentAmount = scanner.nextDouble();
                                reservation.processPayment(paymentAmount);
                            }
                            break;
                        }
                    }

                    if (!roomFound) {
                        System.out.println("Room not available or invalid room number.");
                    }
                    break;

                case 3:
                    // View Reservation Details
                    System.out.println("\n--- Reservation Details ---");
                    if (reservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        for (Reservation res : reservations) {
                            res.displayReservationDetails();
                        }
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Thank you for using the Hotel Reservation System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
