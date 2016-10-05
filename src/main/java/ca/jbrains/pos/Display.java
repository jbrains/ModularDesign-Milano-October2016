package ca.jbrains.pos;

import ca.jbrains.pos.Price;

public interface Display {
    void displayPrice(Price price);

    void displayProductNotFoundMessage(String barcodeNotFound);
}
