package com.gmail.ahfonscs.plataforms.core.managers;

import java.awt.Color;
import java.awt.Font;

public class FontManager
{
  private static final Color SELECTED_BUTTON_COLOR = new Color(255, 201, 14);
  private static final Color BUTTON_COLOR = new Color(65, 198, 243);
  
  private static final Color TITLE_COLOR = new Color(255, 201, 14);
  private static final Color EXTRA_COLOR = new Color(237, 28, 36);
  
  private static final Font BUTTON_FONT = new Font("Cambria", 1, 40);
  private static final Font TITLE_FONT = new Font("Cambria", 0, 130);
  private static final Font COMMON_FONT = new Font("Times New Roman", 0, 40);
  
  public static Font getButtonFont() {
    return BUTTON_FONT;
  }
  public static Font getTitleFont() {
    return TITLE_FONT;
  }
  public static Font getCommonFont() {
    return COMMON_FONT;
  }
  
  public static Color getButtonColor() {
    return BUTTON_COLOR;
  }
  public static Color getSelectedButtonColor() {
    return SELECTED_BUTTON_COLOR;
  }
  public static Color getTitleColor() {
    return TITLE_COLOR;
  }
  public static Color getExtraColor() {
    return EXTRA_COLOR;
  }
}
