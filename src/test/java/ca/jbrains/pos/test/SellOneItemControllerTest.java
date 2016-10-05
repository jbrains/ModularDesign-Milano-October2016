package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
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
        final Price foundPrice = Price.cents(580);

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::irrelevant barcode::"));
            will(returnValue(foundPrice));

            oneOf(display).displayPrice(with(foundPrice));
        }});

        new SellOneItemController(catalog, display).onBarcode("::irrelevant barcode::");
    }

    @Test
    public void productNotFound() throws Exception {
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::irrelevant barcode::"));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with("::irrelevant barcode::"));
        }});

        new SellOneItemController(catalog, display).onBarcode("::irrelevant barcode::");
    }

    public interface Display {
        void displayPrice(Price price);

        void displayProductNotFoundMessage(String barcodeNotFound);
    }

    private static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            final Price price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }
    }

}
