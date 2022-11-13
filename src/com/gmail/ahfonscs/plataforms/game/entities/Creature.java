package com.gmail.ahfonscs.plataforms.game.entities;



public abstract class Creature
  extends Entity
{
  protected static float gravity = 0.75F;
  protected static float friction = 0.11F;
  
  protected float horizontalSpeed;
  
  protected float verticalSpeed;
  
  protected float horizontalAcceleration;
  protected float verticalAcceleration;
  protected float horizontalMaxSpeed = 3.959F;
  protected float verticalMaxSpeed = 5.65F;
  
  public Creature(int x, int y, int width, int height) {
    super(x, y, width, height);
  }


  
  public void update() {
    this.horizontalSpeed += this.horizontalAcceleration;
    
    if (this.horizontalSpeed > this.horizontalMaxSpeed) {
      this.horizontalSpeed = this.horizontalMaxSpeed;
    } else if (this.horizontalSpeed < -this.horizontalMaxSpeed) {
      this.horizontalSpeed = -this.horizontalMaxSpeed;
    } 
    
    this.verticalSpeed += this.verticalAcceleration;
    this.verticalSpeed += gravity;
    
    if (this.verticalSpeed > this.verticalMaxSpeed) {
      this.verticalSpeed = this.verticalMaxSpeed;
    } else if (this.verticalSpeed < -this.verticalMaxSpeed) {
      this.verticalSpeed = -this.verticalMaxSpeed;
    } 
    
    if (this.horizontalSpeed < 0.0F) {
      if (isColliding(Entity.Collisions.LEFT) && !(isCollidingOn(Entity.Collisions.LEFT) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.SpawnEntity)) {
        if (isColliding(Entity.Collisions.BELOW) && !isCollidingOn(Entity.Collisions.BELOW).equals(isCollidingOn(Entity.Collisions.LEFT))) {
          setX(isCollidingOn(Entity.Collisions.LEFT).getX() + isCollidingOn(Entity.Collisions.LEFT).getWidth());
        }
        
        this.horizontalSpeed = 0.0F;
        this.horizontalAcceleration = 0.0F;
      }
      else {
        
        this.horizontalSpeed += isColliding(Entity.Collisions.BELOW) ? friction : (friction / 2.0F);
        
        if (this.horizontalSpeed > 0.0F) {
          this.horizontalSpeed = 0.0F;
        }
      }
    
    }
    else if (this.horizontalSpeed > 0.0F) {
      if (isColliding(Entity.Collisions.RIGHT) && !(isCollidingOn(Entity.Collisions.RIGHT) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.SpawnEntity)) {
        if (isColliding(Entity.Collisions.BELOW) && !isCollidingOn(Entity.Collisions.BELOW).equals(isCollidingOn(Entity.Collisions.RIGHT))) {
          setX(isCollidingOn(Entity.Collisions.RIGHT).getX() - getWidth());
        }
        
        this.horizontalSpeed = 0.0F;
        this.horizontalAcceleration = 0.0F;
      }
      else {
        
        this.horizontalSpeed -= isColliding(Entity.Collisions.BELOW) ? friction : (friction / 2.0F);
        
        if (this.horizontalSpeed < 0.0F) {
          this.horizontalSpeed = 0.0F;
        }
      } 
    } 

    
    if (this.verticalSpeed < 0.0F) {
      if (isColliding(Entity.Collisions.ABOVE) && !(isCollidingOn(Entity.Collisions.ABOVE) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.SpawnEntity)) {
        setY(isCollidingOn(Entity.Collisions.ABOVE).getY() + isCollidingOn(Entity.Collisions.ABOVE).getHeight() + 1.0F);
        
        this.verticalSpeed = 0.0F;
        this.verticalAcceleration = 0.0F;
      }
    
    } else if (this.verticalSpeed > 0.0F && 
      isColliding(Entity.Collisions.BELOW) && !(isCollidingOn(Entity.Collisions.BELOW) instanceof com.gmail.ahfonscs.plataforms.game.entities.elements.marks.SpawnEntity)) {
      setY(isCollidingOn(Entity.Collisions.BELOW).getY() - getHeight());
      
      this.verticalSpeed = 0.0F;
      this.verticalAcceleration = 0.0F;
    } 


    
    setX(getX() + this.horizontalSpeed);
    setY(getY() + this.verticalSpeed);
    
    this.horizontalAcceleration = 0.0F;
    
    if (this.verticalAcceleration > 0.0F) {
      this.verticalAcceleration--;
    } else if (this.verticalAcceleration < 0.0F) {
      this.verticalAcceleration++;
    } 
  }
}
