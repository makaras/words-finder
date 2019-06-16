package com.maciejkaras.wordsfinder.model;

import java.util.Objects;

public final class FileRank {

    private final String fileName;
    private final Double percentage;

    public FileRank(String fileName, Double percentage) {
        this.fileName = fileName;
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FileRank)) {
            return false;
        }

        FileRank fileRank = (FileRank) o;
        return Objects.equals(fileName, fileRank.fileName)
                && Double.compare(percentage, fileRank.percentage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, percentage);
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f%%", fileName, percentage);
    }
}
