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
    private int b;
    private Tile bomb;
    private Tile t;
    private Tile[][] visibleBoard;
    private Tile[][] board;



    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void startNewGame(Difficulty level) {
        if(level == Difficulty.EASY)
        {
            this.row = 8;
            this.col = 8;
            b = 10;
            populateBoard();
        }

        if(level == Difficulty.MEDIUM)
        {
            this.row = 16;
            this.col = 16;
            b = 40;
            populateBoard();

        }

        if(level == Difficulty.HARD)
        {
            this.row = 16;
            this.col = 30;
            b = 99;
            populateBoard();
        }

    }

    public void populateBoard()
    {
        Tile[][] board = new Tile[row][col];
        while(b != 0)
        {
            int a = (int)(Math.random()*row);
            int b = (int)(Math.random()*col);
            if(board[a][b]== null)
            {
                board[a][b] = bomb;
            }
            b--;
        }

    }
    @Override
    public void startNewGame(int row, int col, int explosionCount) {

        this.row = row;
        this.col = col;
        b = explosionCount;

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
