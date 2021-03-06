package ca.jbrains.pos.test;

import ca.jbrains.pos.ConsumeTextCommands;
import ca.jbrains.pos.InterpretTextCommand;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.Scanner;

public class ConsumeTextCommandsTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    public InterpretTextCommand interpretTextCommand;

    @Test
    public void oneLine() throws Exception {
        context.checking(new Expectations() {{
            oneOf(interpretTextCommand).interpretCommand("::I interpret this line as a command::");
        }});

        new ConsumeTextCommands(interpretTextCommand).consume(
                new Scanner(
                        "::I interpret this line as a command::" + System.lineSeparator()));
    }

    @Test
    public void severalLines() throws Exception {
        context.checking(new Expectations() {{
            oneOf(interpretTextCommand).interpretCommand("::I interpret this 1st line as a command::");
            oneOf(interpretTextCommand).interpretCommand("::I interpret this 2nd line as a command::");
            oneOf(interpretTextCommand).interpretCommand("::I interpret this 3rd line as a command::");
        }});

        new ConsumeTextCommands(interpretTextCommand).consume(
                new Scanner(
                        "::I interpret this 1st line as a command::" + System.lineSeparator() +
                                "::I interpret this 2nd line as a command::" + System.lineSeparator() +
                                "::I interpret this 3rd line as a command::" + System.lineSeparator()
                ));
    }

}
