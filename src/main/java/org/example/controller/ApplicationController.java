package org.example.controller;

import java.util.List;

public interface ApplicationController {

    void execute(String command, List<String> arguments);
}
