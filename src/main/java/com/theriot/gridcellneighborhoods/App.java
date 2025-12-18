package com.theriot.gridcellneighborhoods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {

    private GridParser gridParser;
    private GridAnalyzer gridAnalyzer;

    public App() {
        gridParser = new GridParser();
        gridAnalyzer = new GridAnalyzer();
    }

    public GridParser getGridParser() {
        return gridParser;
    }

    public int calculate(String title, Grid grid, int threshold) {
        StringBuffer stringBuffer = new StringBuffer();

        Grid results = gridAnalyzer.analyze(grid, threshold);
        int countPositive = results.countPositive();

        stringBuffer.append(title + "\n\n");
        stringBuffer.append("Input\n-----\n" + grid + "\n");
        stringBuffer.append("Results\n-----\n" + results + "\n");
        stringBuffer.append("Count is : " + countPositive + "\n***********************\n");
        System.out.println(stringBuffer.toString());

        return countPositive;
    }

    /**
     * It is outside the scope of the instructions, but this jar could take
     * command line arguments that point to a file to make this a callable
     * java program for calculating neighborhood size for input files on
     * the file system.
     */
    public static void main (String[] args) {
    }
}