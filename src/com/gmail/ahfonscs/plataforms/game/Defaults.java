package com.gmail.ahfonscs.plataforms.game;

import java.awt.Color;
import java.io.File;

public final class Defaults
{
  private static String path = "C:\\_Levels";
  private static Color personColor;
  private static File currentLevel;
  
  public static Color getPersonColor() {
    return personColor;
  }
  
  public static void setPersonColor(Color personColor) {
    Defaults.personColor = personColor;
  }
  
  public static File getCurrentLevel() {
    return currentLevel;
  }
  
  public static void setCurrentLevel(File currentLevel) {
    Defaults.currentLevel = currentLevel;
  }
  
  public static String getPath() {
    return path;
  }
  
  public static void setPath(String path) {
    Defaults.path = path;
  }
}
