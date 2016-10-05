package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Scanner;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        new CommandExecuterAndInterpreter(
                new SellOneItemController(
                        new InMemoryCatalog(new HashMap<String, Price>() {{
                            put("50375370", Price.cents(150));
                        }}),
                        new Display() {
                            @Override
                            public void displayPrice(Price price) {
                                System.out.println(price.toString());
                            }

                            @Override
                            public void displayProductNotFoundMessage(String barcodeNotFound) {
                                System.out.println(String.format("Product not found for %s", barcodeNotFound));
                            }
                        }
                )
        ).consume(new Scanner(System.in));
    }
}