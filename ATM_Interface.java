import java.util.Scanner;

class BankAccount {
    String name, userName, password, accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        this.name = sc.nextLine();
        System.out.println("Enter your userName: ");
        this.userName = sc.nextLine();
        System.out.println("Enter your password: ");
        this.password = sc.nextLine();
        System.out.println("Enter your account number: ");
        this.accountNo = sc.nextLine();
        System.out.println("Registration completed. Now kindly login");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);

        while (!isLogin) {
            System.out.println("Enter your Username: ");
            String inputUsername = sc.nextLine();
            if(inputUsername.equals(this.userName)) {
                while (!isLogin) {
                    System.out.println("Enter your password: ");
                    String inputPassword = sc.nextLine();
                    if(inputPassword.equals(this.password)) {
                        System.out.println("Login successful.");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect Password!");
                    }
                }
            } else {
                System.out.println("Username not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("Enter the amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if (amount > 0 && balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("Withdraw Successfully.");
            String str = amount + " Rs Withdrawed\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("Invalid Amount or Insufficient Balance");
        }
    }

    public void deposit() {
        System.out.println("Enter amount to deposit: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if (amount > 0 && amount <= 100000f) {
            transactions++;
            balance += amount;
            System.out.println("Successfully Deposited.");
            String str = amount + " Rs deposited\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("Invalid Amount or Exceeds Limit");
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Recipient's name: ");
        String recipient = sc.nextLine();
        System.out.println("Enter amount to transfer: ");
        float amount = sc.nextFloat();
        if (amount > 0 && amount <= 50000f && balance >= amount) {
            transactions++;
            balance -= amount;
            System.out.println("Successfully Transferred to " + recipient);
            String str = amount + " Rs transferred to " + recipient + "\n";
            transactionHistory = transactionHistory.concat(str);
        } else {
            System.out.println("Invalid Amount, Exceeds Limit, or Insufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("Empty.");
        } else {
            System.out.println(transactionHistory);
        }
    }
}

public class ATM_Interface {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);

        while (!flag) {
            try {
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    flag = true;
                } else {
                    System.out.println("Choose a number between 1 to " + limit);
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value");
                sc.nextLine(); // Clear the buffer
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("***** WELCOME TO ATM SYSTEM *****");
        System.out.println("1. Register \n2. Exit");
        System.out.println("Enter your choice: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("1. Login \n2. Exit");
                System.out.println("Enter your choice: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n\n***** WELCOME BACK " + b.name + " *****\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.println("\nEnter your choice: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
