package com.gmail.ahfonscs.plataforms.game;

import com.gmail.ahfonscs.plataforms.core.Game;
import com.gmail.ahfonscs.plataforms.game.states.MainMenuState;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Graphics2D;


public class PlataformsGame
  extends Game
{
  protected void onLoad() {
    State.setCurrentState((State)new MainMenuState());
  }


  
  protected void onUpdate() {
    if (State.getCurrentState() != null) {
      State.getCurrentState().update();
    }
  }



  
  protected void onRender(Graphics2D graphics) {
    if (State.getCurrentState() != null)
      State.getCurrentState().render(graphics); 
  }
  
  protected void onUnload() {}
}
