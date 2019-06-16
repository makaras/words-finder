package com.maciejkaras.wordsfinder.finder.impl;

import com.maciejkaras.wordsfinder.model.FileRank;
import com.maciejkaras.wordsfinder.model.FileWords;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class MostCommonFileWordsFinderTest {

    private MostCommonFileWordsFinder mostCommonFileWordsFinder = new MostCommonFileWordsFinder();

    @Test
    public void shouldIgnoreWhiteSpaceCharacter() {
        //given
        String lineInput = "   word   test  ";
        String fileName = getFileNames()[0];
        Set<String> words = new HashSet<>(Arrays.asList("word", "test", "hello"));
        Set<FileWords> fileWords = new HashSet<>(Collections.singletonList(new FileWords(fileName, words)));
        List<FileRank> fileRanks = new ArrayList<>(Collections.singletonList(new FileRank(fileName, 100d)));

        //when
        List<FileRank> result = mostCommonFileWordsFinder.findFiles(lineInput, fileWords);

        //then
        assertThat(fileRanks, is(result));
    }

    @Test
    public void shouldReturnEmptyListWhenNonFileMatch() {
        //given
        String lineInput = "word toast hi";
        String fileName = getFileNames()[0];
        Set<String> words = new HashSet<>(Arrays.asList("world", "test", "hello"));
        Set<FileWords> fileWords = new HashSet<>(Collections.singletonList(new FileWords(fileName, words)));
        List<FileRank> fileRanks = new ArrayList<>(Collections.emptyList());

        //when
        List<FileRank> result = mostCommonFileWordsFinder.findFiles(lineInput, fileWords);

        //then
        assertThat(fileRanks, is(result));
    }

    @Test
    public void shouldReturnSortedResult() {
        //given
        String lineInput = "word toast hi dog cat elephant";
        Set<FileWords> fileWords = getFileWordsToSort();
        List<FileRank> fileRanks = getSortedFileRank();

        //when
        List<FileRank> result = mostCommonFileWordsFinder.findFiles(lineInput, fileWords);

        //then
        assertThat(fileRanks, is(result));
    }

    @Test
    public void shouldReturnLimitedResult() {
        //given
        String lineInput = "word";
        Set<FileWords> fileWords = getFileWordsToLimit();

        //when
        List<FileRank> result = mostCommonFileWordsFinder.findFiles(lineInput, fileWords);

        //then
        assertThat(result, hasSize(10));
    }

    private Set<FileWords> getFileWordsToSort() {
        String[] fileNames = getFileNames();
        Set<String> oneWordSet = new HashSet<>(Arrays.asList("world", "test", "hello", "dag", "car", "phone"));
        Set<String> twoWordSet = new HashSet<>(Arrays.asList("word", "test", "hello", "dag", "car", "phone"));
        Set<String> threeWordSet = new HashSet<>(Arrays.asList("word", "toast", "hello", "dag", "car", "phone"));
        Set<String> fourWordSet = new HashSet<>(Arrays.asList("word", "toast", "hi", "dag", "car", "phone"));
        Set<String> fiveWordSet = new HashSet<>(Arrays.asList("word", "toast", "hi", "dog", "car", "phone"));
        Set<String> sixWordSet = new HashSet<>(Arrays.asList("word", "toast", "hi", "dog", "cat", "phone"));
        Set<String> sevenWordSet = new HashSet<>(Arrays.asList("word", "toast", "hi", "dog", "cat", "elephant"));
        return new HashSet<>(Arrays.asList(
                new FileWords(fileNames[0], oneWordSet),
                new FileWords(fileNames[1], twoWordSet),
                new FileWords(fileNames[2], threeWordSet),
                new FileWords(fileNames[3], fourWordSet),
                new FileWords(fileNames[4], fiveWordSet),
                new FileWords(fileNames[5], sixWordSet),
                new FileWords(fileNames[6], sevenWordSet)));
    }

    private List<FileRank> getSortedFileRank() {
        String[] fileNames = getFileNames();

        return new ArrayList<>(Arrays.asList(
                new FileRank(fileNames[6], 100d),
                new FileRank(fileNames[5], (double) 5 * 100 / 6),
                new FileRank(fileNames[4], (double) 4 * 100 / 6),
                new FileRank(fileNames[3], (double) 3 * 100 / 6),
                new FileRank(fileNames[2], (double) 2 * 100 / 6),
                new FileRank(fileNames[1], (double) 100 / 6)));
    }

    private Set<FileWords> getFileWordsToLimit() {
        String fileWord = "word";
        String[] fileNames = getFileNames();
        Set<String> fileWordSet = new HashSet<>(Collections.singletonList(fileWord));

        return new HashSet<>(Arrays.asList(
                new FileWords(fileNames[0], fileWordSet),
                new FileWords(fileNames[1], fileWordSet),
                new FileWords(fileNames[2], fileWordSet),
                new FileWords(fileNames[3], fileWordSet),
                new FileWords(fileNames[4], fileWordSet),
                new FileWords(fileNames[5], fileWordSet),
                new FileWords(fileNames[6], fileWordSet),
                new FileWords(fileNames[7], fileWordSet),
                new FileWords(fileNames[8], fileWordSet),
                new FileWords(fileNames[9], fileWordSet),
                new FileWords(fileNames[10], fileWordSet),
                new FileWords(fileNames[11], fileWordSet),
                new FileWords(fileNames[12], fileWordSet),
                new FileWords(fileNames[13], fileWordSet),
                new FileWords(fileNames[14], fileWordSet)));
    }

    private String[] getFileNames() {
        return new String[]{
                "file1.txt", "file2.txt", "file3.txt",
                "file4.txt", "file5.txt", "file6.txt",
                "file7.txt", "file8.txt", "file9.txt",
                "file10.txt", "file11.txt", "file12.txt",
                "file13.txt", "file14.txt", "file15.txt"};
    }
}