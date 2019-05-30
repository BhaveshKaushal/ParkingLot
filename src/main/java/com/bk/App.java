package com.bk;

import com.bk.processor.CommandLineProcessor;
import com.bk.processor.FileProcessor;

public class App {

    public static void main(String[] ar){
        try {

            if(ar.length !=0) {
                FileProcessor fileProcessor = new FileProcessor();
                fileProcessor.processFile(ar[0]);
            } else {
                CommandLineProcessor commandLineProcessor =  new CommandLineProcessor();
                commandLineProcessor.processInteractively();

            }
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }



    }
}
