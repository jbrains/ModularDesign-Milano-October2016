package ca.jbrains.pos;

public class SellOneItemController implements BarcodeScannedCommand {
    private final Catalog catalog;
    private final Shopcart shopcart;
    private final Display display;

    public SellOneItemController(Catalog catalog, Shopcart shopcart, Display display) {
        this.catalog = catalog;
        this.shopcart = shopcart;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        final Price price = catalog.findPrice(barcode);
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else {
            display.displayPrice(price);
            shopcart.addProduct(price);
        }
    }
}
