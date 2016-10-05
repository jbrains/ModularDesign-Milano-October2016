package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private Catalog catalog;

    @Mock
    private Display display;

    @Test
    public void productFound() throws Exception {
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::irrelevant barcode::"));
            will(returnValue(Price.cents(580)));

            oneOf(display).displayPrice(with(Price.cents(580)));
        }});

        new SellOneItemController(catalog, display).onBarcode("::irrelevant barcode::");
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }
    public interface Display {
        void displayPrice(Price price);
    }

    private static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            display.displayPrice(catalog.findPrice(barcode));
        }
    }

    public static class Price {
        public static Price cents(int priceInCents) {
            return null;
        }
    }
}
