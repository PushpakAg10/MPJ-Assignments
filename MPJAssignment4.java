import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

class InvalidIDException extends Exception {
    InvalidIDException(String msg) {
        super(msg);
    }
}

class MinAmountException extends Exception {
    MinAmountException(String msg) {
        super(msg);
    }
}

class NegativeAmountException extends Exception {
    NegativeAmountException(String msg) {
        super(msg);
    }
}

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) {
        super(msg);
    }
}

public class MPJAssignment4 {

    static int[]    customerID      = new int[20];
    static String[] customerName    = new String[20];
    static double[] customerBalance = new double[20];
    static int      totalCustomers  = 0;

    static Scanner sc = new Scanner(System.in);

    static int findCustomer(int id) {
        for (int i = 0; i < totalCustomers; i++) {
            if (customerID[i] == id) {
                return i;
            }
        }
        return -1;
    }

    static void loadFromFile() {
        try {
            FileReader     fr   = new FileReader("bank.txt");
            BufferedReader br   = new BufferedReader(fr);
            String         line;

            while ((line = br.readLine()) != null) {
                String[] parts          = line.split(",");
                customerID[totalCustomers]      = Integer.parseInt(parts[0]);
                customerName[totalCustomers]    = parts[1];
                customerBalance[totalCustomers] = Double.parseDouble(parts[2]);
                totalCustomers++;
            }
            br.close();
            System.out.println("Previous records loaded successfully!");

        } catch (Exception e) {
            System.out.println("No previous records found. Starting fresh!");
        }
    }

