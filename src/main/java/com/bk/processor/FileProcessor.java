package com.bk.processor;


import com.bk.processor.BaseProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class FileProcessor extends BaseProcessor {



    public void processFile(String path) throws IllegalArgumentException {

        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Invalid Path: " + path);
        }

        try (Stream<String> fileStream = Files.lines(Paths.get(path))) {

            Iterator<String> lineIterator = fileStream.iterator();
            long lineNumber = 0l;
            while (lineIterator.hasNext()) {
                try {
                    lineNumber++;
                    String line = lineIterator.next();
                    String[] inputs = getInputs(line);
                    if (inputs.length < 1) {
                        throw new IllegalArgumentException("no input provided in line number: " + lineNumber);
                    }

                    process(inputs);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
