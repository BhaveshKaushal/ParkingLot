package com.bk.processor;

import com.bk.processor.BaseProcessor;

import java.util.Scanner;

public class CommandLineProcessor extends BaseProcessor {

    public void processInteractively() {
        Scanner scanner = new Scanner(System.in);
        while(!this.exit) {
            String line = scanner.nextLine();
            String[] inputs = getInputs(line);
            process(inputs);
        }
    }
}
