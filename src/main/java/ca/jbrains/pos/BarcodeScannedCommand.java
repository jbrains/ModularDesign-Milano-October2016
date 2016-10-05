package ca.jbrains.pos;

public interface BarcodeScannedCommand {
    void onBarcode(String barcode);
}
