package ca.jbrains.pos.test;

import ca.jbrains.pos.PointOfSaleTextCommandInterpreter;
import ca.jbrains.pos.TextCommand;
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

}
