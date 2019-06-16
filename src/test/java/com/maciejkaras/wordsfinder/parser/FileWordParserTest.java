package com.maciejkaras.wordsfinder.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileWordParserTest {

    @Test
    public void shouldIgnoreSpecialCharacters() {
        //given
        final String testDotWord = "word.";
        final String testQuestionWord = "word?";
        final String testExclamationWord = "word!";
        final String testCommaWord = "word,";

        //when
        FileWordParser fileDotWordParser = new FileWordParser(testDotWord);
        FileWordParser fileQuestionWordParser = new FileWordParser(testQuestionWord);
        FileWordParser fileExclamationWordParser = new FileWordParser(testExclamationWord);
        FileWordParser fileCommaWordParser = new FileWordParser(testCommaWord);

        //then
        assertEquals(fileDotWordParser.getWord(), "word");
        assertEquals(fileQuestionWordParser.getWord(), "word");
        assertEquals(fileExclamationWordParser.getWord(), "word");
        assertEquals(fileCommaWordParser.getWord(), "word");
    }

    @Test
    public void shouldIgnoreCapitalCharacters() {
        //given
        final String testWord = "Word";

        //when
        FileWordParser fileWordParser = new FileWordParser(testWord);

        //then
        assertEquals(fileWordParser.getWord(), "word");
    }
}