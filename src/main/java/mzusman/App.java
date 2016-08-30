package mzusman;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            CommandLineHandler commandLineHandler = new CommandLineHandler(args,
                    new JsonCreator(new OutputStreamWriter(System.out), 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
