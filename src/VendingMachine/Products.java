package VendingMachine;

public enum Products {
    GUM("Gum", 0.2f), CRISPS("Crisps", 0.5f), SNICKERS("Snickers", 0.8f), AERO("Aero", 1.5f);
    private String title;
    private Float price;

    Products(String title, Float price) {
        this.title = title;
        this.price = price;

    }

    public String getName() {
        return title;

    }

    public Float getPrice() {
        return price;

    }

}