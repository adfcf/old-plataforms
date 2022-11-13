package com.gmail.ahfonscs.plataforms.core;

import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


















public final class Performancer
  implements ActionListener
{
  protected static final long NANOS_IN_ONE_SECOND = 1000000000L;
  protected static final int ESTIMATED_TICKS_PER_SECOND = 60;
  protected static final long INTERVAL_PER_TICK = NANOS_IN_ONE_SECOND / ESTIMATED_TICKS_PER_SECOND;
  protected static final int ALLOWANCE_TICKS = 3;
  private Timer timer = new Timer(1000, this); private int seconds;
  private int fps;
  
  public void start() {
    this.timer.start();
  }
  private int tps; private int ticks; private int renders;
  protected void countTick() {
    this.ticks++;
  }
  protected void countRender() {
    this.renders++;
  }

  
  protected void drawInfo(Graphics2D graphics) {
    if (State.getCurrentState() instanceof com.gmail.ahfonscs.plataforms.game.states.EditorState || State.getCurrentState() instanceof com.gmail.ahfonscs.plataforms.game.states.DivisionState) {
      return;
    }
    // graphics.setColor(Color.BLACK);
    
    // graphics.drawString("Segundos: " + this.seconds, 10, 20);
    // graphics.drawString("FPS: " + this.fps, 10, 40);
    // graphics.drawString("TPS: " + this.tps, 10, 60);
  }



  
  public void actionPerformed(ActionEvent e) {
    this.seconds++;
    
    if (this.ticks <= this.tps - 3) {
      System.out.println("Queda consider\u00E1vel (ticks)");
    }
    
    this.fps = this.renders;
    this.tps = this.ticks;
    
    this.renders = 0;
    this.ticks = 0;
  }
}
