import java.util.Scanner;

class Shapes {

    double area(double side) {
        return side * side;
    }

    double area(double length, double breadth) {
        return length * breadth;
    }

    double area(double base, double height, boolean isTriangle) {
        return 0.5 * base * height;
    }

    double area(double radius, boolean isCircle) {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shapes s = new Shapes();
        int choice;

        do {
            System.out.println("\n===== SHAPE AREA CALCULATOR =====");
            System.out.println("1. Square");
            System.out.println("2. Rectangle");
            System.out.println("3. Triangle");
            System.out.println("4. Circle");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter side of Square: ");
                    double side = sc.nextDouble();
                    System.out.println("Area of Square = " + s.area(side));
                    break;

                case 2:
                    System.out.print("Enter length of Rectangle: ");
                    double length = sc.nextDouble();
                    System.out.print("Enter breadth of Rectangle: ");
                    double breadth = sc.nextDouble();
                    System.out.println("Area of Rectangle = " + s.area(length, breadth));
                    break;

                case 3:
                    System.out.print("Enter base of Triangle: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter height of Triangle: ");
                    double height = sc.nextDouble();
                    System.out.println("Area of Triangle = " + s.area(base, height, true));
                    break;

                case 4:
                    System.out.print("Enter radius of Circle: ");
                    double radius = sc.nextDouble();
                    System.out.printf("Area of Circle = %.2f%n", s.area(radius, true));
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