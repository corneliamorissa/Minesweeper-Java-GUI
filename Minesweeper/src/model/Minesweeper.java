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
    private boolean firstClick;
    private int currentNumOfMines;



    public Minesweeper(){
        this.firstClick = true;
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
        this.bomb = explosionCount;
        board = new AbstractTile[row][col];
        currentNumOfMines = 0;
        Random random = new Random();

        while (currentNumOfMines < bomb ) {

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
                board[x][y].open();
                deactivateFirstTileRule();
                this.viewNotifier.notifyOpened(x, y, bombCount);
                //openNum(x, y);

            }
            else if (board[x][y].isExplosive() && board[x][y].isFlagged())
            {
                board[x][y].open();
                deactivateFirstTileRule();
            }


            else if(board[x][y].isExplosive() && !board[x][y].isFlagged() && firstClick)
            {
                replaceMine(x,y);
                board[x][y] = generateEmptyTile();
                board[x][y].open();
                deactivateFirstTileRule();
            }
            else if(board[x][y].isExplosive() && !board[x][y].isFlagged() && !firstClick)
            {
                board[x][y].open();
                this.viewNotifier.notifyExploded(x,y);
                this.viewNotifier.notifyGameLost();
            }
        }



        }



        public void replaceMine(int x, int y)
        {
            Random random = new Random();

            while (currentNumOfMines < bomb ) {

                int i = random.nextInt(row);
                int j = random.nextInt(col);


                if(i!=x && j!= y && !board[i][j].isExplosive())
                {
                    board[i][j] = generateExplosiveTile();
                }

                currentNumOfMines = currentNumOfMines+ 1;



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
        firstClick = false;
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

    public void openNum(int x,int y)
    {
        int bombCount = 0;

            for (int r = y - 1; r <= y + 1; r++) {
                for (int c = x - 1; c <= x + 1; c++) {
                    if (board[r][c].isExplosive() && isValid(c,r)) {
                        bombCount++;
                    }
                }
            }



}
public void openBomb()
{

}
}
