package com.gmail.ahfonscs.plataforms.game.entities.elements.enemies;

import com.gmail.ahfonscs.plataforms.game.entities.Creature;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Color;
import java.awt.Graphics2D;


public class Enemy
  extends Creature
{
  private Player target = State.getPlayer();
  
  public Enemy(int x, int y, int width, int height, Color color) {
    super(x, y, width, height);
    setColor(color);
  }

  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(getColor());
      graphics.fillRect(getXI(), getYI(), getHeight(), getWidth());
    } 
  }


  
  public void update() {
    if (this.target == null) {
      this.target = State.getPlayer();
      
      return;
    } 
    if (getX() > this.target.getX()) {
      setX(getX() - 2.0F);
    } else if (getX() < this.target.getX()) {
      setX(getX() + 2.0F);
    } 
    
    super.update();
  }
}
