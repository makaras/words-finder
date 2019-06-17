package com.maciejkaras.wordsfinder.reader.directory.impl;

import com.maciejkaras.wordsfinder.exception.UnreadableFileException;
import com.maciejkaras.wordsfinder.model.FileWords;
import com.maciejkaras.wordsfinder.reader.directory.DirectoryReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtDirectoryReader implements DirectoryReader {

    private static final String TXT_FILE_EXTENSION = ".txt";
    private static final String NO_TXT_FILE_MESSAGE = "In this folder there is no txt files";
    private static final String FOLDER_NOT_READ_MESSAGE = "Could not read dictionary with reason: ";

    private TxtFileReader txtFileReader;

    public TxtDirectoryReader() {
        txtFileReader = new TxtFileReader();
    }

    @Override
    public Set<FileWords> readFiles(File folder) {
        try (Stream<Path> pathStream = Files.walk(Paths.get(folder.getPath()))) {
            Set<FileWords> fileWords = pathStream
                    .filter(this::isTxtFile)
                    .map(this::getFileWord)
                    .collect(Collectors.toSet());
            validate(fileWords);
            return fileWords;
        } catch (IOException e) {
            throw new UnreadableFileException(FOLDER_NOT_READ_MESSAGE + e.getMessage());
        }
    }

    private boolean isTxtFile(Path path) {
        return path.getFileName().toString().endsWith(TXT_FILE_EXTENSION);
    }

    private FileWords getFileWord(Path path) {
        return new FileWords(path.getFileName().toString(), txtFileReader.getFileWords(new File(path.toString())));
    }

    private void validate(Set<FileWords> fileWords) {
        if (fileWords.isEmpty()) {
            throw new IllegalArgumentException(NO_TXT_FILE_MESSAGE);
        }
    }
}
