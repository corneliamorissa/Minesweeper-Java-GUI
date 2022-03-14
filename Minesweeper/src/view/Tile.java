package view;

import model.AbstractTile;

public class Tile extends AbstractTile {
  private boolean bomb;

  public Tile(boolean b)
  {
    bomb = b;
  }

  @Override
  public boolean open() {
    return false;
  }

  @Override
  public void flag() {

  }

  @Override
  public void unflag() {

  }


  @Override
    public boolean isFlagged() {
        return false;
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
