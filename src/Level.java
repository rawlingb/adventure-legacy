import java.util.*;
import javax.swing.*;
import java.io.*;
public class Level
{
    int [][] parameters;
    int [][] enemies;
    boolean[][] isNoZone;
    List<List<String>> level = new ArrayList<List<String>>();
    String currentLevel = "";
    int levelNum; //pass it
    int oX = 0;
    int oY = 0; 
    int endX; //Pass it
    int endY; //Pass it
    int bossX;
    int bossY;
    String input; //Input for outside of battle
    String battleInput; //Input for battles
    int move = 0;
    boolean canEncounter = false;
    boolean canLeave = false;
    boolean isDead = false; //Determines whether you are dead
    boolean enemyIsDead = false; //Determines whether enemy is dead
    boolean hasRun = false;
    Person person;
    EnemyChart enemyChart;
    int personDamage;
    int enemyDamage;
    boolean isAction = false; //Used in battle to determine whether action was valid, so person doesn't take damage for doing nothing
    boolean isBoss;
    int potionChance;
    boolean isMember;
    FileWriter saveFile;
    /**
     * Constructor for objects of class Level
     */
    public Level(Person p, EnemyChart e, int lev, int bX, int bY, int [][] ens, int [][] par, boolean isBoss, boolean[][] noZone, boolean isMem, FileWriter sf, int oX, int oY)//Level
    {
        parameters = par;
        for(int i = 0; i < parameters.length; i++)
        {
            List<String> aList = new ArrayList<String>();
            for(int j = 0; j < parameters[i].length; j++)
            {
                aList.add(".");
            }
            level.add(aList);
        }
        person = p;
        enemyChart = e;
        levelNum = lev;
        endX = bX;
        endY = bY;
        enemies = ens;
        this.isBoss = isBoss;
        isNoZone = noZone;
        isMember = isMem;
        saveFile = sf;
        this.oX = oX;
        this.oY = oY;
    }
    public Level(Person p, EnemyChart e, boolean isMem, int [][] ens)//Boss
    {
        person = p;
        enemyChart = e;
        isMember = isMem;
        enemies = ens;
    }
    public int doLevel(String action) throws IOException
    {
        currentLevel = "";
        if(move == 0)
        {
            move++;
        }
        else
        {
            checkAction(action);
            if(isDead)
            {
                return 0;
            }
        }
        if(canLeave)
        {
            return 1;
        }
        for(int i = 0; i < parameters.length; i++)
        {
            List<String> aList = new ArrayList<String>();
            for(int j = 0; j < parameters[i].length; j++)
            {
               try
               {
                   if(j == oX)
                   {
                        if(i == oY)
                        {
                            aList.add("P");
                        }
                        else if(isNoZone[i][j])
                        {
                            aList.add("x");
                        }
                        else
                        {
                            aList.add("o");
                        }
                   }
                   else if(j == endX)
                   {
                        if(i == endY)
                        {
                            if(isBoss)
                                aList.add("B");
                            else  
                                aList.add("E");
                        }
                        else if(isNoZone[i][j])
                        {
                            aList.add("x");
                        }
                        else
                        {
                            aList.add("o");
                        }
                   }
                   else
                   {
                        if(isNoZone[i][j])
                        {
                            aList.add("x");
                        }
                        else
                        {
                            aList.add("o");
                        }
                   }
               }
               catch(NullPointerException e)
               {
                   aList.add("o");
               }
            }
            level.set(i, aList);
        }
        for(int i = 0; i < parameters.length; i++)
        {
            for(int j = 0; j < parameters[i].length; j++)
            {
                currentLevel += level.get(i).get(j);
            }
            currentLevel += "\n";
        }
        input = JOptionPane.showInputDialog(currentLevel + "\n\nWhat do you want to do?");
        try
        {
            if(input.equalsIgnoreCase("move right") || input.equalsIgnoreCase("right"))
            {
                return doLevel("right");
            }
            else if(input.equalsIgnoreCase("left") || input.equalsIgnoreCase("move left"))
            {
                return doLevel("left");
            }
            else if(input.equalsIgnoreCase("up") || input.equalsIgnoreCase("move up"))
            {
                return doLevel("up");
            }
            else if(input.equalsIgnoreCase("down") || input.equalsIgnoreCase("move down"))
            {
                return doLevel("down");
            }
            else if(input.equalsIgnoreCase("save"))
            {
                return doLevel("save");
            }
            else
            {
                return doLevel(input);
            }
        }
        catch(NullPointerException e)
        {
            return doLevel(null);
        }
    } 
    public void checkAction(String action) throws IOException
    {
        try //Test if input was cancel or X
        {
            if(action.equalsIgnoreCase("right"))
            {
                if(oX+1 == endX)
                {
                    if(oY == endY)
                    {                          
                        oX++;
                        JOptionPane.showMessageDialog (null, "Level " + levelNum + " Complete.", "Level " + levelNum + " Complete", JOptionPane.PLAIN_MESSAGE);
                        canLeave = true;
                        return;
                    }
                    else if(oX+1 >= parameters[oY].length)
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(isNoZone[oY][oX+1])
                        {
                            JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            oX++;
                            isDead = battleCheck();
                        }
                    }
                }
                else if(oX+1 >= parameters[oY].length)
                {
                    JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if(isNoZone[oY][oX+1])
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        oX++;
                        isDead = battleCheck();
                    }
                }
            }
            else if(action.equalsIgnoreCase("down"))
            {
                if(oX == endX)
                {
                    if(oY+1 == endY)
                    {
                        oY++;
                        JOptionPane.showMessageDialog (null, "Level " + levelNum + " Complete.", "Level " + levelNum + " Complete", JOptionPane.PLAIN_MESSAGE);
                        canLeave = true;
                        return;
                    }
                    else if(oY+1 >= parameters.length)
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(isNoZone[oY+1][oX])
                        {
                            JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            oY++;
                            isDead = battleCheck();
                        }
                    }    
                }
                else if(oY+1 >= parameters.length)
                {
                    JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    if(isNoZone[oY+1][oX])
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        oY++;
                        isDead = battleCheck();
                    }
                }
            }
            else if(action.equalsIgnoreCase("left"))
            {
                try
                {
                    if(oX-1 == endX)
                    {
                        if(oY == endY)
                        {
                            oX--;
                            JOptionPane.showMessageDialog (null, "Level " + levelNum + " Complete.", "Level " + levelNum + " Complete", JOptionPane.PLAIN_MESSAGE);
                            canLeave = true;
                            return;
                        }
                        else if(oX-1 < 0)
                        {
                            JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            if(isNoZone[oY][oX-1])
                            {
                                JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                oX--;
                                isDead = battleCheck();
                            }
                        }
                    }
                    else if(oX-1 < 0)
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(isNoZone[oY][oX-1])
                        {
                            JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            oX--;
                            isDead = battleCheck();
                        }
                    }
                } 
                catch (ArrayIndexOutOfBoundsException e)
                {
                    JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(action.equalsIgnoreCase("up"))
            {
                try
                {
                        if(oX == endX)
                        {
                            if(oY-1 == endY)
                            {
                                oY--;
                                JOptionPane.showMessageDialog (null, "Level " + levelNum + " Complete.", "Level 1 Complete", JOptionPane.PLAIN_MESSAGE);
                                canLeave = true;
                                return;
                            }
                            else if(oY-1 < 0)
                            {
                                JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                            }
                            else
                            {
                                if(isNoZone[oY-1][oX])
                                {
                                    JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    oY--;
                                    isDead = battleCheck();
                                }
                            }
                        }
                    else if(oY-1 < 0)
                    {
                        JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        if(isNoZone[oY-1][oX])
                        {
                            JOptionPane.showMessageDialog (null, "You cannot move there.", "You cannot move there.", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            oY--;
                            isDead = battleCheck();
                        }
                    }
                } 
                catch (ArrayIndexOutOfBoundsException e)
                {
                    JOptionPane.showMessageDialog (null, "You cannot move out of bounds", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(action.equalsIgnoreCase("save"))
            {
                saveFile.write(levelNum + "\r\n");
                //Person Stats
                saveFile.write(person.getHp() + "\r\n");
                saveFile.write(person.getMaxHp() + "\r\n");
                saveFile.write(person.getAtt() + "\r\n");
                saveFile.write(person.getDef() + "\r\n");
                saveFile.write(person.getExp() + "\r\n");
                saveFile.write(person.getLevel() + "\r\n");
                saveFile.write(person.getPotionNo() + "\r\n");
                //
                saveFile.write(oX + "\r\n");
                saveFile.write(oY + "\r\n");
                saveFile.write(isMember + "\r\n");
                saveFile.close();
                
                JOptionPane.showMessageDialog (null, "File Saved" + action + "." , "File Saved", JOptionPane.PLAIN_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog (null, "I CANNOT COMPUTE " + action + "." , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(NullPointerException e)
        {
            String i = JOptionPane.showInputDialog("Do you want to leave?");
            try
            {
                if(i.equalsIgnoreCase("Yes"))
                {
                    canLeave = true;
                    isDead = true;
                    return;
                }
                else if(i.equalsIgnoreCase("No"))
                {
                }
                else
                {
                    while(!i.equalsIgnoreCase("Yes")||!i.equalsIgnoreCase("No"))
                    {
                        i = JOptionPane.showInputDialog("Do you want to leave?");
                        if(i.equalsIgnoreCase("Yes"))
                        {
                            canLeave = true;
                            isDead = true;
                            return;
                        }
                        else if(i.equalsIgnoreCase("No"))
                        {
                        }
                    }
                }
            }
            catch(NullPointerException f)
            {
                canLeave = true;
                isDead = true;
                return;
            }
        }
    }
    public boolean battleCheck()
    {
        int battleNumber = (int)(100*Math.random());
        /* Encounter List:
         * 1. Garbage Monster 20%
         * 2. Garbage Pile 10%
         */
        Enemy enemy = null;
        int tick = 0;
        int i = 0;
        try
        {
            while(i < enemies[0].length && tick < 1)
            {
                if(battleNumber <= enemies[1][i] && battleNumber > enemies[0][i])
                {
                    enemy = enemyChart.getEnemy(enemies[2][i]); //Gets Garbage Monster
                    tick++;
                }
                i++;
            }
            if(tick == 0)
            {
                return false;
            }
            if(enemy == null)
            {
                throw new NullPointerException("monster cannot be null");
            }
        }
        catch(NullPointerException e)
        {
            throw new NullPointerException("monster cannot be null");
        }
        JOptionPane.showMessageDialog (null, "You are now in battle" , "Battle", JOptionPane.PLAIN_MESSAGE);
        while(!enemyIsDead&&!isDead&&!hasRun)
        {
            battleInput = JOptionPane.showInputDialog(enemy.getName() + " Level " + enemy.getLevel() + "\nHp: " + enemy.getHp() + "\nPerson Level " + person.getLevel() + "\nHp: " + person.getHp() + "\nWhat do you want to do?");
            if(battleInput.equalsIgnoreCase("Run"))
            {
                if(enemy.getName().equalsIgnoreCase("Garbage Godfather"))
                {
                    JOptionPane.showMessageDialog (null,"You cannot run away from da boss.", "You cannot ran away", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    /*How Run Works
                     *Amount of Levels more or less than monster | Percent chance of running
                     *            < -2                                         0%
                     *              -2                                         15%
                     *              -1                                         45%
                     *               0                                         70%
                     *               1                                         90%
                     *             > 1                                         100%
                     */
                    
                    if(checkRun(person.getLevel() - enemy.getLevel()))
                    {
                        JOptionPane.showMessageDialog (null,"You have successfully ran away from " + enemy.getName() + "." , "You have ran away", JOptionPane.PLAIN_MESSAGE);
                        return false;
                    }
                    JOptionPane.showMessageDialog (null,"You have failed to run away from " + enemy.getName() + "." , "You could not run away", JOptionPane.PLAIN_MESSAGE);
                    isAction = true;
                }
            }
            else if(battleInput.equalsIgnoreCase("Attack"))
            {
                personDamage = calculateDamage(person.getAtt(), enemy.getDef(), person.getLevel() - enemy.getLevel());
                enemy.takeHit(personDamage);
                JOptionPane.showMessageDialog (null, enemy.getName() + " has taken " + personDamage + " damage." , "Enemy takes damage", JOptionPane.PLAIN_MESSAGE);
                if(enemy.getHp() <= 0)
                {
                    JOptionPane.showMessageDialog (null, "Enemy is dead." , "Victory", JOptionPane.PLAIN_MESSAGE);
                    //Members also get double exp and a higher potion chance.
                    if(isMember)
                    {
                        JOptionPane.showMessageDialog (null, "You have gained " + enemy.getExpReward()*2 + " experience." , "Victory", JOptionPane.PLAIN_MESSAGE);
                        person.setExp(enemy.getExpReward()*2);
                        potionChance = (int)(100*Math.random());
                        if(potionChance <= 70)
                        {
                           person.setPotionNo(); 
                           JOptionPane.showMessageDialog (null, "You have gained a potion." , "Potions!", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog (null, "You have gained " + enemy.getExpReward() + " experience." , "Victory", JOptionPane.PLAIN_MESSAGE);
                        person.setExp(enemy.getExpReward());
                        potionChance = (int)(100*Math.random());
                        if(potionChance <= 30)
                        {
                           person.setPotionNo(); 
                           JOptionPane.showMessageDialog (null, "You have gained a potion." , "Potions!", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    return false;
                }
                isAction = true;
            }
            else if(battleInput.equalsIgnoreCase("Examine"))
            {
                JOptionPane.showMessageDialog (null, enemy.getExamineText() , "Examine", JOptionPane.ERROR_MESSAGE);
            }
            else if(battleInput.equalsIgnoreCase("Use Potion") || battleInput.equalsIgnoreCase("Potion"))
            {
                if(person.getPotionNo() > 0)
                {
                    int potionHeal = person.depletePotion();
                    JOptionPane.showMessageDialog (null, "You have healed " + potionHeal + "." , "Potion Used", JOptionPane.ERROR_MESSAGE);
                    isAction = true;
                }
                else
                {
                    JOptionPane.showMessageDialog (null, "You do not have any potions." , "No Potions", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(battleInput.equalsIgnoreCase("Check Potion") || battleInput.equalsIgnoreCase("Check Potions"))
            {
                if(person.getPotionNo() > 0)
                {
                    JOptionPane.showMessageDialog (null, "You have " + person.getPotionNo() + " potion(s) left." , "Potions Left", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog (null, "You have no potions left." , "Potions Left", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog (null, "I CANNOT COMPUTE " + battleInput + "." , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            if(isAction)
            {
                enemyDamage = enemyCalcDamage(enemy.getAtt(), person.getDef(), enemy.getLevel() - person.getLevel());
                person.takeHit(enemyDamage);
                JOptionPane.showMessageDialog (null," You have taken " + enemyDamage + " damage." , "You take damage", JOptionPane.PLAIN_MESSAGE);
                if(person.getHp() <= 0)
                {
                    JOptionPane.showMessageDialog (null, "You are dead." , "OH NO!", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
                isAction = false;
            }
        }
        return false;
    }
    public boolean checkRun(int diff)
    {
        if(diff < -2)
        {
            return false;
        }
        else if(diff == -2 && ((int)(100*Math.random()))<= 15)
        {
            return true;
        }
        else if(diff == -1 && ((int)(100*Math.random()))<= 45)
        {
            return true;
        }
        else if(diff == 0 && ((int)(100*Math.random()))<= 70)
        {
            return true;
        }
        else if(diff == 1 && ((int)(100*Math.random()))<= 90)
        {
            return true;
        }
        else if(diff == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int calculateDamage(int onesAtt, int twosDef, int levelDiff)
    {
        //Damage is based on how much att one has - how much def two has.
        /*How accuracy works (based on level)
         *Amount of Levels more or less than monster | Percent chance of hitting
         *            < -3                                         0%
         *              -3                                         15%
         *              -2                                         50%
         *              -1                                         90%
         *            => 0                                         100%
         * Missing is the ONLY WAY to HIT A 0.
        */
        if(levelDiff < -3)
        {
            return 0;
        }
        else if(levelDiff == -3 && ((int)(100*Math.random()))>= 15)
        {
            return 0;
        }
        else if(levelDiff == -2 && ((int)(100*Math.random()))>= 50)
        {
            return 0;
        }
        else if(levelDiff == -1 && ((int)(100*Math.random()))>= 90)
        {
            return 0;
        }
        else if (onesAtt-twosDef < 1)
        {
            return 1;
        }
        else
        {
            return onesAtt-twosDef;
        }
    }
    public int enemyCalcDamage(int onesAtt, int twosDef, int levelDiff)
    {
        //Damage is based on how much att one has - how much def two has.
        /*How accuracy works FOR ENEMIES (based on level)
         *To prevent easy training, monsters will ALWAYS be able to hit you
         *Amount of Levels more or less than monster | Percent chance of hitting
         *            < -2                                         50%
         *              -2                                         70%
         *              -1                                         90%
         *            => 0                                         100%
         * HAHA!  Have fun not being invincible!
        */
        if(levelDiff <= -3 && ((int)(100*Math.random()))>= 50)
        {
            return 0;
        }
        else if(levelDiff == -2 && ((int)(100*Math.random()))>= 70)
        {
            return 0;
        }
        else if(levelDiff == -1 && ((int)(100*Math.random()))>= 90)
        {
            return 0;
        }
        else if (onesAtt-twosDef < 1)
        {
            return 1;
        }
        else
        {
            return onesAtt-twosDef;
        }
    }
}
