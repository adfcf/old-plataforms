package com.gmail.ahfonscs.plataforms.game.states;

import com.gmail.ahfonscs.plataforms.core.CollisionDetector;
import com.gmail.ahfonscs.plataforms.game.Defaults;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import com.gmail.ahfonscs.plataforms.game.levels.Level;
import com.gmail.ahfonscs.plataforms.game.levels.Unpackager;
import com.gmail.ahfonscs.plataforms.game.states.components.WinnerEvent;
import java.awt.Graphics2D;
import java.awt.Point;



public class GameState
  extends State
{
  private Level level;
  private CollisionDetector detector;
  private WinnerEvent event;
  private Player player;
  private Point deslocation;
  
  public GameState() {
    this.deslocation = new Point();
    
    this.level = Unpackager.loadLevel(Defaults.getCurrentLevel().toPath());
    
    this.player = this.level.getPlayer();
    State.setPlayer(this.player);
    
    this.detector = new CollisionDetector(this.level.getCreatures(), this.level.getPlataforms());
    
    this.deslocation.x = this.player.getXI() - 500;
  }



  
  public void update() {
    this.deslocation.x = this.player.getXI() - 500;
    
    if (this.event == null) {
      
      for (Entity e : this.level.getCreatures()) {
        e.update();
      }
      for (Entity e : this.level.getPlataforms()) {
        e.update();
      }
      
      this.detector.update();
    }
    else {
      
      for (Entity e : this.level.getPlataforms()) {
        e.update();
      }
      
      this.detector.update();
      this.event.update();
      
      return;
    } 
    
    if (this.player.isCollidingMark()) {
      this.event = new WinnerEvent();
    }
  }



  
  public void render(Graphics2D graphics) {
    if (this.event != null) {
      this.event.draw(graphics);
    }
    
    graphics.translate(-this.deslocation.x, -this.deslocation.y);
    
    for (Entity e : this.level.getCreatures()) {
      e.draw(graphics);
    }
    for (Entity e : this.level.getPlataforms()) {
      e.draw(graphics);
    }
    
    graphics.translate(this.deslocation.x, this.deslocation.y);
  }
}
