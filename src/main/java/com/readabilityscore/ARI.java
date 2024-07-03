package com.readabilityscore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ARI {

    private final Path sourceTextFile;
    private long words;
    private long sentences;
    private long characters;

    public ARI(Path sourceTextFile) {
        this.sourceTextFile = sourceTextFile;
    }

    public long countWords() {
        Pattern pattern = Pattern.compile("\\b(\\d{1,3}(\\.\\d{3})*(,\\d+)?|\\w+)\\b");
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.words = words.flatMapToLong(s -> pattern.matcher(s).results().mapToLong(match -> 1))
                    .sum();
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return this.words;
    }

    public long countSentences() {
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.sentences = words.flatMap(s -> Stream.of(s.split("[.?!]"))).count();
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return this.sentences;
    }

    public long countCharacters() {
        Pattern pattern = Pattern.compile("\\S");
        try (Stream<String> words = Files.lines(this.sourceTextFile)) {
            this.characters = words.flatMapToLong(s -> pattern.matcher(s).results().mapToLong(matchResult -> 1))
                    .sum();
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
        return this.characters;
    }

    public double calculateIndex() {
        return 4.71 *
                ((double) this.characters / this.words) + 0.5 *
                ((double) this.words / this.sentences) - 21.43;
    }

    public String getAgeRange() {
        StringBuilder sb = new StringBuilder();
        int idx = (int) Math.ceil(calculateIndex());
        if (idx >= 14) {
            sb.append("18-22");
        } else {
            sb.append(idx + 4).append("-").append(idx + 5);
        }
        return sb.toString();
    }

    public void getFileContent() {
        try (Stream<String> text = Files.lines(this.sourceTextFile)) {
            text.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
