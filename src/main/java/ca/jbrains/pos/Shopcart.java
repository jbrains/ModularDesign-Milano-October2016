package ca.jbrains.pos;

import ca.jbrains.pos.Price;

public interface Shopcart {
    Price getTotal();

    void empty();

    void addProduct(Price price);
}
