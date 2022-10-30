import java.util.ArrayList;

public enum Product {

    FOOD("Food", 0, new ArrayList<>()),
    CLOTHES("Clothes", 0, new ArrayList<>()),
    ENTERTAINMENT("Entertainment", 0, new ArrayList<>()),
    OTHER("Other", 0, new ArrayList<>());

    String name;
    double sum;
    ArrayList<String> list;

    Product(String name, double sum, ArrayList<String> list) {
        this.name = name;
        this.sum = sum;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}

