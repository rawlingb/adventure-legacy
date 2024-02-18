import java.util.*;
import javax.swing.*;
import java.io.*;
public class LevelThree extends Level
{
    public LevelThree(Person p, EnemyChart e, int eX, int eY, int [][] ens, int [][] par, boolean isBoss, boolean[][] isNoZone, boolean isMember, FileWriter sf, int oX, int oY)
    {
        super(p, e, 3, eX, eY, ens, par, isBoss, isNoZone, isMember, sf, oX, oY);
    }
    public int doLevel(String action) throws IOException
    {
        return super.doLevel(action);
    } 
}

