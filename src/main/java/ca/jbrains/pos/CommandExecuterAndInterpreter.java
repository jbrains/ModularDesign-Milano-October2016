package ca.jbrains.pos;

import java.util.Scanner;

public class CommandExecuterAndInterpreter {
    private final BarcodeScannedCommand barcodeScannedCommand;

    public CommandExecuterAndInterpreter(BarcodeScannedCommand barcodeScannedCommand) {
        this.barcodeScannedCommand = barcodeScannedCommand;
    }

    public void consume(Scanner scanner) {
        while (scanner.hasNextLine()) {
            // REFACTOR This looks like a little command interpreter. Separate?
            barcodeScannedCommand.onBarcode(scanner.nextLine());
        }
    }
}
