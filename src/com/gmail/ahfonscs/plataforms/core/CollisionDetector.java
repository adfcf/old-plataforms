package com.gmail.ahfonscs.plataforms.core;

import com.gmail.ahfonscs.plataforms.game.entities.Creature;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import com.gmail.ahfonscs.plataforms.game.entities.elements.enemies.Enemy;
import java.util.ArrayList;
import java.util.List;





public final class CollisionDetector
{
  private final List<Creature> creatures;
  private final List<Plataform> plataforms;
  private Player player;
  private List<Enemy> enemies;
  
  public CollisionDetector(List<Creature> creatures, List<Plataform> plataforms) {
    this.creatures = creatures;
    this.plataforms = plataforms;
    
    this.enemies = new ArrayList<>();
    
    for (Entity e : creatures) {
      if (e instanceof Enemy) {
        this.enemies.add((Enemy)e); continue;
      }  if (e instanceof Player) {
        this.player = (Player)e;
      }
    } 
  }


  
  public void update() {
    for (Entity e1 : this.creatures) {
      for (Entity e2 : this.plataforms) {
        checkAboveCollisions(e1, e2);
        checkBelowCollisions(e1, e2);
        checkLeftCollisions(e1, e2);
        checkRightCollisions(e1, e2);
      } 
    } 
    
    for (Enemy e : this.enemies) {
      checkAboveCollisions((Entity)this.player, (Entity)e);
      checkBelowCollisions((Entity)this.player, (Entity)e);
      checkLeftCollisions((Entity)this.player, (Entity)e);
      checkRightCollisions((Entity)this.player, (Entity)e);
    } 
  }


  
  private void checkLeftCollisions(Entity e1, Entity e2) {
    if (e1.getYI() + e1.getHeight() - 5 > e2.getYI() && e1.getYI() < e2.getYI() + e2.getHeight()) {
      
      if (e1.getXI() <= e2.getXI() + e2.getWidth() && e1.getXI() >= e2.getXI()) {
        e1.setCollisions(Entity.Collisions.LEFT, e2);
        e2.setCollisions(Entity.Collisions.RIGHT, e1);
      }
      else if (e2.equals(e1.isCollidingOn(Entity.Collisions.LEFT))) {
        e1.setCollisions(Entity.Collisions.LEFT, null);
        e2.setCollisions(Entity.Collisions.RIGHT, null);
      }
    
    } else if (e2.equals(e1.isCollidingOn(Entity.Collisions.LEFT))) {
      e1.setCollisions(Entity.Collisions.LEFT, null);
      e2.setCollisions(Entity.Collisions.RIGHT, null);
    } 
  }


  
  private void checkRightCollisions(Entity e1, Entity e2) {
    if (e1.getYI() + e1.getHeight() - 5 >= e2.getYI() && e1.getYI() <= e2.getYI() + e2.getHeight()) {
      
      if (e1.getXI() + e1.getWidth() >= e2.getXI() && e1.getXI() <= e2.getXI() + e2.getWidth() - 3) {
        e1.setCollisions(Entity.Collisions.RIGHT, e2);
        e2.setCollisions(Entity.Collisions.LEFT, e1);
      }
      else if (e2.equals(e1.isCollidingOn(Entity.Collisions.RIGHT))) {
        e1.setCollisions(Entity.Collisions.RIGHT, null);
        e2.setCollisions(Entity.Collisions.LEFT, null);
      }
    
    } else if (e2.equals(e1.isCollidingOn(Entity.Collisions.RIGHT))) {
      e1.setCollisions(Entity.Collisions.RIGHT, null);
      e2.setCollisions(Entity.Collisions.LEFT, null);
    } 
  }


  
  private void checkBelowCollisions(Entity e1, Entity e2) {
    if (e1.getYI() + e1.getHeight() >= e2.getYI() && e1.getYI() <= e2.getYI()) {
      
      if (e1.getXI() + e1.getWidth() > e2.getXI() && e1.getXI() < e2.getXI() + e2.getWidth()) {
        e1.setCollisions(Entity.Collisions.BELOW, e2);
        e2.setCollisions(Entity.Collisions.ABOVE, e1);
      }
      else if (e2.equals(e1.isCollidingOn(Entity.Collisions.BELOW))) {
        e1.setCollisions(Entity.Collisions.BELOW, null);
        e2.setCollisions(Entity.Collisions.ABOVE, null);
      }
    
    } else if (e2.equals(e1.isCollidingOn(Entity.Collisions.BELOW))) {
      e1.setCollisions(Entity.Collisions.BELOW, null);
      e2.setCollisions(Entity.Collisions.ABOVE, null);
    } 
  }


  
  private void checkAboveCollisions(Entity e1, Entity e2) {
    if (e1.getXI() + e1.getWidth() > e2.getXI() + 5 && e1.getXI() + 5 < e2.getXI() + e2.getWidth()) {
      
      if (e1.getYI() < e2.getYI() + e2.getHeight() && e1.getYI() > e2.getYI() + 5) {
        e1.setCollisions(Entity.Collisions.ABOVE, e2);
        e2.setCollisions(Entity.Collisions.BELOW, e1);
      }
      else if (e2.equals(e1.isCollidingOn(Entity.Collisions.ABOVE))) {
        e1.setCollisions(Entity.Collisions.ABOVE, null);
        e2.setCollisions(Entity.Collisions.BELOW, null);
      }
    
    } else if (e2.equals(e1.isCollidingOn(Entity.Collisions.ABOVE))) {
      e1.setCollisions(Entity.Collisions.ABOVE, null);
      e2.setCollisions(Entity.Collisions.BELOW, null);
    } 
  }
}
