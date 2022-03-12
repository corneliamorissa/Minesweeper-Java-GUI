package model;
import model.AbstractMineSweeper;
import model.Difficulty;
import notifier.IGameStateNotifier;
import view.Tile;
import view.TileView;

import java.util.random.*;

public class Minesweeper extends AbstractMineSweeper{

    private int width;
    private int height;
    private int row;
    private int col;
    private Tile bomb;
    private Tile t;
    private Tile[][] visibleBoard;
    private Tile[][] board;



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
        if(level == Difficulty.EASY)
        {
            startNewGame(8,8,10);
        }

        if(level == Difficulty.MEDIUM)
        {
            startNewGame(16,16,40);

        }

        if(level == Difficulty.HARD)
        {
            startNewGame(16,30,99);
        }

    }


    @Override
    public void startNewGame(int row, int col, int explosionCount) {

        board = new Tile[row][col];
        while(explosionCount != 0)
        {
            int a = (int)(Math.random()*row);
            int b = (int)(Math.random()*col);
            if(board[a][b]== null)
            {
                board[a][b] = bomb;
            }
            explosionCount--;
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

        return null;
    }

    @Override
    public void setWorld(AbstractTile[][] world) {


    }

    @Override
    public void open(int x, int y) {


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
        return null;
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return null;
    }
}
