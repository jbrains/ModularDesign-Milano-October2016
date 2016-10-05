package ca.jbrains.pos.test;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CheckoutControllerTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private Shopcart shopcart;

    @Mock
    private Display display;

    @Test
    public void purchaseInProgress() throws Exception {
        final Price shopcartTotal = Price.cents(12500);

        context.checking(new Expectations() {{
            allowing(shopcart).getTotal();
            will(returnValue(shopcartTotal));

            oneOf(display).displayTotal(shopcartTotal);
        }});

        new CheckoutController(shopcart, display).onCheckout();
    }

    public interface Shopcart {
        Price getTotal();
    }
    
    public static class CheckoutController {
        private final Shopcart shopcart;
        private final Display display;

        public CheckoutController(Shopcart shopcart, Display display) {
            this.shopcart = shopcart;
            this.display = display;
        }

        public void onCheckout() {
            display.displayTotal(shopcart.getTotal());
        }
    }
}
