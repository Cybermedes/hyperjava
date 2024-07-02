package com.simplesearchengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> savedData = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String samples = scanner.nextLine();
            String dataToSearch = scanner.nextLine();
            Collections.addAll(savedData, samples.split("\\s+"));
            System.out.println(getIndex(savedData, dataToSearch));
        }
    }

    public static String getIndex(List<String> samples, String searchWord) {
        if (samples.contains(searchWord)) {
            return String.valueOf(samples.indexOf(searchWord) + 1);
        } else {
            return "Not found";
        }
    }
}
