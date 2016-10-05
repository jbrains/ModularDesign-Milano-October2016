package ca.jbrains.pos;

import ca.jbrains.pos.Price;

// SMELL displayPrice() and displayTotal() do the same thing!
public interface Display {
    void displayPrice(Price price);

    void displayProductNotFoundMessage(String barcodeNotFound);

    void displayTotal(Price total);
}
