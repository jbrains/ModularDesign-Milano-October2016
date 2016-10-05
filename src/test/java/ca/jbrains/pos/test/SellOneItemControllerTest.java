package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private Shopcart shopcart;

    @Mock
    private Catalog catalog;

    @Mock
    private Display display;

    @Test
    public void productFound() throws Exception {
        final Price foundPrice = Price.cents(580);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::irrelevant barcode::"));
            will(returnValue(foundPrice));

            oneOf(display).displayPrice(with(foundPrice));
            oneOf(shopcart).addProduct(with(foundPrice));
        }});

        new SellOneItemController(catalog, shopcart, display).onBarcode("::irrelevant barcode::");
    }

    @Test
    public void productNotFound() throws Exception {
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::irrelevant barcode::"));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with("::irrelevant barcode::"));
        }});

        new SellOneItemController(catalog, shopcart, display).onBarcode("::irrelevant barcode::");
    }

}
