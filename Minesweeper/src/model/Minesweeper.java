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
        board = new AbstractTile[col][row];


        int currentNumOfMines = 0;
        Random random = new Random();

        while (currentNumOfMines < explosionCount ) {

            int x = random.nextInt(row);
            int y = random.nextInt(col);


            while(board[y][x] != null)
            {
                x = random.nextInt(row);
                y = random.nextInt(col);
            }
            Tile t = new Tile(true, y,x);
            board[y][x] = t;
            currentNumOfMines = currentNumOfMines+ 1;


        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(board[j][i] ==null)
                {
                    Tile t = new Tile(false,j,i);
                    board[j][i] = t;
                }
            }
            }





            }

    @Override
    public void toggleFlag(int x, int y) {

        if(board[x][y].isFlagged())
        {
            board[x][y].unflag();
        }

        else{
            board[x][y].flag();
        }


    }

    @Override
    public AbstractTile getTile(int x, int y) {
        if((x >= 0) && (y >= 0)  && (x < board.length )  &&  (y < board[0].length ) ){
            return board[x][y];
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
