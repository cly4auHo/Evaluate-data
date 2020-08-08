package output;

import java.util.*;

public class AppMain {
    public static void main(String[] args) {
        List<String> input = new ArrayList<String>();
        input.add("C 1.1 8.15.1 P 15.10.2012 83");
        input.add("C 1 10.1 P 01.12.2012 65");
        input.add("C 1.1 5.5.1 P 01.11.2012 117");
        input.add("D 1.1 8 P 01.01.2012-01.12.2012");
        input.add("C 3 10.2 N 02.10.2012 100");
        input.add("D 1 * P 8.10.2012-20.11.2012");
        input.add("D 3 10 P 01.12.2012");

        Outputer outputer = new Outputer();
        List<String> outputs = outputer.output(input);

        for (String output : outputs) {
            System.out.println(output);
        }
    }
}
