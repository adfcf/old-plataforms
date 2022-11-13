package com.gmail.ahfonscs.plataforms.game.states;

import com.gmail.ahfonscs.plataforms.core.GameWindow;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import java.awt.Graphics2D;


public abstract class State
{
  private static State currentState;
  private static GameWindow currentWindow;
  private static Player player;
  
  public static State getCurrentState() {
    return currentState;
  }
  public static void setCurrentState(State currentState) {
    State.currentState = currentState;
  }
  
  public static GameWindow getCurrentWindow() {
    return currentWindow;
  }
  public static void setCurrentWindow(GameWindow currentWindow) {
    State.currentWindow = currentWindow;
  }
  
  public static Player getPlayer() {
    return player;
  }
  public static void setPlayer(Player player) {
    State.player = player;
  }
  
  public abstract void update();
  
  public abstract void render(Graphics2D paramGraphics2D);
}
