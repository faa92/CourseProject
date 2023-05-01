package org.example;

import org.example.commands.AdminCommands;
import org.example.controller.ApplicationController;
import org.example.controller.ConsoleController;
import org.example.models.ExchangeRate;
import org.example.utils.ExchangeFormats;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ApplicationController controller = new ConsoleController();

        if (args.length > 0) { // leave this impl
            String command = args[0];
            List<String> options = List.of(args).subList(1, args.length);
            controller.execute(command, options);
        }

    }
}