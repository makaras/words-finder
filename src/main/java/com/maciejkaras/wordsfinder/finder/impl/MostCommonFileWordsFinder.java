package com.maciejkaras.wordsfinder.finder.impl;

import com.maciejkaras.wordsfinder.finder.FileWordsFinder;
import com.maciejkaras.wordsfinder.model.FileRank;
import com.maciejkaras.wordsfinder.model.FileWords;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MostCommonFileWordsFinder implements FileWordsFinder {

    private static final String WHITESPACE_CHARACTER = "\\s+";
    private static final Comparator<FileRank> FILE_RANK_COMPARATOR = Comparator.comparingDouble(FileRank::getPercentage);
    private static final int MAX_RESULT_NUMBER = 10;

    @Override
    public List<FileRank> findFiles(String line, Set<FileWords> fileWords) {
        List<String> providedStrings = Arrays.stream(line.split(WHITESPACE_CHARACTER))
                .filter(providedWord -> !providedWord.isEmpty())
                .collect(Collectors.toList());

        return fileWords.stream()
                .map(fileWord -> getFileRank(fileWord, providedStrings))
                .filter(fileRank -> fileRank.getPercentage() > 0)
                .sorted(FILE_RANK_COMPARATOR.reversed())
                .limit(MAX_RESULT_NUMBER)
                .collect(Collectors.toList());
    }

    private FileRank getFileRank(FileWords fileWords, List<String> providedStrings) {
        return new FileRank(fileWords.getFileName(), calculatePercentage(fileWords.getWords(), providedStrings));
    }

    private Double calculatePercentage(Set<String> fileWords, List<String> providedStrings) {
        double obtained = providedStrings.stream()
                .filter(fileWords::contains)
                .count();

        return obtained * 100 / providedStrings.size();
    }
}
