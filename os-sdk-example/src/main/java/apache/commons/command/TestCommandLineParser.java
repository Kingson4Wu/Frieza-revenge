package apache.commons.command;

import org.apache.commons.cli.*;

/**
 * Created by kxw on 2015/10/10.
 */
public class TestCommandLineParser {

    public void test() throws ParseException {

        //String[] args = {"http", "-p", "80", "-c" ,"/helloworld.json"};
        String[] args = {"http", "socket", "-p", "80", "-c" ,"/helloworld.json" ,"-https" ,"mm"};

        Options options = new Options();
        Option opt1 = new Option("c", true, "config");
        opt1.setType(String.class);
        opt1.setRequired(false);
        options.addOption(opt1);

        Option opt2 = new Option("p", true, "port");
        opt2.setType(Number.class);
        opt2.setRequired(false);
        options.addOption(opt2);

        Option option = new Option(null, "https", true, "Https certificate filename");
        option.setType(String.class);
        option.setRequired(false);
        options.addOption(option);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);


        String port = cmd.getOptionValue("p");
        String config = cmd.getOptionValue("c");

        String https = cmd.getOptionValue("https");



        System.out.println(port);
        System.out.println(config);
        System.out.println(https);
        System.out.println(cmd.hasOption("https"));


        System.out.println(cmd.getArgs().length);
    }

}
