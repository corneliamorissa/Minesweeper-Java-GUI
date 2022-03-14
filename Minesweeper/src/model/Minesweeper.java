package model;

import java.util.Random;
import java.util.Arrays;

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
    private AbstractTile[][] board;
    private int flagCounter;




    @Override
    public int getWidth() {
        return col;
    }

    @Override
    public int getHeight() {
        return row;
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
        board = new AbstractTile[row][col];


        int currentNumOfMines = 0;
        Random random = new Random();

        while (currentNumOfMines < explosionCount ) {

            int x = random.nextInt(col);
            int y = random.nextInt(row);


            while(board[y][x] != null)
            {
                x = random.nextInt(col);
                y = random.nextInt(row);
            }
            Tile t = new Tile(true, x,y);
            board[y][x] = t;
            currentNumOfMines = currentNumOfMines+ 1;


        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[i][j] ==null)
                {
                    Tile t = new Tile(false,j,i);
                    board[i][j] = t;
                }
            }
            }





            }

    @Override
    public void toggleFlag(int x, int y) {

        if(board[y][x].isFlagged())
        {
            board[y][x].unflag();
        }

        else{
            board[y][x].flag();
        }


    }

    @Override
    public AbstractTile getTile(int x, int y) {
        if((x >= 0) && (y >= 0)  && (y < board.length )  &&  (x < board[0].length ) ){
            return board[y][x];
        }


        return null;
    }

    @Override
    public void setWorld(AbstractTile[][] world) {
       board = world;


    }

    @Override
    public void open(int x, int y) {
        if((x >= 0) && (y >= 0)  && (x < board.length )  &&  (y < board[0].length ) ){
            if(!board[x][y].isExplosive() && !board[x][y].isFlagged() )
            {
                board[x][y].open();
            }
            else if(board[x][y].isExplosive() && board[x][y].isFlagged() )
            {
                board[x][y].open();
            }
        }



    }

    @Override
    public void flag(int x, int y) {
        board[x][y].flag();
        flagCounter = flagCounter + 1;

    }

    @Override
    public void unflag(int x, int y) {
        board[x][y].unflag();
        flagCounter = flagCounter - 1;

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
