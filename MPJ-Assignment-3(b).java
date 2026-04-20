import java.util.Scanner;

class Hillstations {
    void famousfood() {
        System.out.println("Famous food of this hill station.");
    }

    void famousfor() {
        System.out.println("This hill station is famous for its natural beauty.");
    }
}

class Mahabaleshwar extends Hillstations {
    @Override
    void famousfood() {
        System.out.println("Mahabaleshwar: Famous for Strawberries, Corn, and Mulberries.");
    }

    @Override
    void famousfor() {
        System.out.println("Mahabaleshwar: Famous for strawberry farms, Venna Lake, and pleasant climate.");
    }
}

class Lonavala extends Hillstations {
    @Override
    void famousfood() {
        System.out.println("Lonavala: Famous for Chikki (traditional sweet) and Fudge.");
    }

    @Override
    void famousfor() {
        System.out.println("Lonavala: Famous for scenic valleys, Bhushi Dam, and Karla Caves.");
    }
}

class Darjeeling extends Hillstations {
    @Override
    void famousfood() {
        System.out.println("Darjeeling: Famous for Darjeeling Tea, Momos, and Thukpa.");
    }

    @Override
    void famousfor() {
        System.out.println("Darjeeling: Famous for tea gardens, Tiger Hill sunrise, and Toy Train.");
    }
}

class HillstationsMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hillstations h;
        int choice;

        do {
            System.out.println("\n===== HILL STATIONS MENU =====");
            System.out.println("1. Mahabaleshwar");
            System.out.println("2. Lonavala");
            System.out.println("3. Darjeeling");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    h = new Mahabaleshwar();
                    System.out.println("\n--- Mahabaleshwar ---");
                    h.famousfood();
                    h.famousfor();
                    break;

                case 2:
                    h = new Lonavala();
                    System.out.println("\n--- Lonavala ---");
                    h.famousfood();
                    h.famousfor();
                    break;

                case 3:
                    h = new Darjeeling();
                    System.out.println("\n--- Darjeeling ---");
                    h.famousfood();
                    h.famousfor();
                    break;

                case 0:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}