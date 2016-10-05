package ca.jbrains.pos.test;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.Shopcart;
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

            // SMELL Two expectations in the same test!
            // What does this mean?
            oneOf(display).displayTotal(shopcartTotal);
            oneOf(shopcart).empty();
        }});

        new CheckoutController(shopcart, display).onCheckout();
    }

    @Test
    public void purchaseNotInProgress() throws Exception {
        // This is a UI problem, so don't solve it here!!
        // Asking to check out without any products in the
        // shopcart is like running a program that doesn't
        // compile. :)
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
            shopcart.empty();
        }
    }
}
