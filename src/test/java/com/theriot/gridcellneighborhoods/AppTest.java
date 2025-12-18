package com.theriot.gridcellneighborhoods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    private void test(String title, String classpath, int threshold, int expected) throws Exception {
        Grid grid = app.getGridParser().parseFromClasspath(title);
        assertEquals(expected, app.calculate(classpath, grid, threshold));
    }

    @Test
    void testGrids() throws Exception {
        test("/example01.txt", "Example 1", 3,25);
        test("/example02.txt", "Example 2", 3,21);
        test("/example03.txt", "Example 3", 2,26);
        test("/example04.txt", "Example 4", 2,22);
    }
}
