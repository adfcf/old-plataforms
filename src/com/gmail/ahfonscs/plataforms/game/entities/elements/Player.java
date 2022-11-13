package com.gmail.ahfonscs.plataforms.game.entities.elements;

import com.gmail.ahfonscs.plataforms.core.managers.InputManager;
import com.gmail.ahfonscs.plataforms.game.Defaults;
import com.gmail.ahfonscs.plataforms.game.entities.Creature;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import java.awt.Graphics2D;



public final class Player
  extends Creature
{
  private static final InputManager INPUT = InputManager.getInstance();
  
  private boolean classicMode;
  private final int sx;
  private final int sy;
  
  public Player(int x, int y, int width, int height) {
    super(x, y, width, height);
    
    this.sx = x;
    this.sy = y;
  }



  
  public void update() {
    setColor(Defaults.getPersonColor());
    
    if (INPUT.isPressed(80)) {
      this.classicMode = !this.classicMode;
    }
    if (INPUT.isPressed(82)) {
      setX(this.sx);
      setY(this.sy);
    } 
    
    if (!this.classicMode) {
      
      if (INPUT.isGoingUp() && isColliding(Entity.Collisions.BELOW)) {
        this.verticalAcceleration = -13.6F;
      }
      if (INPUT.isGoingLeft()) {
        this.horizontalAcceleration = -0.25F;
      }
      if (INPUT.isGoingRight()) {
        this.horizontalAcceleration = 0.25F;
      }
    }
    else {
      
      if (INPUT.isGoingUp() && !isColliding(Entity.Collisions.ABOVE)) {
        setY(getY() - 5.0F);
      }
      if (INPUT.isGoingLeft() && !isColliding(Entity.Collisions.LEFT)) {
        setX(getX() - 5.0F);
      }
      if (INPUT.isGoingRight() && !isColliding(Entity.Collisions.RIGHT)) {
        setX(getX() + 5.0F);
      }
      if (INPUT.isGoingDown() && !isColliding(Entity.Collisions.BELOW)) {
        setY(getY() + 5.0F);
      }
      
      return;
    } 
    try {
      super.update();
    } catch (NullPointerException e) {
      System.err.println("Situa\u00E7\u00E3o Inconsistente!");
    } 
  }


  
  public boolean isCollidingMark() {
    if (isCollidingOn(Entity.Collisions.ABOVE) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.FinishEntity)
      return true; 
    if (isCollidingOn(Entity.Collisions.LEFT) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.FinishEntity)
      return true; 
    if (isCollidingOn(Entity.Collisions.RIGHT) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.FinishEntity)
      return true; 
    if (isCollidingOn(Entity.Collisions.BELOW) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.FinishEntity) {
      return true;
    }
    
    return false;
  }

  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(getColor());
      graphics.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
    } 
  }
}
