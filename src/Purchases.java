import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Purchases {

    Scanner scn = new Scanner(System.in);
    File saveFile = new File("purchases.txt");
    private double totalSum = 0;
    


    public void add(Income income) {
        boolean back = false;

        do {
            switch (getTypeOfPurchase()) {
                case 1 -> Product.FOOD.list.add(purchaseString(income, Product.FOOD));
                case 2 -> Product.CLOTHES.list.add(purchaseString(income, Product.CLOTHES));
                case 3 -> Product.ENTERTAINMENT.list.add(purchaseString(income, Product.ENTERTAINMENT));
                case 4 -> Product.OTHER.list.add(purchaseString(income, Product.OTHER));
                case 5 -> back = true;
            }
        } while (!back);
    }

    public void showList() {
        boolean back = false;

        do {
            switch (getTypeOfList()) {
                case 1 -> printList(Product.FOOD);
                case 2 -> printList(Product.CLOTHES);
                case 3 -> printList(Product.ENTERTAINMENT);
                case 4 -> printList(Product.OTHER);
                case 5 -> showListOfAllProducts();
                case 6 -> back = true;
            }

        } while (!back);
    }

    public void sort() {
        boolean back = false;

        do {
            switch (getTypeOfSort()) {
                case 1 -> sortAllPurchases();
                case 2 -> sortByType();
                case 3 -> sortCertainType();
                case 4 -> back = true;
            }
        } while (!back);
    }

    private int getTypeOfPurchase() {
        System.out.println("""
                \nChoose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
        return Integer.parseInt(scn.nextLine());
    }

    private int getTypeOfList() {
        System.out.println("""
                \nChoose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
        return Integer.parseInt(scn.nextLine());
    }

    private int getTypeOfSort() {
        System.out.println("""
                \nHow do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back""");
        return Integer.parseInt(scn.nextLine());
    }

    private int getTypeOfCertainType() {
        System.out.println("""
                \nChoose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other""");
        return Integer.parseInt(scn.nextLine());
    }


    private String purchaseString(Income income, Product TYPE) {
        System.out.println("\nEnter purchase name:");
        String nameOfPurchase = scn.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scn.nextLine());

        totalSum += price;
        TYPE.setSum(TYPE.getSum() + price);
        setBalance(income, price);

        System.out.println("Purchase was added!");

        return String.format("%s $%.2f", nameOfPurchase, price);
    }

    private void showListOfAllProducts() {
        System.out.print("\nAll:\n");
        Product.FOOD.list.forEach(System.out::println);
        Product.CLOTHES.list.forEach(System.out::println);
        Product.ENTERTAINMENT.list.forEach(System.out::println);
        Product.OTHER.list.forEach(System.out::println);

        System.out.printf("Total sum: $%.2f\n", totalSum);
    }

    private void printList(Product TYPE) {
        if (TYPE.list.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            System.out.printf("\n%s:\n", TYPE.getName());
            TYPE.list.forEach(System.out::println);
            System.out.printf("Total sum: $%.2f\n", TYPE.getSum());
        }
    }

    private void setBalance(Income income, double price) {
        if (income.getBalance() - price < 0) {
            income.setBalance(0);
        } else {
            income.setBalance(income.getBalance() - price);
        }
    }

    public void save(Income income) {
        File file = createSaveFile(saveFile);
        try (FileWriter writer = new FileWriter(file, false)){
            writer.write(income.getBalance() + "\n");
            writer.write(totalSum + "\n");
            writeToFile(writer, Product.FOOD);
            writeToFile(writer, Product.CLOTHES);
            writeToFile(writer, Product.ENTERTAINMENT);
            writeToFile(writer, Product.OTHER);
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }

    }

    private File createSaveFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + file.getPath());
        }
        return file;
    }

    private void writeToFile(FileWriter writer, Product TYPE) {
        try {
            if (!TYPE.list.isEmpty()) {
                writer.write(TYPE.getName() + "\n");
                writer.write(TYPE.getSum() +"\n");
                for (String purchase : TYPE.list) {
                    writer.write(purchase + "\n");
                }
            }
        } catch (IOException e) {
            System.out.printf("An exception occurred %s\n", e.getMessage());
        }
    }


    public void load(Income income) {
        clearAllData(income);
        try (Scanner fileScanner = new Scanner(saveFile)) {
            readFile(fileScanner, income);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found exception %s\n", e.getMessage());
        }
        System.out.println("\nPurchases were loaded!");
    }

    private void readFile(Scanner scn, Income income) {
        income.setBalance(Double.parseDouble(scn.nextLine()));
        totalSum = (Double.parseDouble(scn.nextLine()));
        Product TYPE = Product.FOOD;
        while (scn.hasNextLine()) {
            String lineInFile = scn.nextLine();
            if (lineInFile.equals("Food")) {
                TYPE.setSum(Double.parseDouble(scn.nextLine()));
                lineInFile = scn.nextLine();
            }
            if (lineInFile.equals("Clothes")) {
                TYPE = Product.CLOTHES;
                TYPE.setSum(Double.parseDouble(scn.nextLine()));
                lineInFile = scn.nextLine();
            }
            if (lineInFile.equals("Entertainment")) {
                TYPE = Product.ENTERTAINMENT;
                TYPE.setSum(Double.parseDouble(scn.nextLine()));
                lineInFile = scn.nextLine();
            }
            if (lineInFile.equals("Other")) {
                TYPE = Product.OTHER;
                TYPE.setSum(Double.parseDouble(scn.nextLine()));
                lineInFile = scn.nextLine();
            }
            TYPE.list.add(lineInFile);
        }

    }
    private void clearAllData(Income income) {
        Product.FOOD.list.clear(); Product.FOOD.setSum(0);
        Product.CLOTHES.list.clear(); Product.CLOTHES.setSum(0);
        Product.ENTERTAINMENT.list.clear(); Product.ENTERTAINMENT.setSum(0);
        Product.OTHER.list.clear(); Product.OTHER.setSum(0);

        totalSum = 0;
        income.setBalance(0);
    }

    private void sortAllPurchases() {
        ArrayList<String> allProducts = createListOfAllProduct();
        if (allProducts.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            System.out.println("\nAll:");
            allProducts.sort(new NumericalStringComparator().reversed());
            allProducts.forEach(System.out::println);
            System.out.printf("Total sum: $%.2f\n", totalSum);
        }

    }

    private void sortByType() {
        ArrayList<String> typeList = createTypeList();
        System.out.println("\nAll:");
        typeList.sort(new NumericalStringComparator().reversed());
        typeList.forEach(System.out::println);
        System.out.printf("Total sum: $%.2f\n", totalSum);

    }

    private void sortCertainType() {
        switch (getTypeOfCertainType()) {
            case 1 -> sortAndPrintCertainType(Product.FOOD);
            case 2 -> sortAndPrintCertainType(Product.CLOTHES);
            case 3 -> sortAndPrintCertainType(Product.ENTERTAINMENT);
            case 4 -> sortAndPrintCertainType(Product.OTHER);
        }
    }

    private void sortAndPrintCertainType(Product Type) {
        ArrayList<String> typeList = new ArrayList<>(Type.getList());
        if (typeList.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            System.out.printf("\n%s\n", Type.getName());
            typeList.sort(new NumericalStringComparator().reversed());
            typeList.forEach(System.out::println);
            System.out.printf("Total sum: $%.2f\n", Type.getSum());
        }
    }

    private ArrayList<String> createListOfAllProduct() {
        ArrayList<String> allProducts = new ArrayList<>();
        allProducts.addAll(Product.FOOD.list);
        allProducts.addAll(Product.ENTERTAINMENT.list);
        allProducts.addAll(Product.CLOTHES.list);
        allProducts.addAll(Product.OTHER.list);
        return allProducts;
    }

    private ArrayList<String> createTypeList() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add(String.format("%s - $%.2f", Product.FOOD.getName(), Product.FOOD.getSum()));
        typeList.add(String.format("%s - $%.2f", Product.CLOTHES.getName(), Product.CLOTHES.getSum()));
        typeList.add(String.format("%s - $%.2f", Product.ENTERTAINMENT.getName(), Product.ENTERTAINMENT.getSum()));
        typeList.add(String.format("%s - $%.2f", Product.OTHER.getName(), Product.OTHER.getSum()));
        return typeList;
    }


}
