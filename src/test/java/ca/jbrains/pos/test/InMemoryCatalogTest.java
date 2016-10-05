package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;

public class InMemoryCatalogTest extends CatalogContract {

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put(String.format("Not %s", barcodeToAvoid), Price.cents(1));
            put(String.format("Absolutely not %s", barcodeToAvoid), Price.cents(2));
            put(String.format("Still, completely and totally not %s", barcodeToAvoid), Price.cents(3));
        }});
    }

    @Override
    protected Catalog catalogWith(final String barcode, final Price foundPrice) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put(barcode, foundPrice);
        }});
    }

}
