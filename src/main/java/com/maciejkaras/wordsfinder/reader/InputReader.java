package com.maciejkaras.wordsfinder.reader;

import com.maciejkaras.wordsfinder.finder.FileWordsFinder;
import com.maciejkaras.wordsfinder.model.FileRank;
import com.maciejkaras.wordsfinder.model.FileWords;
import com.maciejkaras.wordsfinder.reader.directory.DirectoryReader;

import java.io.File;
import java.util.List;
import java.util.Set;

public class InputReader {

    private static final String NO_FILES_FOUND_MESSAGE = "No matches found";
    private static final String NUMBER_OF_READ_FILES_MESSAGE = "%x file(s) read in directory";
    private static final String NO_DIRECTORY_MESSAGE = "No directory given to index.";
    private static final String QUIT_COMMAND = ":quit";

    private Set<FileWords> fileWords;
    private FileWordsFinder fileWordsFinder;
    private DirectoryReader directoryReader;
    private boolean isTerminated;

    public InputReader(String[] args,
                       FileWordsFinder fileWordsFinder,
                       DirectoryReader directoryReader) {
        validate(args);
        this.fileWordsFinder = fileWordsFinder;
        this.directoryReader = directoryReader;
        fileWords = getFileWords(args[0]);
    }

    public void searchInFiles(String line) {
        if (QUIT_COMMAND.equals(line.trim())) {
            isTerminated = true;
        } else {
            printResults(line);
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(NO_DIRECTORY_MESSAGE);
        }
    }

    private Set<FileWords> getFileWords(String pathInput) {
        Set<FileWords> words = directoryReader.readFiles(new File(pathInput));
        System.out.println(String.format(NUMBER_OF_READ_FILES_MESSAGE, words.size()));
        return words;
    }

    private void printResults(String line) {
        List<FileRank> fileRanks = fileWordsFinder.findFiles(line, fileWords);
        if (fileRanks.isEmpty()) {
            System.out.println(NO_FILES_FOUND_MESSAGE);
        } else {
            fileRanks.forEach(System.out::println);
        }
    }
}
