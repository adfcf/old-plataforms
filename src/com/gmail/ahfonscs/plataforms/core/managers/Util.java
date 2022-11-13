package com.gmail.ahfonscs.plataforms.core.managers;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;


public final class Util
{
  private static final Random RANDOM = new Random();
  
  public static int newNumber() {
    return RANDOM.nextInt(25000000);
  }
  
  public static int newNumber(int max) {
    return RANDOM.nextInt(max);
  }
  
  public static int newNumber(int min, int max) {
    return min + RANDOM.nextInt(max - min);
  }
  
  public static Color newColor() {
    return new Color(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
  }
  
  public static void newMessage(String message, String title, Type type) {
    JOptionPane.showMessageDialog(null, message, title, type.id);
  }
  
  public enum Type
  {
    WARNING(2), INFORMATION(1),
    ERROR(0), QUESTION(3);
    protected final int id;
    
    Type(int id) {
      this.id = id;
    }
  }
}
