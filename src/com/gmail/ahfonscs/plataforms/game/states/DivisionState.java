package com.gmail.ahfonscs.plataforms.game.states;

import java.awt.Color;
import java.awt.Graphics2D;

public class DivisionState
  extends State {
  private State newState;
  private int y;
  
  public DivisionState(State state) {
    this.newState = state;
  }


  
  public void update() {
    this.y += 8;
    
    if (this.y > State.getCurrentWindow().getHeight()) {
      State.setCurrentState(this.newState);
    }
  }



  
  public void render(Graphics2D graphics) {
    graphics.setColor(Color.ORANGE);
    graphics.fillRect(0, this.y, State.getCurrentWindow().getWidth(), State.getCurrentWindow().getHeight());
  }
}
