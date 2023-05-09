package org.example;

import org.example.controller.ApplicationController;
import org.example.controller.ConsoleController;

import java.util.List;

public class ApplicationExcange {
    public static void main(String[] args) {

        ApplicationController controller = new ConsoleController();

        if (args.length > 0) { //todo leave this impl
            String command = args[0];
            List<String> options = List.of(args).subList(1, args.length);
            controller.execute(command, options);
        }

    }
}