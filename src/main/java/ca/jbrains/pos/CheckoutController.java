package ca.jbrains.pos;

import ca.jbrains.pos.Display;
import ca.jbrains.pos.Shopcart;

public class CheckoutController {
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
