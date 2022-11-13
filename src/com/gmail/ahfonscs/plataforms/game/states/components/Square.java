package com.gmail.ahfonscs.plataforms.game.states.components;

import com.gmail.ahfonscs.plataforms.core.managers.FontManager;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import java.awt.Graphics2D;


public class Square
  extends Entity
{
  private int r;
  private int p;
  private int a;
  private boolean growing;
  
  public Square(int x, int y, int l, int p, int a) {
    super(x, y, l, l);
    
    this.p = p;
    this.a = a;
  }



  
  public void update() {
    if (this.r <= 0) {
      this.growing = true;
    } else if (this.r >= this.p) {
      this.growing = false;
    } 
    
    if (this.growing) {
      this.r++;
      setX(getY() + this.a);
    } else {
      this.r--;
      setX(getY() - this.a);
    } 
  }



  
  public void draw(Graphics2D graphic) {
    Graphics2D graphics = (Graphics2D)graphic.create();
    
    graphics.rotate(Math.toRadians(this.r));
    
    graphics.setColor(FontManager.getTitleColor());
    graphics.fillRect(getXI(), getYI(), getWidth(), getHeight());
    
    graphics.dispose();
  }
}
