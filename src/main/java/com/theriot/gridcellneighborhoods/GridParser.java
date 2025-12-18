package com.theriot.gridcellneighborhoods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GridParser {

    /**
     * This unimplemented method could provide another flavor of the parser
     * that locates input on the filesystem using relative filepaths.
     */
    public Grid parseFromFilepath(String filepath) {
        return null;
    }

    public Grid parseFromClasspath(String classpath) throws Exception {
        try(InputStream inputStream = App.class.getResourceAsStream(classpath)) {
            return parse(inputStream);
        }
    }

    public Grid parse(InputStream inputStream) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return Grid.fromStringStream(reader.lines());
        }
    }
}
