import java.util.Scanner;

public class Menu {

    Scanner scn = new Scanner(System.in);

    public void start() {
        Income income = new Income(0);
        Purchases purchase = new Purchases();

        while (true) {

            showMainMenu();
            int action = getInput();

            switch (action) {
                case 1 -> income.add(scn);
                case 2 -> purchase.add(income);
                case 3 -> purchase.showList();
                case 4 -> income.showBalance();
                case 5 -> purchase.save(income);
                case 6 -> purchase.load(income);
                case 7 -> purchase.sort();
                case 0 -> turnOff();
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("""
                \nChoose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit""");
    }

    private int getInput() {
        int input;
        String badParameter = "Bad parameter";

        while(true) {
            try {
                input = Integer.parseInt(scn.next());
                if (input < 0 || input > 7) {
                    System.out.println(badParameter);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(badParameter);
            }
        }
        return input;
    }

    private void turnOff() {
        System.out.println("\nBye!");
        System.exit(0);
    }

}
