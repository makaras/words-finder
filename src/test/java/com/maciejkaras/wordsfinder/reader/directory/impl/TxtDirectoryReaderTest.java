package com.maciejkaras.wordsfinder.reader.directory.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TxtDirectoryReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private TxtDirectoryReader txtDirectoryReader = new TxtDirectoryReader();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWrongPathProvided() {
        //given
        File file = mock(File.class);
        String wrongPath = "C:\\w@o#g-t$s%-p^t&";

        //when
        when(file.getPath()).thenReturn(wrongPath);

        //then
        txtDirectoryReader.readFiles(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEmptyDictionaryProvided() {
        //given
        File mockFolder = folder.getRoot();

        //when then
        txtDirectoryReader.readFiles(mockFolder);
    }

    @Test
    public void shouldFilterNonTxtFiles() throws IOException {
        //given
        File mockFolder = folder.getRoot();
        String fileOneName = "file1.txt";
        String fileTwoName = "file2.txt";
        String fileThreeName = "file3.xlsx";

        //when
        folder.newFile(fileOneName);
        folder.newFile(fileTwoName);
        folder.newFile(fileThreeName);

        //then
        assertThat(txtDirectoryReader.readFiles(mockFolder), hasSize(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIsNoTxtFilesInDictionary() throws IOException {
        //given
        File mockFolder = folder.getRoot();
        String fileOneName = "file1.log";
        String fileTwoName = "file2.dll";
        String fileThreeName = "file3.xlsx";

        //when
        folder.newFile(fileOneName);
        folder.newFile(fileTwoName);
        folder.newFile(fileThreeName);

        //then
        txtDirectoryReader.readFiles(mockFolder);
    }

    @Test
    public void shouldReturnFileWordWhenFilePathIsProvided() throws IOException {
        //given
        String fileName = "file1.txt";

        //when
        File mockFile = folder.newFile(fileName);

        //then
        assertThat(txtDirectoryReader.readFiles(mockFile), hasSize(1));
    }
}