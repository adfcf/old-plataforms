package com.gmail.ahfonscs.plataforms.game.entities.elements;

import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class MobileGround
  extends Plataform
{
  private Direction directionX = Direction.STATIC;
  private Direction directionY = Direction.STATIC;
  
  private final int miX;
  
  private final int maX;
  private final int miY;
  private final int maY;
  
  public MobileGround(int x, int y, int width, int height, Point min, Point max) {
    super(x, y, width, height);
    
    this.miX = min.x;
    this.miY = min.y;
    this.maX = max.x;
    this.maY = max.y;
  }


  
  public MobileGround(int x, int y, int width, int height, String min, String max) {
    super(x, y, width, height);

    
    int commaIndexMin = min.indexOf(",");
    int commaIndexMax = max.indexOf(",");
    
    String minXS = min.substring(0, commaIndexMin);
    String minYS = min.substring(commaIndexMin + 1, min.length());
    
    this.miX = Integer.parseInt(minXS);
    this.miY = Integer.parseInt(minYS);
    
    String maxXS = max.substring(0, commaIndexMax);
    String maxYS = max.substring(commaIndexMax + 1, max.length());
    
    this.maX = Integer.parseInt(maxXS);
    this.maY = Integer.parseInt(maxYS);
  }

  
  public MobileGround(int x, int y, int width, int height, String min, String max, Color color) {
    this(x, y, width, height, min, max);
    setColor(color);
  }

  
  public void update() {
    super.update();
    
    if (this.directionX == Direction.STATIC) {
      this.directionX = (getX() <= this.miX || (getX() >= this.miX && getX() <= this.maX)) ? Direction.RIGHT : Direction.LEFT;
    }
    
    if (this.directionX == Direction.RIGHT && this.miX != this.maX) {
      setX(getX() + 1.0F);
      
      if (getX() > this.maX) {
        this.directionX = Direction.STATIC;
      }
    }
    else if (this.directionX == Direction.LEFT && this.miX != this.maX) {
      setX(getX() - 1.0F);
      
      if (getX() < this.miX) {
        this.directionX = Direction.STATIC;
      }
    } 
    
    if (this.directionY == Direction.STATIC) {
      this.directionY = (getY() <= this.miY || (getY() >= this.miY && getY() <= this.maY)) ? Direction.DOWN : Direction.UP;
    }
    
    if (this.directionY == Direction.DOWN && this.miY != this.maY) {
      setY(getY() + 1.0F);
      
      if (getY() > this.maY) {
        this.directionY = Direction.STATIC;
      }
    }
    else if (this.directionY == Direction.UP && this.miY != this.maY) {
      setY(getY() - 1.0F);
      
      if (getY() < this.miY) {
        this.directionY = Direction.STATIC;
      }
    } 
    
    if (isColliding(Entity.Collisions.ABOVE)) {
      if (this.directionX == Direction.RIGHT) {
        isCollidingOn(Entity.Collisions.ABOVE).setX((isCollidingOn(Entity.Collisions.ABOVE).getXI() + 1));
      } else if (this.directionX == Direction.LEFT) {
        isCollidingOn(Entity.Collisions.ABOVE).setX((isCollidingOn(Entity.Collisions.ABOVE).getXI() - 1));
      } 
    }
  }


  
  public void draw(Graphics2D graphics) {
    if (isActive()) {
      graphics.setColor(getColor());
      graphics.fillRect(getXI(), getYI(), getWidth(), getHeight());
    } 
  }
  
  protected enum Direction
  {
    UP, DOWN, LEFT, RIGHT, STATIC;
  }
}
