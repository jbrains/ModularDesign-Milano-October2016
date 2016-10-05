package ca.jbrains.pos;

import java.util.Scanner;

public class ConsumeTextCommands {
    private final InterpretTextCommand interpretTextCommand;

    public ConsumeTextCommands(InterpretTextCommand interpretTextCommand) {
        this.interpretTextCommand = interpretTextCommand;
    }

    public void consume(Scanner scanner) {
        while (scanner.hasNextLine()) {
            interpretTextCommand.interpretCommand(scanner.nextLine());
        }
    }
}
