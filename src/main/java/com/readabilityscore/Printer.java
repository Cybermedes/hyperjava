package com.readabilityscore;

public abstract class Printer {
    private final static ReadabilityAlgorithms readability = new ReadabilityAlgorithms();

    static void printAriResults(TextProperties text) {
        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).%n",
                readability.getAriScore(text),
                readability.getAriAge());
    }

    static void printFkResults(TextProperties text) {
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d-year-olds).%n",
                readability.getFkScore(text),
                readability.getFleschKincaidAge());
    }

    static void printSmogResults(TextProperties text) {
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).%n",
                readability.getSmogScore(text),
                readability.getSmogAge());
    }

    static void printClResults(TextProperties text) {
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).%n",
                readability.getClScore(text),
                readability.getColemanLiauAge());
    }

    static void printAverageAge() {
        System.out.printf("%nThis test should be understood in average by %.2f-years-old.%n",
                readability.getAverageAge());
    }
}
