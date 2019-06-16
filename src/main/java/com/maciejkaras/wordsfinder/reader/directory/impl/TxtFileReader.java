package com.maciejkaras.wordsfinder.reader.directory.impl;

import com.maciejkaras.wordsfinder.parser.FileWordParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TxtFileReader {

    private static final String FILE_NOT_READ_MESSAGE = "Could not read file with reason: ";
    private static final String WHITESPACE_CHARACTER = "\\s+";

    Set<String> getFileWords(File file) {
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            return lines
                    .map(fileWord -> fileWord.split(WHITESPACE_CHARACTER))
                    .flatMap(Arrays::stream)
                    .filter(fileWord -> !fileWord.isEmpty())
                    .map(FileWordParser::new)
                    .map(FileWordParser::getWord)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new IllegalArgumentException(FILE_NOT_READ_MESSAGE + e.getMessage());
        }
    }
}
