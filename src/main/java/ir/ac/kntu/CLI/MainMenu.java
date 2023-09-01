package ir.ac.kntu.CLI;

import ir.ac.kntu.enums.Choice;
import ir.ac.kntu.enums.ReceiptType;
import ir.ac.kntu.logic.JsonGenerator;
import ir.ac.kntu.logic.ReceiptContainer;
import ir.ac.kntu.logic.ReceiptProcessor;
import ir.ac.kntu.objects.Storage;

import java.util.Locale;
import java.util.Scanner;

import static ir.ac.kntu.enums.Choice.exit;
import static ir.ac.kntu.enums.ReceiptType.BUY;
import static ir.ac.kntu.enums.ReceiptType.SELL;

public class MainMenu {
    public static void main(String receipt) {
        Storage storage = new Storage();
        Scanner scanner = new Scanner(System.in);
        ReceiptType receipt_type;
        receipt_type = getReceiptType(scanner);
        ReceiptContainer receiptContainer = new ReceiptContainer(receipt, receipt_type);
        receiptContainer.process(storage);
        String string_choice;
        Choice choice = null;
        do {
            System.out.println("""
                    choose operation on the commodity list:
                    PST: print_of_specific_type
                    SBP: sort_by_prices
                    SBE: sort_by_expiration_date
                    SBQ: sort_by_quantity
                    SEC: show_expired_commodities
                    PTP: print_total_price
                    PCR: print_clean_receipt
                    NEW: enter_new_receipt
                    exit
                    enter alphabetic input !
                    """);
            string_choice = scanner.nextLine();
            if (isValidChoice(string_choice, Choice.values())) {
                choice = Choice.valueOf(string_choice);
            } else {
                clearConsole();
                System.out.println("""
                        wrong input !
                        try again !
                        """);
                continue;
            }
            switch (choice) {
                case PST -> {
                    clearConsole();
                    ReceiptProcessor.
                            printOfSpecificType(receiptContainer.getCommodities(), scanner);
                }
                case SBP -> {
                    clearConsole();
                    ReceiptProcessor.printSortedByPrices(receiptContainer.getCommodities());
                }
                case SBE -> {
                    clearConsole();
                    ReceiptProcessor.printSortedByExpirationDate
                            (receiptContainer.getCommodities());
                }
                case SBQ -> {
                    clearConsole();
                    ReceiptProcessor.printSortedByQuantities
                            (receiptContainer.getCommodities());
                }
                case SEC -> {
                    clearConsole();
                    ReceiptProcessor.printExpiredCommodities(
                            receiptContainer.getCommodities());
                }
                case PTP -> {
                    clearConsole();
                    ReceiptProcessor.printTotalPrice(receiptContainer.getCommodities());
                }
                case PCR -> {
                    clearConsole();
                    ReceiptProcessor.printCleanReceipt
                            (receiptContainer.getCommodities());
                }
                case NEW -> {
                    clearConsole();
                    JsonGenerator jsonGenerator = new JsonGenerator();
                    receipt = jsonGenerator.generateBuyRecipe();
                    receipt_type = getReceiptType(scanner);
                    receiptContainer = new ReceiptContainer(receipt, receipt_type);
                    receiptContainer.process(storage);
                }
            }
        } while (choice != exit);
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static boolean isValidChoice(String test, Choice[] clazz) {
        for (Choice str : clazz) {
            if (String.valueOf(str).equals(test)) {
                return true;
            }
        }
        return false;
    }

    public static ReceiptType getReceiptType(Scanner scanner) {
        String receipt_type_input;
        System.out.println("""
                which receipt?
                1- buy
                2- sell
                type the number !
                """);
        do {
            receipt_type_input = scanner.nextLine();
            if (!(receipt_type_input.toLowerCase(Locale.ROOT).equals("sell") ||
                    receipt_type_input.toLowerCase(Locale.ROOT).equals("buy"))) {
                System.out.println("""
                        wrong input!
                        try again!
                        """);
            }
        } while (!(receipt_type_input.toLowerCase(Locale.ROOT).equals("sell") ||
                receipt_type_input.toLowerCase(Locale.ROOT).equals("buy")));
        ReceiptType receipt_type = null;
        if (receipt_type_input.equals("buy")) {
            receipt_type = BUY;
        }
        if (receipt_type_input.equals("2")) {
            receipt_type = SELL;
        }
        return receipt_type;
    }
}
