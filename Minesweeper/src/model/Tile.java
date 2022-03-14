package model;

import model.AbstractTile;

public class Tile extends AbstractTile {
  private boolean bomb;
  private int xAxis;
  private int yAxis;
  private boolean flag;

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

  @Override
  public boolean open() {
    return false;
  }

  @Override
  public void flag() {
    flag = true;


  }
  public void setlocation(int x, int y)

  {
    xAxis = x;
    yAxis = y;
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
        return false;
    }
}
