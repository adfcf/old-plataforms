package com.gmail.ahfonscs.plataforms.game.entities.elements.enemies;

import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import java.awt.Graphics2D;


public class Projectile
  extends Entity
{
  private boolean rightDirection;
  
  public Projectile(int x, int y, int width, int height, boolean right) {
    super(x, y, width, height);
    this.rightDirection = right;
  }

  
  public void update() {
    if (this.rightDirection) {
      setX(getX() + 9.0F);
    } else {
      setX(getX() - 9.0F);
    } 
  }

  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(getColor());
      graphics.fillRect(getXI(), getYI(), getWidth(), getHeight());
    } 
  }
}
