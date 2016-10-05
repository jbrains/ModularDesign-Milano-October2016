package ca.jbrains.pos.test.ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Scanner;

public class ConsumeInputAndInterpretCommandsTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    public BarcodeScannedCommand barcodeScannedCommand;

    @Test
    public void oneLine() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedCommand).onBarcode("::I interpret this line as a barcode::");
        }});

        new CommandExecuterAndInterpreter(barcodeScannedCommand).consume(
                new Scanner(
                        "::I interpret this line as a barcode::" + System.lineSeparator()));
    }

    public interface BarcodeScannedCommand {
        void onBarcode(String barcode);
    }

    private static class CommandExecuterAndInterpreter {
        private final BarcodeScannedCommand barcodeScannedCommand;

        public CommandExecuterAndInterpreter(BarcodeScannedCommand barcodeScannedCommand) {
            this.barcodeScannedCommand = barcodeScannedCommand;
        }

        public void consume(Scanner scanner) {
            barcodeScannedCommand.onBarcode(scanner.nextLine());
        }
    }
}