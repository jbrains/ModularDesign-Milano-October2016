package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Test;

public abstract class CatalogContract {
    @Test
    public void productFound() throws Exception {
        final Price foundPrice = Price.cents(795);

        Assert.assertEquals(
                foundPrice,
                catalogWith("::known product::", foundPrice).findPrice("::known product::"));
    }

    @Test
    public void productNotFound() throws Exception {
        Assert.assertEquals(
                null,
                catalogWithout("::unknown product::").findPrice("::unknown product::"));
    }

    protected abstract Catalog catalogWithout(String barcodeToAvoid);

    protected abstract Catalog catalogWith(String barcode, Price foundPrice);
}
