package com.gmail.ahfonscs.plataforms.game.levels;

import com.gmail.ahfonscs.plataforms.game.entities.Creature;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;





public final class Level
  implements Serializable
{
  private final List<Creature> creatures;
  private final List<Plataform> plataforms;
  private final String name;
  private final Point spawn;
  private final Point end;
  
  public Level(String name, Point spawn, Point end, List<Creature> creatures, List<Plataform> plataforms) {
    this.name = name;
    
    this.spawn = spawn;
    this.end = end;
    
    this.creatures = creatures;
    this.plataforms = plataforms;
  }

  
  public List<Creature> getCreatures() {
    return this.creatures;
  }
  
  public List<Plataform> getPlataforms() {
    return this.plataforms;
  }
  
  public Player getPlayer() {
    Player player = null;
    for (Entity e : this.creatures) {
      if (e instanceof Player) {
        player = (Player)e;
      }
    } 
    return player;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Point getSpawn() {
    return this.spawn;
  }
  
  public Point getEnd() {
    return this.end;
  }
}