    static void saveAllToFile() {
        try {
            FileWriter fw = new FileWriter("bank.txt", false);
            for (int i = 0; i < totalCustomers; i++) {
                fw.write(customerID[i] + "," + customerName[i] + "," + customerBalance[i] + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("File save error: " + e.getMessage());
        }
    }

    static void saveLog(String data) {
        try {
            FileWriter fw = new FileWriter("bank_log.txt", true);
            fw.write(data + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }

    static void createAccount() {
        try {
            System.out.print("Enter Customer ID (1 to 20): ");
            int id = sc.nextInt();
            sc.nextLine();

            if (id < 1 || id > 20) {
                throw new InvalidIDException("ID must be between 1 and 20!");
            }

            if (findCustomer(id) != -1) {
                System.out.println("Customer with this ID already exists!");
                return;
            }

            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Amount (min Rs.1000): ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new NegativeAmountException("Amount cannot be negative or zero!");
            }

            if (amt < 1000) {
                throw new MinAmountException("Minimum amount to open account is Rs.1000!");
            }

            customerID[totalCustomers]      = id;
            customerName[totalCustomers]    = name;
            customerBalance[totalCustomers] = amt;
            totalCustomers++;

            saveAllToFile();
            saveLog("CREATED | ID:" + id + " | Name:" + name + " | Amount:Rs." + amt);

            System.out.println("Account Created Successfully!");
            System.out.println("ID: " + id + " | Name: " + name + " | Balance: Rs." + amt);

        } catch (InvalidIDException e) {
            System.out.println("Invalid ID Error: " + e.getMessage());
        } catch (NegativeAmountException e) {
            System.out.println("Negative Amount Error: " + e.getMessage());
        } catch (MinAmountException e) {
            System.out.println("Minimum Amount Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            sc.nextLine();
        }
    }

    static void deposit() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            if (id < 1 || id > 20) {
                throw new InvalidIDException("ID must be between 1 and 20!");
            }

            int index = findCustomer(id);
            if (index == -1) {
                System.out.println("Customer not found!");
                return;
            }

            System.out.print("Enter Deposit Amount: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new NegativeAmountException("Deposit amount must be positive!");
            }

            customerBalance[index] += amt;

            saveAllToFile();
            saveLog("DEPOSIT | ID:" + id + " | Deposited:Rs." + amt + " | Balance:Rs." + customerBalance[index]);

            System.out.println("Amount Deposited Successfully!");
            System.out.println("New Balance: Rs." + customerBalance[index]);

        } catch (InvalidIDException e) {
            System.out.println("Invalid ID Error: " + e.getMessage());
        } catch (NegativeAmountException e) {
            System.out.println("Negative Amount Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            sc.nextLine();
        }
    }

    static void withdraw() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            if (id < 1 || id > 20) {
                throw new InvalidIDException("ID must be between 1 and 20!");
            }

            int index = findCustomer(id);
            if (index == -1) {
                System.out.println("Customer not found!");
                return;
            }

            System.out.print("Enter Withdrawal Amount: ");
            double amt = sc.nextDouble();

            if (amt <= 0) {
                throw new NegativeAmountException("Withdrawal amount must be positive!");
            }

            if (amt > customerBalance[index]) {
                throw new InsufficientBalanceException(
                    "You want Rs." + amt + " but balance is only Rs." + customerBalance[index]);
            }

            customerBalance[index] -= amt;

            saveAllToFile();
            saveLog("WITHDRAW | ID:" + id + " | Withdrawn:Rs." + amt + " | Balance:Rs." + customerBalance[index]);

            System.out.println("Amount Withdrawn Successfully!");
            System.out.println("Remaining Balance: Rs." + customerBalance[index]);

        } catch (InvalidIDException e) {
            System.out.println("Invalid ID Error: " + e.getMessage());
        } catch (NegativeAmountException e) {
            System.out.println("Negative Amount Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Insufficient Balance Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            sc.nextLine();
        }
    }

    static void checkBalance() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = sc.nextInt();

            if (id < 1 || id > 20) {
                throw new InvalidIDException("ID must be between 1 and 20!");
            }

            int index = findCustomer(id);
            if (index == -1) {
                System.out.println("Customer not found!");
                return;
            }

            System.out.println("--- Account Details ---");
            System.out.println("ID      : " + customerID[index]);
            System.out.println("Name    : " + customerName[index]);
            System.out.println("Balance : Rs." + customerBalance[index]);

        } catch (InvalidIDException e) {
            System.out.println("Invalid ID Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            sc.nextLine();
        }
    }

    static void displayAll() {
        if (totalCustomers == 0) {
            System.out.println("No customers added yet!");
            return;
        }
        System.out.println("\n--- All Customers ---");
        for (int i = 0; i < totalCustomers; i++) {
            System.out.println("ID: " + customerID[i] + " | Name: " + customerName[i] + " | Balance: Rs." + customerBalance[i]);
        }
    }

    static void deleteAccount() {
        try {
            System.out.print("Enter Customer ID to Delete: ");
            int id = sc.nextInt();

            if (id < 1 || id > 20) {
                throw new InvalidIDException("ID must be between 1 and 20!");
            }

            int index = findCustomer(id);
            if (index == -1) {
                System.out.println("Customer not found!");
                return;
            }

            for (int i = index; i < totalCustomers - 1; i++) {
                customerID[i]      = customerID[i + 1];
                customerName[i]    = customerName[i + 1];
                customerBalance[i] = customerBalance[i + 1];
            }
            totalCustomers--;

            saveAllToFile();
            saveLog("DELETED | ID:" + id);

            System.out.println("Account Deleted Successfully!");

        } catch (InvalidIDException e) {
            System.out.println("Invalid ID Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            sc.nextLine();
        }
    }

    static void viewLog() {
        try {
            FileReader     fr = new FileReader("bank_log.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            System.out.println("\n--- Transaction Log ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("No log records found yet.");
        }
    }

    public static void main(String[] args) {

        loadFromFile();
        int choice;

        do {
            System.out.println("\n====== BANKING SYSTEM ======");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Customers");
            System.out.println("6. Delete Account");
            System.out.println("7. View Transaction Log");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if      (choice == 1) createAccount();
            else if (choice == 2) deposit();
            else if (choice == 3) withdraw();
            else if (choice == 4) checkBalance();
            else if (choice == 5) displayAll();
            else if (choice == 6) deleteAccount();
            else if (choice == 7) viewLog();
            else if (choice == 0) System.out.println("Goodbye!");
            else                  System.out.println("Wrong choice!");

        } while (choice != 0);

        sc.close();
    }
}