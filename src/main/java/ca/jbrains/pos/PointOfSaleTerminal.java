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
                        new Shopcart() {
                            @Override
                            public Price getTotal() {
                                throw new UnsupportedOperationException("Not yet implemented.");
                            }

                            @Override
                            public void empty() {
                                throw new UnsupportedOperationException("Not yet implemented.");
                            }

                            @Override
                            public void addProduct(Price price) {
                                throw new UnsupportedOperationException("Not yet implemented.");
                            }
                        }, new Display() {
                            @Override
                            public void displayPrice(Price price) {
                                System.out.println(price.toString());
                            }

                            @Override
                            public void displayProductNotFoundMessage(String barcodeNotFound) {
                                System.out.println(String.format("Product not found for %s", barcodeNotFound));
                            }

                            @Override
                            public void displayTotal(Price total) {
                                throw new UnsupportedOperationException("Not yet implemented.");
                            }
                        }
                )
        ).consume(new Scanner(System.in));
    }
}
