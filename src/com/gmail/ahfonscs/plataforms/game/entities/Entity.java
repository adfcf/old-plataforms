package com.gmail.ahfonscs.plataforms.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;





public abstract class Entity
  implements Serializable
{
  private float x;
  private float y;
  private boolean active;
  private int width;
  private int height;
  private Entity[] collisions;
  private Rectangle rectangle;
  private Color color;
  
  public Entity(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    
    this.width = width;
    this.height = height;
    
    this.active = true;
    
    this.rectangle = new Rectangle(x, y, width, height);
    this.color = new Color(0, 0, 0);
    this.collisions = new Entity[4];
  }

  
  public float getX() {
    return this.x;
  }
  public int getXI() {
    return (int)this.x;
  }
  
  public void setX(float x) {
    this.x = x;
  }
  
  public float getY() {
    return this.y;
  }
  public int getYI() {
    return (int)this.y;
  }
  
  public void setY(float y) {
    this.y = y;
  }
  
  public boolean isActive() {
    return this.active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public int getWidth() {
    return this.width;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public int getHeight() {
    return this.height;
  }
  public void setHeight(int height) {
    this.height = height;
  }
  
  public boolean isColliding(Collisions collision) {
    return (this.collisions[collision.index] != null);
  }
  public Entity isCollidingOn(Collisions collision) {
    return this.collisions[collision.index];
  }
  public void setCollisions(Collisions collision, Entity entity) {
    this.collisions[collision.index] = entity;
  }
  
  public Rectangle getBounds() {
    return this.rectangle;
  }
  
  public Color getColor() {
    return this.color;
  }
  public void setColor(Color color) {
    this.color = color;
  }
  
  public abstract void update();
  
  public abstract void draw(Graphics2D paramGraphics2D);
  
  public enum Collisions { ABOVE((byte)0), BELOW((byte)1), LEFT((byte)2), RIGHT((byte)3);
    protected final byte index;
    
    Collisions(byte index) {
      this.index = index;
    } }

}
