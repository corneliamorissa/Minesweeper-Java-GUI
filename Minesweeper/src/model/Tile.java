package model;

import model.AbstractTile;

import java.beans.ExceptionListener;
import java.util.EmptyStackException;

public class Tile extends AbstractTile {
  private boolean bomb;
  private int xAxis;
  private int yAxis;
  private int num;
  private boolean flag;
  private boolean open;

  public Tile(boolean b)
  {
    bomb = b;
  }
  public Tile(boolean b, int x, int y)
  {
    bomb = b;
    xAxis = x;
    yAxis = y;

  }
  public Tile(boolean b, int x, int y, int num)
  {
    bomb = b;
    xAxis = x;
    yAxis = y;
    this.num = num;
  }


  @Override
  public boolean open() {

    open = true;
    return open;

  }

  @Override
  public void flag() {
      flag = true;
      open = false;

  }

  @Override
  public void unflag() {
    flag = false;
  }

  @Override
    public boolean isFlagged() {
        return flag;
    }

    @Override
    public boolean isExplosive() {
        return bomb;
    }

    @Override
    public boolean isOpened() {
        return open;
    }
}
