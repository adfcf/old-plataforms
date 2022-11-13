package com.gmail.ahfonscs.plataforms.game.states;

import com.gmail.ahfonscs.plataforms.core.GameWindow;
import com.gmail.ahfonscs.plataforms.core.managers.FontManager;
import com.gmail.ahfonscs.plataforms.game.entities.Creature;
import com.gmail.ahfonscs.plataforms.game.entities.Entity;
import com.gmail.ahfonscs.plataforms.game.entities.Plataform;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Ground;
import com.gmail.ahfonscs.plataforms.game.entities.elements.MobileGround;
import com.gmail.ahfonscs.plataforms.game.entities.elements.Player;
import com.gmail.ahfonscs.plataforms.game.entities.elements.enemies.Enemy;
import com.gmail.ahfonscs.plataforms.game.entities.elements.marks.FinishEntity;
import com.gmail.ahfonscs.plataforms.game.entities.elements.marks.SpawnEntity;
import com.gmail.ahfonscs.plataforms.game.levels.Level;
import com.gmail.ahfonscs.plataforms.game.levels.Packager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;


public class EditorState
  extends State
{
  private List<Plataform> plataforms;
  private List<Creature> creatures;
  private Point deslocation;
  private Color pC = Color.BLACK;
  
  private int ucx;
  
  private int ucy;
  private int enemySize = 25;
  
  private GameWindow window;
  
  private Listener listener;
  
  private Rectangle rectangle;
  private Entity selected;
  private SpawnEntity spawn;
  private FinishEntity finish;
  private Mode mode = Mode.SELECT;
  
  public EditorState() {
    init();
  }

  
  private void init() {
    this.deslocation = new Point();
    
    this.plataforms = new ArrayList<>();
    this.creatures = new ArrayList<>();
    
    this.spawn = new SpawnEntity(50, 75, 30, 30);
    this.finish = new FinishEntity(50, 115, 30, 30);
    
    this.plataforms.add(this.finish);
    this.plataforms.add(this.spawn);
    
    this.window = State.getCurrentWindow();
    
    this.listener = new Listener();
    this.window.getCanvas().addMouseListener(this.listener);
    this.window.getCanvas().addMouseMotionListener(this.listener);
    this.window.getCanvas().addKeyListener(this.listener);
    
    this.rectangle = new Rectangle();
  }


  
  private void finalizeLevel() {
    this.window.getCanvas().removeMouseListener(this.listener);
    this.window.getCanvas().removeMouseMotionListener(this.listener);
    this.window.getCanvas().removeKeyListener(this.listener);
    
    String n = JOptionPane.showInputDialog((Component)null, "Qual ser\u00E1 o nome da fase?");
    this.creatures.add(new Player(this.spawn.getXI(), this.spawn.getYI(), 30, 30));
    
    Point ss = new Point(this.spawn.getXI(), this.spawn.getYI());
    Point sc = new Point(this.finish.getXI(), this.finish.getYI());
    
    Level newLevel = new Level(n, ss, sc, this.creatures, this.plataforms);
    
    if (Packager.serializarLevel(newLevel)) {
      JOptionPane.showMessageDialog(null, "Fase constru\u00EDda com \u00EAxito.", "Sucesso!", 1);
    } else {
      JOptionPane.showMessageDialog(null, "N\u00E3o foi poss\u00EDvel construir fase!", "Falha!", 0);
    } 
    
    State.setCurrentState(new DivisionState(new MainMenuState()));
  }



  
  public void update() {
    for (Plataform e : this.plataforms) {
      e.update();
    }
  }


  
  public void render(Graphics2D graphic) {
    Graphics2D graphics = (Graphics2D)graphic.create();
    
    graphics.translate(-this.deslocation.x, -this.deslocation.y);
    
    if (this.mode != Mode.SELECT) {
      graphics.setColor(Color.DARK_GRAY);
      graphics.drawRect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
    } 
    
    for (Plataform e : this.plataforms) {
      if (e.equals(this.selected)) {
        graphics.setColor(Color.MAGENTA);
        graphics.drawRect(e.getXI(), e.getYI(), e.getWidth(), e.getHeight());
        graphics.setColor(Color.DARK_GRAY);
        continue;
      } 
      e.draw(graphics);
    } 
    
    for (Creature c : this.creatures) {
      if (c.equals(this.selected)) {
        graphics.setColor(Color.MAGENTA);
        graphics.drawRect(c.getXI(), c.getYI(), c.getWidth(), c.getHeight());
        graphics.setColor(Color.DARK_GRAY);
        continue;
      } 
      c.draw(graphics);
    } 
    
    graphics.translate(this.deslocation.x, this.deslocation.y);
    
    graphics.setFont(FontManager.getCommonFont());
    graphics.setColor(Color.BLACK);
    graphics.drawString("Modo: ", 25, 40);
    
    graphics.setColor((this.mode == Mode.SELECT) ? FontManager.getTitleColor() : Color.BLACK);
    graphics.drawString(Mode.SELECT.toString(), 170, 40);
    
    graphics.setColor((this.mode == Mode.GROUND) ? FontManager.getTitleColor() : Color.BLACK);
    graphics.drawString(Mode.GROUND.toString(), 360, 40);
    
    graphics.setColor((this.mode == Mode.MOBILE_GROUND) ? FontManager.getTitleColor() : Color.BLACK);
    graphics.drawString(Mode.MOBILE_GROUND.toString(), 530, 40);
    
    graphics.setColor((this.mode == Mode.ENEMY) ? FontManager.getTitleColor() : Color.BLACK);
    graphics.drawString(Mode.ENEMY.toString(), 755, 40);
    
    graphics.dispose();
  }
  
  private class Listener
    extends MouseAdapter
    implements KeyListener {
    private Listener() {}
    
    public void mouseDragged(MouseEvent e) {
      Point point = EditorState.this.window.getMousePosition();
      if (point == null || EditorState.this.mode == EditorState.Mode.ENEMY)
        return; 
      point.x = point.x - 3 + EditorState.this.deslocation.x;
      point.y = point.y - 25 + EditorState.this.deslocation.y;
      
      if (EditorState.this.mode == EditorState.Mode.SELECT && EditorState.this.selected != null) {
        
        int deslocamentox = point.x - EditorState.this.selected.getWidth() / 2;
        int deslocamentoy = point.y - EditorState.this.selected.getHeight() / 2;
        
        EditorState.this.selected.setX(deslocamentox);
        EditorState.this.selected.setY(deslocamentoy);
        
        return;
      } 
      if (EditorState.this.mode == EditorState.Mode.SELECT && EditorState.this.selected == null) {
        
        if (point.x < EditorState.this.ucx) {
          EditorState.this.deslocation.x += EditorState.this.ucx - point.x;
        } else {
          EditorState.this.deslocation.x -= point.x - EditorState.this.ucx;
        } 
        
        if (point.y < EditorState.this.ucy) {
          EditorState.this.deslocation.y += EditorState.this.ucy - point.y;
        } else {
          EditorState.this.deslocation.y -= point.y - EditorState.this.ucy;
        } 
      } 

      
      EditorState.this.rectangle.width = point.x - EditorState.this.rectangle.x;
      EditorState.this.rectangle.height = point.y - EditorState.this.rectangle.y;
    }



    
    public void mousePressed(MouseEvent e) {
      Point point = EditorState.this.window.getMousePosition();
      if (point == null)
        return; 
      point.x = point.x - 3 + EditorState.this.deslocation.x;
      point.y = point.y - 25 + EditorState.this.deslocation.y;
      
      EditorState.this.ucx = point.x;
      EditorState.this.ucy = point.y;
      
      if (EditorState.this.mode == EditorState.Mode.SELECT) {
        EditorState.this.rectangle.setBounds(point.x, point.y, 1, 1);
        
        Rectangle pr = new Rectangle();
        
        for (Entity p : EditorState.this.plataforms) {
          pr.setBounds(p.getXI(), p.getYI(), p.getWidth(), p.getHeight());
          if (EditorState.this.rectangle.intersects(pr)) {
            EditorState.this.selected = p;
            break;
          } 
          EditorState.this.selected = null;
        } 
      } 

      
      EditorState.this.rectangle.setLocation(point.x, point.y);
    }



    
    public void mouseClicked(MouseEvent e) {
      Point point = EditorState.this.window.getMousePosition();
      if (point == null)
        return; 
      point.x = point.x - 3 + EditorState.this.deslocation.x;
      point.y = point.y - 25 + EditorState.this.deslocation.y;
      
      if (EditorState.this.mode == EditorState.Mode.ENEMY) {
        EditorState.this.creatures.add(new Enemy(point.x, point.y, EditorState.this.enemySize, EditorState.this.enemySize, EditorState.this.pC));
      }
    }



    
    public void mouseReleased(MouseEvent e) {
      if (EditorState.this.mode == EditorState.Mode.GROUND) {
        
        EditorState.this.plataforms.add(new Ground(EditorState.this.rectangle.x, EditorState.this.rectangle.y, EditorState.this.rectangle.width, EditorState.this.rectangle.height, EditorState.this.pC));
      }
      else if (EditorState.this.mode == EditorState.Mode.MOBILE_GROUND) {
        
        String min = JOptionPane.showInputDialog("Insira o ponto(x,y) m\u00EDnimo: ", String.valueOf(EditorState.this.rectangle.x).concat(",").concat(String.valueOf(EditorState.this.rectangle.y)));
        String max = JOptionPane.showInputDialog("Insira o ponto(x,y) m\u00E1ximo: ", String.valueOf(EditorState.this.rectangle.x + 75).concat(",").concat(String.valueOf(EditorState.this.rectangle.y)));

        
        EditorState.this.plataforms.add(new MobileGround(EditorState.this.rectangle.x, EditorState.this.rectangle.y, EditorState.this.rectangle.width, EditorState.this.rectangle.height, min, max, EditorState.this.pC));
      } 

      
      EditorState.this.rectangle.setBounds(0, 0, 0, 0);
    }



    
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        
        case 68:
          if (EditorState.this.mode == EditorState.Mode.SELECT) {
            EditorState.this.mode = EditorState.Mode.ENEMY; break;
          }  if (EditorState.this.mode == EditorState.Mode.ENEMY) {
            EditorState.this.mode = EditorState.Mode.MOBILE_GROUND; break;
          }  if (EditorState.this.mode == EditorState.Mode.MOBILE_GROUND) {
            EditorState.this.mode = EditorState.Mode.GROUND; break;
          } 
          EditorState.this.mode = EditorState.Mode.SELECT;
          break;


        
        case 70:
          if (EditorState.this.mode == EditorState.Mode.SELECT) {
            EditorState.this.mode = EditorState.Mode.GROUND; break;
          }  if (EditorState.this.mode == EditorState.Mode.GROUND) {
            EditorState.this.mode = EditorState.Mode.MOBILE_GROUND; break;
          }  if (EditorState.this.mode == EditorState.Mode.MOBILE_GROUND) {
            EditorState.this.mode = EditorState.Mode.ENEMY; break;
          } 
          EditorState.this.mode = EditorState.Mode.SELECT;
          break;


        
        case 27:
          if (EditorState.this.selected != null && !(EditorState.this.selected instanceof SpawnEntity) && !(EditorState.this.selected instanceof FinishEntity)) {
            EditorState.this.plataforms.remove(EditorState.this.selected);
          }
          break;

        
        case 127:
          if (EditorState.this.selected != null && !(EditorState.this.selected instanceof SpawnEntity) && !(EditorState.this.selected instanceof FinishEntity)) {
            EditorState.this.plataforms.remove(EditorState.this.selected);
          }
          break;
        
        case 10:
          EditorState.this.finalizeLevel();
          break;
        case 81:
          EditorState.this.enemySize = EditorState.this.enemySize - 1;
          break;
        case 80:
          EditorState.this.enemySize = EditorState.this.enemySize + 1;
          break;
        case 38:
          EditorState.this.deslocation.y -= 8;
          break;
        case 40:
          EditorState.this.deslocation.y += 8;
          break;
        case 37:
          EditorState.this.deslocation.x -= 8;
          break;
        case 39:
          EditorState.this.deslocation.x += 8;
          break;
        case 67:
          EditorState.this.pC = JColorChooser.showDialog(null, "Cor da Plataforma!", Color.BLACK);
          break;
      } 
    }

    
    public void keyReleased(KeyEvent arg0) {}

    
    public void keyTyped(KeyEvent arg0) {}
  }

  
  private enum Mode
  {
    SELECT("Sele\u00E7\u00E3o"), GROUND("Pl. Fixa"), ENEMY("Inimigo"), MOBILE_GROUND("Pl. M\u00F3vel");
    private final String type;
    
    Mode(String type) {
      this.type = type;
    }

    
    public String toString() {
      return this.type;
    }
  }
}
