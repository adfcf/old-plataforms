package com.gmail.ahfonscs.plataforms.game.entities.elements;

import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import java.awt.Color;
import java.awt.Graphics2D;


public class Ground
  extends Plataform
{
  public Ground(int x, int y, int width, int height) {
    super(x, y, width, height);
  }
  
  public Ground(int x, int y, int width, int height, Color color) {
    super(x, y, width, height);
    setColor(color);
  }

  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(getColor());
      graphics.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    } 
  }
}
