package ir.ac.kntu.objects;

import ir.ac.kntu.enums.CommodityType;

import java.time.LocalDate;
import java.util.Comparator;

public class Commodity {
    private String name;
    private CommodityType type;
    private String quantity;
    private String price;
    private LocalDate production_date;
    private LocalDate expiration_date;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setType(CommodityType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionDate(String date) {
        String[] dateNums = date.split("-");
        this.production_date = LocalDate.of
                (Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setExpirationDate(String date) {
        String[] dateNums = date.split("-");
        this.expiration_date = LocalDate.of
                (Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
    }

    public CommodityType getType() {
        return type;
    }

    public LocalDate get_expiration_date() {
        return expiration_date;
    }

    public String getPrice() {
        return price;
    }

    public Commodity() {
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", production_date=" + production_date +
                ", expiration_date=" + expiration_date +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    public static Comparator<Commodity> QuantityComparator = (commodity1, commodity2) -> {
        Integer quantity1 = Integer.parseInt(commodity1.getQuantity());
        Integer quantity2 = Integer.parseInt(commodity2.getQuantity());
        return quantity1.compareTo(quantity2);
    };
    public static Comparator<Commodity> ExpirationDateComparator = (commodity1, commodity2) -> {
        LocalDate date1 = commodity1.get_expiration_date();
        LocalDate date2 = commodity2.get_expiration_date();
        return date1.compareTo(date2);
    };

    public static Comparator<Commodity> PriceComparator = (commodity1, commodity2) -> {
        Long price1 = Long.parseLong(commodity1.getPrice());
        Long price2 = Long.parseLong(commodity2.getPrice());
        return price1.compareTo(price2);
    };

}
