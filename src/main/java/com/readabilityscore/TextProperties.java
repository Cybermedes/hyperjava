package com.readabilityscore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TextProperties {

    private final Path sourceTextFile;
    private long words;
    private long sentences;
    private long characters;
    private long syllables;
    private long polysyllables;

    public TextProperties(Path sourceTextFile) {
        this.sourceTextFile = sourceTextFile;
    }

    private static long countSyllablesPerWord(String word) {
        return Math.max(1, Pattern.compile("([aiouy]|e(?!$))+")
                .matcher(word)
                .results()
                .count());
    }

    private static boolean isPolysyllable(String word) {
        return countSyllablesPerWord(word) > 2;
    }

    public long countWords() {
        Pattern pattern = Pattern.compile("\\b(\\d{1,3}(\\.\\d{3})*(,\\d+)?|\\w+)\\b");
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.words = words.flatMapToLong(s -> pattern.matcher(s).results().mapToLong(match -> 1))
                    .sum();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return this.words;
    }

    public long countSentences() {
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.sentences = words.flatMap(s -> Stream.of(s.split("[.?!]"))).count();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return this.sentences;
    }

    public long countCharacters() {
        Pattern pattern = Pattern.compile("\\S");
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.characters = words.flatMapToLong(s -> pattern.matcher(s).results().mapToLong(matchResult -> 1))
                    .sum();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return this.characters;
    }

    public long countSyllables() {
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.syllables = words.flatMap(s -> Stream.of(s.split("\\s+")))
                    .mapToLong(TextProperties::countSyllablesPerWord)
                    .sum();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return this.syllables;
    }

    public long countPolysyllables() {
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.polysyllables = words.flatMap(s -> Stream.of(s.split("\\PL+")))
                    .filter(TextProperties::isPolysyllable)
                    .count();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return this.polysyllables;
    }

    public void getFileContent() {
        try (Stream<String> text = Files.lines(this.sourceTextFile)) {
            text.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
