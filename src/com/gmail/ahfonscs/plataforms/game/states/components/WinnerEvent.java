package com.gmail.ahfonscs.plataforms.game.states.components;

import com.gmail.ahfonscs.plataforms.core.managers.FontManager;
import com.gmail.ahfonscs.plataforms.game.states.DivisionState;
import com.gmail.ahfonscs.plataforms.game.states.MainMenuState;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Color;
import java.awt.Graphics2D;


public class WinnerEvent
{
  private int count;
  
  public void update() {
    this.count++;
    
    if (this.count > 70) {
      State.setCurrentState((State)new DivisionState((State)new MainMenuState()));
    }
  }


  
  public void draw(Graphics2D graphic) {
    Graphics2D graphics = (Graphics2D)graphic.create();
    
    graphics.setColor(Color.ORANGE);
    graphics.setFont(FontManager.getTitleFont());
    
    graphics.drawString("Fase Conclu\u00EDda!", 60, State.getCurrentWindow().getHeight() / 2);
    
    graphics.dispose();
  }
}
