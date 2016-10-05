package ca.jbrains.pos;

import java.util.Scanner;

public class CommandExecuterAndInterpreter {
    private final BarcodeScannedCommand barcodeScannedCommand;
    private final InterpretTextCommand interpretTextCommand;

    public CommandExecuterAndInterpreter(BarcodeScannedCommand barcodeScannedCommand) {
        this(barcodeScannedCommand, null);
    }

    public CommandExecuterAndInterpreter(BarcodeScannedCommand barcodeScannedCommand, InterpretTextCommand interpretTextCommand) {
        this.barcodeScannedCommand = barcodeScannedCommand;
        this.interpretTextCommand = interpretTextCommand;
    }

    public void consume(Scanner scanner) {
        while (scanner.hasNextLine()) {
            // REFACTOR This looks like a little command interpreter. Separate?
            final String commandText = scanner.nextLine();
            barcodeScannedCommand.onBarcode(commandText);
            interpretTextCommand.interpretCommand(commandText);
        }
    }
}
