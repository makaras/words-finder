package com.maciejkaras.wordsfinder;

import com.maciejkaras.wordsfinder.finder.impl.MostCommonFileWordsFinder;
import com.maciejkaras.wordsfinder.reader.InputReader;
import com.maciejkaras.wordsfinder.reader.directory.impl.TxtDirectoryReader;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader(
                args,
                new MostCommonFileWordsFinder(),
                new TxtDirectoryReader());

        Scanner keyboard = new Scanner(System.in);

        while (!inputReader.isTerminated()) {
            System.out.print("search> ");
            final String line = keyboard.nextLine();
            inputReader.searchInFiles(line);
        }
    }
}
