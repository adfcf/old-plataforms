package com.gmail.ahfonscs.plataforms.game.levels;

import com.gmail.ahfonscs.plataforms.game.Defaults;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import javax.swing.JOptionPane;





public class Packager
{
  private static Path finalPath;
  private static FileOutputStream fos;
  private static ObjectOutputStream oos;
  
  private static void createDirectory(String levelName) {
    String type = "\\_Levels_v".concat(String.valueOf(0.0D));
    String pathS = Defaults.getPath().concat(type);

    
    try {
      Path fPath = Paths.get(pathS, new String[0]);
      
      finalPath = Paths.get(pathS.concat("\\").concat(levelName), new String[0]);
      
      Files.createDirectories(Paths.get(Defaults.getPath(), new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
      Files.createDirectories(fPath, (FileAttribute<?>[])new FileAttribute[0]);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "N\u00E3o foi poss\u00EDvel criar um reposit\u00F3rio para as fases!", "Erro!", 0);
    } 
  }


  
  public static boolean serializarLevel(Level level) {
    createDirectory("level_".concat(level.getName().concat(".os")));

    
    try {
      fos = new FileOutputStream(finalPath.toFile());
      oos = new ObjectOutputStream(fos);
      
      oos.writeObject(level);
      oos.close();
      
      return true;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Um erro fatal impediu a constru\u00E7\u00E3o da fase!", "Falha!", 0);
      return false;
    } 
  }
}
