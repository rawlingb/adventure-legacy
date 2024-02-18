import java.io.*;
import java.nio.file.*;
import javax.swing.*;
public class AdventureGame
{
    public static void main(String [] args) throws IOException
    {
        StartScreen start = new StartScreen(); 
        int checkScreen = 0;
        Person person = null;
        EnemyChart enemyChart = new EnemyChart();
        int doWhat = 0;//Determines the result of what to do after a level is done
        boolean isWhat;//Determines the result of what to do after the boss battle
        int [][] enemies1 = {{ 0, 20 },
                          { 20, 30 },
                          { 1, 2}};
        int [][] enemies2 = {{ 0, 15, 20, 35, 45 },
                          { 15, 20, 35, 45, 55 },
                          { 1, 2, 3, 4, 5}};
        int [][] enemies3 = {{ 0, 7, 15, 22, 32 },
                          { 7, 15, 22, 32, 42 },
                          { 3, 4, 5, 6, 7}};
        int [][] enemies4 = {{ 0, 15},
                          { 15, 30},
                          { 8, 9 }};
        int [][] enemies5 = {{ 0, 15, 30},
                          { 15, 30, 33},
                          { 10, 11,  13}};
        int [][] enemies5Mem = {{ 0, 15},
                          { 15, 30},
                          { 10, 11}};   
        int [][] boss = {{ 0},
                          { 100},
                          { 12}}; 
        int [][] parameters1 = new int[1][10];
        int [][] parameters2 = new int[3][12];//(y and x)
        int [][] parameters3 = new int[4][10];
        int [][] parameters4 = new int[5][9];
        int [][] parameters5 = new int[5][6];
        int [] memebershipCodes = {278992343, 231245767, 298731452};
        boolean [][] isNoZone1 = new boolean[1][10];
        boolean [][] isNoZone2 = new boolean[3][12];
        boolean [][] isNoZone3 = new boolean[4][10];
        boolean [][] isNoZone4 = new boolean[5][9];
        boolean [][] isNoZone5 = new boolean[5][6];
        Path path = Paths.get("TextSave.txt");
        String newGame;
        FileWriter saveFile = null;
        //Parameters from load file
        int thisLevel = 0;
        int hp;
        int maxHp;
        int att;
        int def;
        int exp;
        int level;
        int potionNo;
        int oX = 0;
        int oY = 0;
        boolean isMember = false;
        boolean canDelete = true;
        if (Files.notExists(path)) 
        {
             saveFile = new FileWriter("TextSave.txt");
        }
        for(int i = 0; i < isNoZone1.length; i++)
        {
           for(int j = 0; j < isNoZone1[i].length; j++)
           {
                   isNoZone1[i][j] = false;
           }
        }
        for(int i = 0; i < isNoZone2.length; i++)
        {
           for(int j = 0; j < isNoZone2[i].length; j++)
           {
                   isNoZone2[i][j] = false;
           }
        }
        for(int i = 0; i < isNoZone3.length; i++)
        {
           for(int j = 0; j < isNoZone3[i].length; j++)
           {
               if((i > 0 && i < 3) && (j < 7))
               {
                   isNoZone3[i][j] = true;
               }
               else
               {
                   isNoZone3[i][j] = false;
               }
           }
        }
        for(int i = 0; i < isNoZone4.length; i++)
        {
           for(int j = 0; j < isNoZone4[i].length; j++)
           {
               if((j == 0 && (i == 1 || i == 3 || i == 4)) ||
                  (j == 1 && i == 4) ||
                  (j == 2 && (i == 0 || i == 3 || i == 4)) ||
                  (j == 3 && i == 1) ||
                  (j == 5 && (i >= 0 && i < 4)) ||
                  (j == 7 && (i >= 1 && i < 5)))
               {
                   isNoZone4[i][j] = true;
               }
               else
               {
                   isNoZone4[i][j] = false;
               }
           }
        }
        for(int i = 0; i < isNoZone5.length; i++)
        {
           for(int j = 0; j < isNoZone5[i].length; j++)
           {
               if((j == 1 && (i >= 0 && i < 4)) ||
                  (j == 2 && i == 3) ||
                  (j == 3 && i == 1) ||
                  (j == 4 && (i >= 1 && i < 5)))
               {
                   isNoZone5[i][j] = true;
               }
               else
               {
                   isNoZone5[i][j] = false;
               }
           }
        }
        while(thisLevel == 0)
        {
            checkScreen = start.loadScreen();
            if(checkScreen == 0)
            {
                if (Files.exists(path)) 
                {
                    try
                    {
                        newGame = JOptionPane.showInputDialog ("If you continue, you will delete your old file.");
                        if(newGame.equalsIgnoreCase("Yes"))
                        {
                            thisLevel = 1;
                            person = new Person(isMember);
                            doWhat = 1;
                        }
                        if(newGame.equalsIgnoreCase("No"))
                        {
                            thisLevel = 0;
                        }
                    }
                    catch(NullPointerException e)
                    {
                        return;
                    }
                }
                else
                {
                    thisLevel = 1;
                    person = new Person(isMember);
                    doWhat = 1;
                }
                if(thisLevel == 1)
                {
                    saveFile = new FileWriter("TextSave.txt");
                }
            }
            else if(checkScreen == 1)
            {
                return;
            }
            else if(checkScreen == 2)
            {
                if (Files.exists(path)) 
                {
                    BufferedReader loadFile =
                        new BufferedReader(new FileReader("TextSave.txt"));
                    thisLevel = Integer.parseInt(loadFile.readLine()); 
                    hp = Integer.parseInt(loadFile.readLine()); 
                    maxHp = Integer.parseInt(loadFile.readLine()); 
                    att = Integer.parseInt(loadFile.readLine()); 
                    def = Integer.parseInt(loadFile.readLine()); 
                    exp = Integer.parseInt(loadFile.readLine()); 
                    level = Integer.parseInt(loadFile.readLine()); 
                    potionNo = Integer.parseInt(loadFile.readLine()); 
                    person = new Person(hp, maxHp, att, def, exp, level, potionNo, isMember);
                    oX = Integer.parseInt(loadFile.readLine()); 
                    oY = Integer.parseInt(loadFile.readLine());
                    isMember = Boolean.parseBoolean(loadFile.readLine());
                    loadFile.close();
                    doWhat = 1;
                }
                else
                {
                    JOptionPane.showMessageDialog (null, "There is no file.", "Where is My Load File Sandwich?!", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else if(checkScreen == 3)
            {
                isMember = true;
                JOptionPane.showMessageDialog (null, "Your Membership has been Activated", "Membership Activated", JOptionPane.PLAIN_MESSAGE);
            }
        }
        if(thisLevel == 1)
        {
            if(doWhat == 1)
            {
                LevelOne levelOne = new LevelOne(person, enemyChart, 9, 0, enemies1, parameters1, false, isNoZone1, isMember, saveFile, oX, oY);
                doWhat = levelOne.doLevel("");
                oX = 0;
                oY = 0;
                thisLevel++;
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 2)
        {
            if(doWhat == 1)
            {
                LevelTwo levelTwo = new LevelTwo(person, enemyChart, 11, 2, enemies2, parameters2, false, isNoZone2, isMember, saveFile, oX, oY);
                doWhat = levelTwo.doLevel("");
                oX = 0;
                oY = 0;
                thisLevel++;
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 3)
        {
            if(doWhat == 1)
            {
                Level levelThree = new LevelThree(person, enemyChart, 0, 3, enemies3, parameters3, false, isNoZone3, isMember, saveFile, oX, oY);
                doWhat = levelThree.doLevel("");
                oX = 0;
                oY = 0;
                thisLevel++;
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 4)
        {
            if(doWhat == 1)
            {
                Level levelFour = new LevelFour(person, enemyChart, 8, 4, enemies4, parameters4, false, isNoZone4, isMember, saveFile, oX, oY);
                doWhat = levelFour.doLevel("");
                oX = 0;
                oY = 0;
                thisLevel++;
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 5)
        {
            if(doWhat == 1)
            {
                if(isMember)
                {
                    Level levelFive = new LevelFive(person, enemyChart, 5, 4, enemies5Mem, parameters5, true, isNoZone5, isMember, saveFile, oX, oY);
                    doWhat = levelFive.doLevel("");
                    thisLevel++;
                }
                else
                {
                    Level levelFive = new LevelFive(person, enemyChart, 5, 4, enemies5, parameters5, true, isNoZone5, isMember, saveFile, oX, oY);
                    doWhat = levelFive.doLevel("");
                    thisLevel++;
                }
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 6)
        {
            if(doWhat == 1)
            {
                    Level levelBoss = new LevelBoss(person, enemyChart, isMember, boss);
                    isWhat = levelBoss.battleCheck();
                    thisLevel++;
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
        if(thisLevel == 7)
        {
            if(doWhat == 1)
            {
                    JOptionPane.showMessageDialog (null, "You have completed the free portion of the game.", "Game Complete!", JOptionPane.PLAIN_MESSAGE);
                    if(isMember)
                    {
                        JOptionPane.showMessageDialog (null, "Levels above are 5 currently in development.", "The Bunny took my Stopwatch!", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog (null, "For levels above 5, you need to have preminum Membership.  Memebers get:\n40 extra levels on character,\naccess to 10 extra levels and\neven funnier content.\nTry it now.  It's only $9.99.", "Membership", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
            }
            else if(doWhat == 0)
            {
                return;
            }
        }
    }
}
