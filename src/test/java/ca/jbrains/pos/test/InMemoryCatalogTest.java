package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        final Price foundPrice = Price.cents(795);

        Assert.assertEquals(foundPrice, catalogWith("12345", foundPrice).findPrice("12345"));
    }

    private InMemoryCatalog catalogWith(final String barcode, final Price foundPrice) {
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
