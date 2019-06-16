package com.maciejkaras.wordsfinder.reader.directory;

import com.maciejkaras.wordsfinder.model.FileWords;

import java.io.File;
import java.util.Set;

public interface DirectoryReader {

    Set<FileWords> readFiles(File folder);

}
