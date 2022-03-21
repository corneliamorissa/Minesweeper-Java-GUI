package model;

import notifier.IGameStateNotifier;
import view.MinesweeperView;

import java.time.Duration;
import java.util.Random;
import java.util.Arrays;
import java.lang.Exception;

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
    private long time;



    public Minesweeper(){
    }


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
        this.viewNotifier.notifyTimeElapsedChanged(Duration.ofSeconds(time));
        if (level == Difficulty.EASY) {
            startNewGame(8, 8, 10);
            this.flagCounter = 10;
            this.viewNotifier.notifyNewGame(8,8);

        }

        if (level == Difficulty.MEDIUM) {
            startNewGame(16, 16, 40);
            this.flagCounter = 40;
            this.viewNotifier.notifyNewGame(16,16);

        }

        if (level == Difficulty.HARD) {
            startNewGame(16, 30, 99);
            this.flagCounter = 99;
            this.viewNotifier.notifyNewGame(16,30);
        }
        this.viewNotifier.notifyFlagCountChanged(flagCounter);
    }

    public boolean isValid(int x, int y)
    {
        if((x >= 0) && (y >= 0)  && (y < row )  &&  (x < col) ){
            return true;
        }
        else{
            return false;
        }
    }

    public void startTimer()
    {
        this.time = System.currentTimeMillis();
        int i = 0;
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

                        Tile t = new Tile(false, j, i);
                        board[i][j] = t;


                }
            }
            }



            this.viewNotifier.notifyNewGame(row,col);

            }

    @Override
    public void toggleFlag(int x, int y) {

        int initialCount = flagCounter;
        if(board[y][x].isFlagged())
        {

                board[y][x].unflag();
                this.viewNotifier.notifyUnflagged(x, y);
                flagCounter = flagCounter + 1;
                this.viewNotifier.notifyFlagCountChanged(flagCounter);



        }

        else {
            if(flagCounter>=0) {
                board[y][x].flag();
                this.viewNotifier.notifyFlagged(x, y);
                flagCounter = flagCounter - 1;
                this.viewNotifier.notifyFlagCountChanged(flagCounter);
            }
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

        if (x < 0 || x >= row || y < 0 || y >= col) {

        }
        else {
            if (!board[x][y].isExplosive() && !board[x][y].isFlagged()) {

                int bombs = bombCount(x,y);
                if(bombs == 0)
                {
                    board[x][y].open();
                    this.viewNotifier.notifyOpened(x, y, 0);
                    for (int r = y - 1; r <= y + 1; r++) {
                        for (int c = x - 1; c <= x + 1; c++) {
                            if (isValid(c,r) && !board[r][c].isExplosive()) {
                                    int otherBomb = bombCount(r,c);
                                    board[r][c].open();
                                    this.viewNotifier.notifyOpened(r, c, otherBomb);
                                }
                            }
                            }
                        }
                else {
                    board[x][y].open();
                    this.viewNotifier.notifyOpened(x, y, bombs);

                }

            }
            else if (board[x][y].isExplosive() && board[x][y].isFlagged())
            {
                board[x][y].open();
            }


            else if(board[x][y].isExplosive() && !board[x][y].isFlagged())
            {
                board[x][y].open();
                this.viewNotifier.notifyExploded(x,y);
                this.viewNotifier.notifyGameLost();
            }
        }


        }





    @Override
    public void flag(int x, int y) {
        if (!board[y][x].isOpened()) {
            board[y][x].flag();


            /*this.viewNotifier.notifyFlagged(x, y);
            flagCounter = flagCounter - 1;
            this.viewNotifier.notifyFlagCountChanged(flagCounter);*/
        }


    }

    @Override
    public void unflag(int x, int y) {
        if (board[y][x].isFlagged()) {
            board[y][x].unflag();

            /*this.viewNotifier.notifyUnflagged(x,y);
            flagCounter = flagCounter + 1;
            this.viewNotifier.notifyFlagCountChanged(flagCounter);*/
        }


    }

    @Override
    public void deactivateFirstTileRule() {
        this.viewNotifier.notifyTimeElapsedChanged(Duration.ofSeconds(60));

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


public int bombCount(int x, int y)
{
    int bombCount = 0;

    for (int r = y - 1; r <= y + 1; r++) {
        for (int c = x - 1; c <= x + 1; c++) {
            if (isValid(c,r)) {
                if(board[r][c].isExplosive())
                {
                    bombCount++;
                }
            }
        }
    }
    return( bombCount);

}
}
