package ca.jbrains.pos;

public class PointOfSaleTextCommandInterpreter implements InterpretTextCommand {
    private final TextCommand checkoutCommand;
    private final TextCommand scannedBarcodeCommand;

    // SMELL It will be easy to get all these commands wrong! We need stronger typing?
    // REFACTOR Maybe we need some kind of command language description?! (I'm thinking too far ahead!)
    public PointOfSaleTextCommandInterpreter(TextCommand checkoutCommand, TextCommand scannedBarcodeCommand) {
        this.checkoutCommand = checkoutCommand;
        this.scannedBarcodeCommand = scannedBarcodeCommand;
    }

    @Override
    public void interpretCommand(String commandText) {
        // SMELL parse command and execute command are in one place. Hm.
        if ("T".equals(commandText))
            checkoutCommand.execute(commandText);
        else
            scannedBarcodeCommand.execute(commandText);
    }
}
