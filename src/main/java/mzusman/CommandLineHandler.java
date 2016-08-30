package mzusman;

import io.airlift.airline.*;

import java.io.*;
import java.util.List;

/**
 * Created by mzeus-arch on 8/30/16.
 */
public class CommandLineHandler implements CommandLineHandlerInterface {
    PageCreator creator;

    public CommandLineHandler(String[] args, PageCreator pageCreator) {
        this.creator = pageCreator;
        Cli.CliBuilder<Runnable> cliBuilder = Cli.<Runnable>builder("levelmaker")
                .withDescription("make a json to pass it for the levels parser")
                .withDefaultCommand(Help.class)
                .withCommands(Help.class, Names.class);

        Cli<Runnable> parser = cliBuilder.build();
        parser.parse(args).run();


    }

    public static class LevelMakerCommand implements Runnable {

        public void run() {
        }
    }

    @Command(name = "names", description = "set names of values inside the json unit")
    public static class Names extends LevelMakerCommand {
        PageCreator creator;

        @Arguments(required = true, title = "names", description = "names")
        public List<String> names;

        File file;

        public Names() {
            try {
                File file = askForFile();
                    this.file = file;
                    this.creator = new JsonCreator(new OutputStreamWriter(new FileOutputStream(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private File askForFile() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("enter file for output:");
            String s = reader.readLine();
            File file = new File(s);
            if(file.exists())
                file.delete();
            if(file.createNewFile())
                return file;
            else return askForFile();
        }

        public Names(PageCreator pageCreator) {
            this.creator = pageCreator;
        }

        @Override
        public void run() {
            super.run();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                creator.setBeginOfLevels(1);
                do {
                    creator.addLevel();
                    for (String name :
                            names) {
                        System.out.print("for name __" + name + "__ enter value:");
                        String v = reader.readLine();
                        creator.addCriteria(name, v);
                    }
                    creator.endLevel();
                } while (wantMore());
                creator.endLevelsArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean wantMore() throws IOException {
            System.out.println("continue for level " + creator.getCurrentLevel() + " ?(y/n)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String answer = reader.readLine();
            return answer.equals("y") || !answer.equals("n") && wantMore();
        }

    }


}


