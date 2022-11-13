package com.gmail.ahfonscs.plataforms.core.managers;

import com.gmail.ahfonscs.plataforms.game.states.DivisionState;
import com.gmail.ahfonscs.plataforms.game.states.MainMenuState;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;





public final class InputManager
  extends KeyAdapter
{
  private static InputManager instance;
  private final List<Integer> list = new ArrayList<>();


  
  public static InputManager getInstance() {
    if (instance == null) {
      instance = new InputManager();
    }
    
    return instance;
  }

  
  public boolean isPressed(int code) {
    return this.list.contains(Integer.valueOf(code));
  }
  
  public boolean isGoingUp() {
    return this.list.contains(Integer.valueOf(38));
  }
  public boolean isGoingLeft() {
    return this.list.contains(Integer.valueOf(37));
  }
  public boolean isGoingRight() {
    return this.list.contains(Integer.valueOf(39));
  }
  public boolean isGoingDown() {
    return this.list.contains(Integer.valueOf(40));
  }


  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 27 && State.getCurrentState() instanceof com.gmail.ahfonscs.plataforms.game.states.GameState) {
      State.setCurrentState((State)new DivisionState((State)new MainMenuState()));
    }
    
    if (!this.list.contains(Integer.valueOf(e.getKeyCode()))) {
      this.list.add(Integer.valueOf(e.getKeyCode()));
    }
  }

  
  public void keyReleased(KeyEvent e) {
    if (this.list.contains(Integer.valueOf(e.getKeyCode())))
      this.list.remove(Integer.valueOf(e.getKeyCode())); 
  }
}
