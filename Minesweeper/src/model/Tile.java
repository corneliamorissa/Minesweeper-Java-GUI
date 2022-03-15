package model;

import model.AbstractTile;

import java.beans.ExceptionListener;
import java.util.EmptyStackException;

public class Tile extends AbstractTile {
  private boolean bomb;
  private int xAxis;
  private int yAxis;
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

  @Override
  public boolean open() {

    open = true;
    return open;
  }

  @Override
  public void flag() {
      flag = true;
      //this.viewNotifier.notifyFlagged();


  }
  public void setlocation(int x, int y)

  {
    xAxis = x;
    yAxis = y;

  }

  @Override
  public void unflag() {
    flag = false;
    //this.viewNotifier.notifyUnflagged();
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
