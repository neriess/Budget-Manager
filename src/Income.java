public class Income {

    private double balance;

    public Income(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void add(java.util.Scanner scn) {
        double income = getIncomeInput(scn);
        balance += income;
        System.out.println("Income was added!");
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n", balance);
    }

    private double getIncomeInput(java.util.Scanner scn) {
        double income;
        String badParameter = "Bad parameter";

        while (true) {
            try {
                System.out.println("\nEnter Income:");
                income = Double.parseDouble(scn.next());
                if (income <= 0) {
                    System.out.println(badParameter);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(badParameter);
            }
        }
        return income;
    }
}