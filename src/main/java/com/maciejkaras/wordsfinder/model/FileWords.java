package com.maciejkaras.wordsfinder.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class FileWords {

    private final String fileName;
    private final Set<String> words;

    public FileWords(String fileName, Set<String> words) {
        this.fileName = fileName;
        this.words = new HashSet<>(words);
    }

    public String getFileName() {
        return fileName;
    }

    public Set<String> getWords() {
        return Collections.unmodifiableSet(words);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileWords)) {
            return false;
        }

        FileWords fileWords = (FileWords) o;
        return Objects.equals(fileName, fileWords.fileName)
                && Objects.equals(words, fileWords.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, words);
    }
}
