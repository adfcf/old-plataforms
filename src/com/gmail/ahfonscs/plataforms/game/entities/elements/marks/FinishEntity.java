package com.gmail.ahfonscs.plataforms.game.entities.elements.marks;

import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import java.awt.Color;
import java.awt.Graphics2D;


public class FinishEntity
  extends Plataform
{
  public FinishEntity(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(Color.DARK_GRAY);
      graphics.drawRect(getXI(), getYI(), getWidth(), getHeight());
    } 
  }
}
