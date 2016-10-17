package ca.jbrains.pos;

import java.util.HashMap;
import java.util.Scanner;

public class PointOfSaleTerminal {

    public static void main(String[] args) {
        final Shopcart shopcart = new Shopcart() {
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
        };

        final Catalog catalog = new InMemoryCatalog(new HashMap<String, Price>() {{
            put("50375370", Price.cents(150));
        }});

        final Display display = new Display() {
            @Override
            public void displayPrice(Price price) {
                System.out.printf("%s", price);
            }

            @Override
            public void displayProductNotFoundMessage(String barcodeNotFound) {
                System.out.printf("Product not found for %s", barcodeNotFound);

            }

            @Override
            public void displayTotal(Price total) {
                System.out.printf("Total: %s", total);
            }
        };

        new ConsumeTextCommands(
                new PointOfSaleTextCommandInterpreter(
                        (command) -> { new CheckoutController(shopcart, display).onCheckout(); },
                        (command) -> { new BarcodeScannedController(catalog, shopcart, display).onBarcode(command); }
                )
        ).consume(new Scanner(System.in));
    }
}
