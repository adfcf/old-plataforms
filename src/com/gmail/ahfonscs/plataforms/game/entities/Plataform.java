package com.gmail.ahfonscs.plataforms.game.entities;

public abstract class Plataform
  extends Entity
{
  public Plataform(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  public void update() {}
}
