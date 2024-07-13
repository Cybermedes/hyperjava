package com.introduction;

import java.io.*;
import java.util.Objects;
import java.util.stream.Stream;

public class Intro {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                readIntroductionFile();
            } else {
                if (args[0].equals("--help")) {
                    readProjectHelpFile(args[1]);
                } else {
                    System.out.printf("Unknown '%s' command.%n", args[0]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.format("missing 'project name' argument%n");
        }
    }

    private static void readIntroductionFile() {
        try (InputStream is = Intro.class.getClassLoader().getResourceAsStream("help/introduction.txt");
             var br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
             Stream<String> lines = br.lines()) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }

    private static void readProjectHelpFile(String projectName) {
        final String fileName = "help/" + projectName + ".txt";
        try (InputStream is = Intro.class.getClassLoader().getResourceAsStream(fileName);
             var br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
             Stream<String> lines = br.lines()) {
            lines.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.err.format("couldn't locate the project with the name: '%s'%n", projectName);
        } catch (IOException e) {
            System.err.format("%s%n", e);
        }
    }
}
