package com.gmail.ahfonscs.plataforms.game.states;

import com.gmail.ahfonscs.plataforms.core.managers.Util;
import com.gmail.ahfonscs.plataforms.game.Defaults;
import com.gmail.ahfonscs.plataforms.game.states.components.MenuButton;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;





public class OptionsState
  extends State
{
  private MenuButton cor;
  private MenuButton selecao;
  private MenuButton sobre;
  private MenuButton none;
  
  public OptionsState() {
    State.getCurrentWindow().getCanvas().addMouseListener(new Listener());
    
    this.cor = new MenuButton("Cor de Personagem", 100, 100, 375, 80);
    this.selecao = new MenuButton("Sele\u00E7\u00E3o de Fases", 100, 200, 375, 80);
    this.sobre = new MenuButton("Sobre", 480, 300, 375, 80);
    this.none = new MenuButton("Voltar", 480, 400, 375, 80);
  }



  
  public void update() {
    this.cor.update();
    this.selecao.update();
    this.sobre.update();
    this.none.update();
  }



  
  public void render(Graphics2D graphics) {
    this.cor.draw(graphics);
    this.selecao.draw(graphics);
    this.sobre.draw(graphics);
    this.none.draw(graphics);
  }
  
  private class Listener
    extends MouseAdapter
  {
    private JFileChooser chooser;
    
    private Listener() {}
    
    public void mouseClicked(MouseEvent e) {
      if (OptionsState.this.cor.isSelected()) {
        chooseColor();
      } else if (OptionsState.this.selecao.isSelected()) {
        chooseLevel();
      } else if (OptionsState.this.sobre.isSelected()) {
        info();
      } else if (OptionsState.this.none.isSelected()) {
        back();
      } 
    }

    
    private void chooseColor() {
      Defaults.setPersonColor(JColorChooser.showDialog(null, "Selecione a cor: ", Color.BLUE));
      Util.newMessage("A cor do personagem foi trocada!", "Sucesso!", Util.Type.INFORMATION);
    }

    
    private void chooseLevel() {
      if (this.chooser == null) {
        this.chooser = new JFileChooser();
      }
      
      this.chooser.setCurrentDirectory(new File("C:\\_Levels".concat("\\_Levels_v").concat(String.valueOf(0.0D))));
      this.chooser.showOpenDialog(null);
      Defaults.setCurrentLevel(this.chooser.getSelectedFile());
      
      Util.newMessage("Sele\u00E7\u00E3o bem-sucedida!", "Sucesso!", Util.Type.INFORMATION);
    }

    
    private void info() {
      JOptionPane.showMessageDialog(null, "por: adfcf", 
          "Plataforms", 1);
    }
    
    private void back() {
      State.getCurrentWindow().getCanvas().removeMouseListener(this);
      State.setCurrentState(new MainMenuState());
    }
  }
}
