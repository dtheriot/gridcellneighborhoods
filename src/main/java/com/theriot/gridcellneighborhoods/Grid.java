package com.theriot.gridcellneighborhoods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Grid {

    private int[][] values;

    public Grid(int[][] values) {
        if (!(values.length >= 1)) {
            throw new UnsupportedOperationException("Grid must contain at least one row");
        }

        int columnCount = values[0].length;
        if (!(columnCount >= 1 )) {
            throw new UnsupportedOperationException("Grids must contain at least one column");
        }
        for (int rowIdx=1; rowIdx < values.length; rowIdx++) {
            if (values[rowIdx].length != columnCount) {
                throw new UnsupportedOperationException("All rows must contain the same number of values");
            }
        }

        this.values = values;
    }

    public Grid(int height, int width) {
        values = new int[height][width];
    }

    public int height() {
        return values.length;
    }

    public int width() {
        return values[0].length;
    }

    public int at(int rowIdx, int colIdx) {
        return values[rowIdx][colIdx];
    }

    public void set(int rowIdx, int colIdx, int value) {
        if (rowIdx >= 0 && rowIdx < height()) {
            if (colIdx >= 0 && colIdx < width()) {
                values[rowIdx][colIdx] = value;
            }
        }
    }

    public void iterate(BiConsumer<Integer,Integer> callback) {
        for (int r = 0; r < height(); r++) {
            for (int c = 0; c < width(); c++) {
                callback.accept(r, c);
            }
        }
    }

    public void annotate(int rowIdx1, int colIdx1, int threshold) {
        iterate((rowIdx2,colIdx2) -> {
            if (calcManhattanIndex(rowIdx1,colIdx1,rowIdx2,colIdx2) <= threshold) {
                set(rowIdx2,colIdx2,1);
            }
        });
    }

    private int calcManhattanIndex(int rowIdx1, int colIdx1, int rowIdx2, int colIdx2) {
        return Math.abs(rowIdx1 - rowIdx2) + Math.abs(colIdx1 - colIdx2);
    }

    public int countPositive() {
        AtomicInteger count = new AtomicInteger();
        iterate((rowIdx,colIdx) -> count.addAndGet(at(rowIdx, colIdx) > 0 ? 1 : 0));
        return count.get();
    }

    public static Grid fromStringStream(Stream<String> stringStream) {
        List<int[]> rows = new ArrayList<>();
        stringStream.forEach(string -> {
            rows.add(Arrays.stream(string.split("\\s+")).mapToInt(Integer::valueOf).toArray());
        });
        return new Grid(rows.toArray(new int[][]{}));
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        for (int r=0; r < height(); r++) {
            for (int c = 0; c < width(); c++) {
                sb.append(values[r][c]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
