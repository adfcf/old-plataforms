package com.gmail.ahfonscs.plataforms.game.entities.elements.marks;

import com.gmail.ahfonscs.plataforms.game.Defaults;
import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Graphics2D;



public class SpawnEntity
  extends Plataform
{
  public SpawnEntity(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  
  public void draw(Graphics2D graphics) {
    if (State.getCurrentState() instanceof com.gmail.ahfonscs.plataforms.game.states.EditorState) {
      graphics.setColor(Defaults.getPersonColor());
      graphics.drawRect(getXI(), getYI(), getWidth(), getHeight());
    } 
  }
}
