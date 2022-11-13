package com.gmail.ahfonscs.plataforms.game.states.components;

import com.gmail.ahfonscs.plataforms.core.managers.FontManager;
import com.gmail.ahfonscs.plataforms.game.states.State;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;






public class MenuButton
  extends Rectangle
{
  private static Rectangle mouseLocation;
  private String function;
  private boolean selected;
  
  public MenuButton(String function, int x, int y, int width, int height) {
    this.function = function;
    
    this.x = x;
    this.y = y;
    
    this.width = width;
    this.height = height;
    
    configurate();
  }


  
  public void update() {
    Point location = State.getCurrentWindow().getMousePosition();
    if (location == null)
      return; 
    mouseLocation.setLocation(location.x - 3, location.y - 25);
    this.selected = mouseLocation.intersects(this);
  }


  
  public void draw(Graphics2D graphics) {
    graphics.setColor(this.selected ? FontManager.getSelectedButtonColor() : FontManager.getButtonColor());
    graphics.fillRect(this.x, this.y, this.width, this.height);
    
    graphics.setColor(Color.WHITE);
    graphics.setFont(FontManager.getButtonFont());
    graphics.drawString(this.function, this.x + 10, this.y + 50);
  }

  
  public boolean isSelected() {
    return this.selected;
  }

  
  private void configurate() {
    setSize(new Dimension(this.width, this.height));
    
    if (mouseLocation == null)
      mouseLocation = new Rectangle(0, 0, 1, 1); 
  }
}
