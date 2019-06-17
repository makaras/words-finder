package com.maciejkaras.wordsfinder.parser;

public final class FileWordParser {

    private static final String SPECIAL_CHARACTER = "[\\W]";
    private static final String NUMBERS = "[\\d]";
    private static final String EMPTY_STRING = "";

    private final String word;

    public FileWordParser(String fileWord) {
        this.word = fileWord.toLowerCase()
                .replaceAll(SPECIAL_CHARACTER, EMPTY_STRING)
                .replaceAll(NUMBERS, EMPTY_STRING);
    }

    public String getWord() {
        return word;
    }
}
