package com.theriot.gridcellneighborhoods;

public class GridAnalyzer {

    public Grid analyze(Grid grid, int threshold) {
        Grid results = new Grid(grid.height(), grid.width());

        grid.iterate((rowIdx,colIdx) -> {
            if (grid.at(rowIdx,colIdx) > 0) {
                results.annotate(rowIdx,colIdx,threshold);
            }
        });

        return results;
    }
}
