package com.theriot.gridcellneighborhoods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(new int[][]{
                new int[] { 1, 2, 3 },
                new int[] { 1, 2, 3 }});
    }

    @Test
    void testConstructor() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new Grid(new int[][]{});
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            new Grid(new int[][]{new int[] {}});
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            new Grid(new int[][]{new int[] { 1, 2 }, new int[] { 1 }});
        });

        new Grid(new int[][]{new int[] { 1 }, new int[] { 1 }});
    }

    @Test
    void testHeight() {
        assertEquals(2, grid.height());
    }

    @Test
    void testWidth() {
        assertEquals(3, grid.width());
    }

    @Test
    void testFromStringStream() {
        Grid grid = Grid.fromStringStream(Stream.of("0 0 0", "0 1 0", "0 0 0"));
        assertEquals(3, grid.width());
        assertEquals(3, grid.height());
    }
}
