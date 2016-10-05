package ca.jbrains.pos.test;

import ca.jbrains.pos.InterpretTextCommand;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class InterpretTextCommandTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private TextCommand checkoutCommand;

    @Mock
    private TextCommand scannedBarcodeCommand;

    @Test
    public void checkout() throws Exception {
        context.checking(new Expectations() {{
            oneOf(checkoutCommand).execute("T");
        }});

        new PointOfSaleTextCommandInterpreter(checkoutCommand, null).interpretCommand("T");
    }

    @Test
    public void scannedBarcode() throws Exception {
        context.checking(new Expectations() {{
            oneOf(scannedBarcodeCommand).execute("::any other command::");
        }});

        new PointOfSaleTextCommandInterpreter(null, scannedBarcodeCommand).interpretCommand("::any other command::");
    }

    public interface TextCommand {
        void execute(String commandText);
    }

    private static class PointOfSaleTextCommandInterpreter implements InterpretTextCommand {
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
}
