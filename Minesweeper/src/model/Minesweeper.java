package model;

import java.util.Random;

public class Minesweeper extends AbstractMineSweeper{

    private int width;
    private int height;
    private int row;
    private int col;
    private int bomb;
    private Difficulty level;
    private Tile t;
    private AbstractTile[][] bombPlace;
    private AbstractTile[][] openTiles;
    private Tile[][] board;



    @Override
    public int getWidth() {
        return row;
    }

    @Override
    public int getHeight() {
        return col;
    }

    @Override
    public void startNewGame(Difficulty level) {
        if (level == Difficulty.EASY) {
            startNewGame(8, 8, 10);
        }

        if (level == Difficulty.MEDIUM) {
            startNewGame(16, 16, 40);

        }

        if (level == Difficulty.HARD) {
            startNewGame(16, 30, 99);
        }
    }

    public void populateBoard()
    {
        Random random = new Random();

        int r = bomb;

        for(int i = 0; i < row-1; i ++)
        {
            for(int j = 0; j < col - 1 ; j ++)
            {

            }
        }

    }
    public void generateTiles()
    {
    }
    @Override
    public void startNewGame(int row, int col, int explosionCount) {


        this.row = row;
        this.col = col;
        board = new Tile[row][col];

        int currentNumOfMines = 0;
        Random random = new Random();

        while (currentNumOfMines <= explosionCount ) {

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {

                    double probability = random.nextDouble();




        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; i++) {
                int bomb = 0;
                int num = 0;
                if (board[i][j].isExplosive()) {
                    bomb = 1;
                    num = 1;
                    for (int d = i - 1; d <= i + 1; d++) {
                        for (int e = j - 1; e <= j + 1; j++) {
                            if (board[i][j].isExplosive()) {
                                num++;
                                bomb++;
                            }

                        }

                    }
                }
            }


        }
    }

    @Override
    public void toggleFlag(int x, int y) {
        if(t.isFlagged())
        {
            t.unflag();
        }

        else{
            t.flag();
        }


    }

    @Override
    public AbstractTile getTile(int x, int y) {

        return board[x][y];
    }

    @Override
    public void setWorld(AbstractTile[][] world) {
        bombPlace = world;


    }

    @Override
    public void open(int x, int y) {
        bombPlace[x][y].open();

    }

    @Override
    public void flag(int x, int y) {

    }

    @Override
    public void unflag(int x, int y) {

    }

    @Override
    public void deactivateFirstTileRule() {

    }

    @Override
    public AbstractTile generateEmptyTile()
    {
        Tile t = new Tile(false);
        return t;

    }

    @Override
    public AbstractTile generateExplosiveTile() {
        Tile t = new Tile(true);
        return t;
    }
}
