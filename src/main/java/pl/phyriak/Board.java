package pl.phyriak;

public class Board {

    private int size;
    private int percentChanceOfAliveAtInit;

    private boolean[][] grid;

    public Board(int size, int percentOfAlive) {
        this.size = size;
        this.percentChanceOfAliveAtInit = percentOfAlive;

        initGrid();
    }

    public Board(boolean[][] grid) {
        this.grid = grid;
        this.size = this.grid.length;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((grid[i][j] ? "x" : "-") + "  ");
            }
            System.out.println();
        }
    }

    private void initGrid() {
        grid = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = getRandomCellState();
            }
        }
    }

    private boolean getRandomCellState() {
        return Math.random() >= (double)(100 - percentChanceOfAliveAtInit)/100;
    }

    public boolean tick() {

        boolean isAnyAlive = false;
        boolean [][] newGrid = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newGrid[i][j] = isCellAliveInNextGen(i, j);

                if(newGrid[i][j] && !isAnyAlive) {
                    isAnyAlive = true;
                }
            }
        }

        grid = newGrid;

        return isAnyAlive;
    }

    private boolean isCellAliveInNextGen(int x, int y) {
        int neighbours = getCountOfAliveNeighbours(x, y);

        switch (neighbours) {
            case 2:
                return grid[x][y];
            case 3:
                return true;
            default:
                return false;
        }
    }

    protected int getCountOfAliveNeighbours(int x, int y) {

        int aliveNeighbours = 0;

        for(int i = x - 1; i <= x + 1; i++) {
            if (i >= 0 && i < size) {
                for (int j = y - 1; j <= y + 1; j++)
                    if (j >= 0 && j < size) {
                        if (!(i == x && j == y) && grid[i][j]) {
                            aliveNeighbours++;
                        }
                    }
            }
        }

        return aliveNeighbours;
    }
}
