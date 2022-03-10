package model;
import model.AbstractMineSweeper;
import model.Difficulty;
import view.Tile;
import view.TileView;

import java.util.random.*;

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
        this.level = level;
        if(level == Difficulty.EASY)
        {
            this.row = 8;
            this.col = 8;
            bomb = 10;
        }

        if(level == Difficulty.MEDIUM)
        {
            this.row = 16;
            this.col = 16;
            bomb = 40;

        }

        if(level == Difficulty.HARD)
        {
            this.row = 16;
            this.col = 30;
            bomb = 99;
        }
    }

    public void populateBoard()
    {


    }
    @Override
    public void startNewGame(int row, int col, int explosionCount) {

        this.row = row;
        this.col = col;
        bomb = explosionCount;

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
        return null;
    }

    @Override
    public AbstractTile generateExplosiveTile() {
        return null;
    }
}
