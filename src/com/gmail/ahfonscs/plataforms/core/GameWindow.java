package com.gmail.ahfonscs.plataforms.core;

import com.gmail.ahfonscs.plataforms.core.managers.InputManager;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class GameWindow
  extends JFrame
{
  private Canvas canvas;
  
  public GameWindow() {
    configurate();
  }

  
  private void configurate() {
    this.canvas = new Canvas();
    
    setTitle("Plataforms");
    setSize(1000, 625);
    setLocationRelativeTo((Component)null);
    setDefaultCloseOperation(3);
    setIgnoreRepaint(true);
    setResizable(false);
    setVisible(false);
    
    this.canvas.addKeyListener((KeyListener)InputManager.getInstance());
    this.canvas.setFocusable(true);
    
    add(this.canvas);
  }


  
  public Canvas getCanvas() {
    return this.canvas;
  }
}
