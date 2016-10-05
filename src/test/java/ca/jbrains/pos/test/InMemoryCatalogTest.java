package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
