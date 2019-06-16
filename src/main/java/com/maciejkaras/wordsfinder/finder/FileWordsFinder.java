package com.maciejkaras.wordsfinder.finder;

import com.maciejkaras.wordsfinder.model.FileRank;
import com.maciejkaras.wordsfinder.model.FileWords;

import java.util.List;
import java.util.Set;

public interface FileWordsFinder {

    List<FileRank> findFiles(String line, Set<FileWords> fileWords);

}
