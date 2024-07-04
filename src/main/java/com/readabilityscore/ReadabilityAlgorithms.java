package com.readabilityscore;

import java.util.ArrayList;
import java.util.List;

public class ReadabilityAlgorithms {

    private final List<Integer> ages = new ArrayList<>();
    private int ariAge;
    private int fleschKincaidAge;
    private int smogAge;
    private int colemanLiauAge;

    public int getAriAge() {
        return ariAge;
    }

    public int getFleschKincaidAge() {
        return fleschKincaidAge;
    }

    public int getSmogAge() {
        return smogAge;
    }

    public int getColemanLiauAge() {
        return colemanLiauAge;
    }

    /**
     * Calculates ARI score and recommended age
     *
     * @return readability score as double
     */
    public double getAriScore(TextProperties text) {
        double score = 4.71 *
                ((double) text.countCharacters() / text.countWords()) + 0.5 *
                ((double) text.countWords() / text.countSentences()) - 21.43;
        ariAge = (int) Math.ceil(score) + 5;
        ages.add(ariAge);
        return score;
    }

    /**
     * Calculates Flesch–Kincaid readability score and recommended age
     *
     * @return readability score as double
     */
    public double getFkScore(TextProperties text) {
        double score = 0.39 *
                ((double) text.countWords() / text.countSentences()) + 11.8 *
                ((double) text.countSyllables() / text.countWords()) - 15.59;
        fleschKincaidAge = (int) Math.ceil(score) + 5;
        ages.add(fleschKincaidAge);
        return score;
    }

    /**
     * Calculates SMOG (Simple Measure of Gobbledygook) score and recommended age
     *
     * @return readability score as double
     */
    public double getSmogScore(TextProperties text) {
        double score = 1.043 *
                Math.sqrt(text.countPolysyllables() * ((double) 30 / text.countSentences())) + 3.1291;
        smogAge = (int) Math.ceil(score) + 5;
        ages.add(smogAge);
        return score;
    }

    /**
     * Calculates Coleman-Liau score and recommended age
     *
     * @return readability score as double
     */
    public double getClScore(TextProperties text) {
        double L = ((double) text.countCharacters() / text.countWords()) * 100;
        double S = ((double) text.countSentences() / text.countWords()) * 100;
        double score = 0.0588 * L - 0.296 * S - 15.8;
        colemanLiauAge = (int) Math.ceil(score) + 6;
        ages.add(colemanLiauAge);
        return score;
    }

    /**
     * Calculates the average age of reader from all calculated algorithms
     *
     * @return double representing average age of reader
     */
    public double getAverageAge() {
        double sum = 0;
        for (int i : ages) {
            sum += i;
        }
        return sum / ages.size();
    }
}
