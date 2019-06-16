package com.maciejkaras.wordsfinder.reader;

import com.maciejkaras.wordsfinder.finder.impl.MostCommonFileWordsFinder;
import com.maciejkaras.wordsfinder.reader.directory.impl.TxtDirectoryReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class InputReaderTest {

    @Mock
    private MostCommonFileWordsFinder mostCommonFileWordsFinder;

    @Mock
    private TxtDirectoryReader directoryReader;

    @Test
    public void shouldTerminatedProgramOnCommand() {
        //given
        String[] args = new String[]{"test"};
        InputReader inputReader = new InputReader(args, mostCommonFileWordsFinder, directoryReader);

        //when
        inputReader.searchInFiles(":quit");

        //then
        assertTrue(inputReader.isTerminated());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNonArgumentProvided() {
        //given
        String[] emptyArray = new String[]{};

        //when then
        new InputReader(emptyArray, mostCommonFileWordsFinder, directoryReader);
    }
}