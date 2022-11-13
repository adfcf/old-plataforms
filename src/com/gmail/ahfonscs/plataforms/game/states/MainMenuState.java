package com.gmail.ahfonscs.plataforms.game.states;

import com.gmail.ahfonscs.plataforms.core.GameWindow;
import com.gmail.ahfonscs.plataforms.core.managers.FontManager;
import com.gmail.ahfonscs.plataforms.game.Defaults;
import com.gmail.ahfonscs.plataforms.game.states.components.MenuButton;
import com.gmail.ahfonscs.plataforms.game.states.components.Square;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;




public class MainMenuState
  extends State
{
  private MenuButton start;
  private MenuButton options;
  private MenuButton editor;
  private MenuButton exit;
  private Square s;
  
  public MainMenuState() {
    State.getCurrentWindow().getCanvas().addMouseListener(new Listener());
    GameWindow gameWindow = State.getCurrentWindow();
    
    int gWidth = 350;
    int gHeight = 80;
    
    this.s = new Square(400, 250, 50, 35, 20);
    
    this.start = new MenuButton("Jogar", gameWindow.getWidth() / 2 - gWidth / 2, 200, gWidth, gHeight);
    this.options = new MenuButton("Op\u00E7\u00F5es", gameWindow.getWidth() / 2 - gWidth / 2, 200 + gHeight + 10, gWidth, gHeight);
    this.editor = new MenuButton("Editor", gameWindow.getWidth() / 2 - gWidth / 2, 200 + gHeight * 2 + 20, gWidth, gHeight);
    this.exit = new MenuButton("Sair", gameWindow.getWidth() / 2 - gWidth / 2, 200 + gHeight * 3 + 30, gWidth, gHeight);
  }



  
  public void update() {
    this.start.update();
    this.options.update();
    this.editor.update();
    this.exit.update();
    
    this.s.update();
  }



  
  public void render(Graphics2D graphics) {
    graphics.setFont(FontManager.getTitleFont());
    graphics.setColor(FontManager.getTitleColor());
    graphics.drawString("Plataforms", State.getCurrentWindow().getWidth() / 2 - 290, 130);
    
    this.start.draw(graphics);
    this.options.draw(graphics);
    this.editor.draw(graphics);
    this.exit.draw(graphics);
    
    this.s.draw(graphics);
  }
  
  private class Listener
    extends MouseAdapter
  {
    private Listener() {}
    
    public void mouseClicked(MouseEvent e) {
      if (MainMenuState.this.start.isSelected()) {
        
        if (Defaults.getCurrentLevel() != null) {
          State.getCurrentWindow().getCanvas().removeMouseListener(this);
          State.setCurrentState(new DivisionState(new GameState()));
        } else {
          JOptionPane.showMessageDialog(null, "Nesta vers\u00E3o, voc\u00EA deve escolher uma fase primeiro!", "Sem fase!", 2);
        }
      
      } else if (MainMenuState.this.options.isSelected()) {
        
        State.getCurrentWindow().getCanvas().removeMouseListener(this);
        State.setCurrentState(new OptionsState());
      }
      else if (MainMenuState.this.editor.isSelected()) {
        
        State.getCurrentWindow().getCanvas().removeMouseListener(this);
        State.setCurrentState(new DivisionState(new EditorState()));
      
      }
      else if (MainMenuState.this.exit.isSelected()) {
        
        System.exit(0);
      } 
    }
  }
}
