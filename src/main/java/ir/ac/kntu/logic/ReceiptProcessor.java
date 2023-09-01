package ir.ac.kntu.logic;

import ir.ac.kntu.enums.CommodityType;
import ir.ac.kntu.objects.Commodity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReceiptProcessor {
    public static void printTotalPrice(ArrayList<Commodity> commodities) {
        long sum = 0;
        for (Commodity commodity : commodities) {
            sum += Integer.parseInt(commodity.getPrice());
        }
        System.out.println("total price is: " + sum + "\n");
    }

    public static ArrayList<CommodityType> getCommodityTypes(ArrayList<Commodity> commodities) {
        ArrayList<CommodityType> commodityTypes = new ArrayList<>();
        for (Commodity commodity :
                commodities) {
            if (!commodityTypes.contains(commodity.getType())) {
                commodityTypes.add(commodity.getType());
            }
        }
        return commodityTypes;
    }

    public static void printOfSpecificType(ArrayList<Commodity> commodities, Scanner scanner) {
        ArrayList<Commodity> specified_commodity = new ArrayList<>();
        String input;
        do {
            System.out.println("enter commodity types from the list below: ");
            System.out.println(getCommodityTypes(commodities));
            input = scanner.nextLine();
            if (isNotValidCommodityType(input)) {
                System.out.println("wrong input! \n try again... \n");
            }
        } while (isNotValidCommodityType(input));
        CommodityType commodity_type = CommodityType.valueOf(input);
        for (Commodity commodity : commodities) {
            if (commodity.getType().equals(commodity_type)) {
                specified_commodity.add(commodity);
            }
        }
        printCleanReceipt(specified_commodity);
    }

    public static boolean isNotValidCommodityType(String input) {
        for (CommodityType commodity_type : CommodityType.values()) {
            if (input.equals(String.valueOf(commodity_type))) {
                return false;
            }
        }
        return true;
    }

    public static void printSortedByPrices(ArrayList<Commodity> commodities) {
        commodities.sort(Commodity.PriceComparator);
        printCleanReceipt(commodities);
    }

    public static void printSortedByExpirationDate(ArrayList<Commodity> commodities) {
        commodities.sort(Commodity.ExpirationDateComparator);
        printCleanReceipt(commodities);
    }

    public static void printSortedByQuantities(ArrayList<Commodity> commodities) {
        commodities.sort(Commodity.QuantityComparator);
        printCleanReceipt(commodities);
    }

    public static void printExpiredCommodities(ArrayList<Commodity> commodities) {
        ArrayList<Commodity> expired_commodities = new ArrayList<>();
        for (Commodity commodity : commodities) {
            if (commodity.get_expiration_date().compareTo(LocalDate.now()) < 0) {
                expired_commodities.add(commodity);
            }
        }
        System.out.println("expired commodities: \n");
        printCleanReceipt(expired_commodities);
    }

    public static void printCleanReceipt(ArrayList<Commodity> commodities) {
        for (Commodity commodity : commodities)
            System.out.println(commodity.toString());
        System.out.println();
    }
}
