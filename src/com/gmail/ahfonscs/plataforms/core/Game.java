package com.gmail.ahfonscs.plataforms.core;

import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;








public abstract class Game
{
  public static final double VERSION = 0.0D;
  private BufferStrategy bufferSt;
  private Performancer performancer;
  private GameWindow mainWindow;
  private Graphics2D graphics;
  private boolean running;
  
  private void load() {
    this.performancer = new Performancer();
    this.mainWindow = new GameWindow();
    
    State.setCurrentWindow(this.mainWindow);
    onLoad();
    
    this.performancer.start();
    this.mainWindow.setVisible(true);
  }


  
  private void update() {
    this.performancer.countTick();
    
    Thread.yield();
    onUpdate();
  }



  
  private void render() {
    try {
      if (this.bufferSt == null) {
        this.mainWindow.getCanvas().createBufferStrategy(2);
        this.bufferSt = this.mainWindow.getCanvas().getBufferStrategy();
        this.graphics = (Graphics2D)this.bufferSt.getDrawGraphics();
      } else {
        this.graphics = (Graphics2D)this.bufferSt.getDrawGraphics();
      }
    
    } catch (Exception e1) {
      System.err.println("Graphic context is out of reach!");
      
      return;
    } 
    this.graphics.setColor(Color.WHITE);
    this.graphics.fillRect(0, 0, this.mainWindow.getWidth(), this.mainWindow.getHeight());
    this.performancer.drawInfo(this.graphics);
    
    onRender(this.graphics);
    
    if (!this.bufferSt.contentsLost()) {
      this.performancer.countRender();
      this.bufferSt.show();
    } else {
      System.out.println("Lost frame!");
    } 
    
    this.graphics.dispose();
  }

  
  private void unload() {
    onUnload();
    
    this.bufferSt.dispose();
    this.mainWindow.dispose();
  }

  
  public void run() {
    this.running = true;
    load();
    
    long nanoTime = System.nanoTime();
    while (this.running) {
      while (System.nanoTime() > nanoTime) {
        update();
        nanoTime += 20000000L;
      } 
      render();
    } 
    unload();
  }
  
  protected abstract void onLoad();
  
  protected abstract void onUpdate();
  
  protected abstract void onRender(Graphics2D paramGraphics2D);
  
  protected abstract void onUnload();
}
