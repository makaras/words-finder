package com.maciejkaras.wordsfinder.reader.directory.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class TxtFileReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private TxtFileReader txtFileReader = new TxtFileReader();

    @Test
    public void shouldDistinguishTheSameWords() throws IOException {
        //given
        File file = folder.newFile("file.txt");
        FileWriter writer = new FileWriter(file);
        String toWrite = "Test test.";

        //when
        writer.write(toWrite);
        writer.close();

        //then
        assertThat(txtFileReader.getFileWords(file), hasSize(1));
    }

    @Test
    public void shouldReadMultipleLines() throws IOException {
        //given
        File file = folder.newFile("file.txt");
        FileWriter writer = new FileWriter(file);
        String toWrite = "Test test." + System.getProperty("line.separator") + "Hello hello.";

        //when
        writer.write(toWrite);
        writer.close();

        //then
        assertThat(txtFileReader.getFileWords(file), hasSize(2));
    }

    @Test
    public void shouldReadEmptyFile() throws IOException {
        //given
        File file = folder.newFile("file.txt");

        //when then
        assertThat(txtFileReader.getFileWords(file), hasSize(0));
    }
}