package com.gmail.ahfonscs.plataforms.game.levels;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;




public final class Unpackager
{
  private static FileInputStream fis;
  private static ObjectInputStream ois;
  
  public static Level loadLevel(Path path) {
    Level level;
    try {
      fis = new FileInputStream(path.toFile());
      ois = new ObjectInputStream(fis);
      
      level = (Level)ois.readObject();
      ois.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
      try {
        Files.deleteIfExists(path);
      } catch (IOException e1) {
        e1.printStackTrace();
      } 
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      try {
        Files.deleteIfExists(path);
      } catch (IOException e1) {
        e1.printStackTrace();
      } 
      return null;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      try {
        Files.deleteIfExists(path);
      } catch (IOException e1) {
        e1.printStackTrace();
      } 
      return null;
    } 
    
    return level;
  }
}
