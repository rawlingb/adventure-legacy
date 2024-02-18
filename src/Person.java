import javax.swing.*;
public class Person
{
    private int hp;
    private int maxHp;
    private int att;
    private int def;
    private int exp;
    private int level;
    private int potionNo;
    private boolean isMember;
    public Person()
    {
        this.hp = 10;
        this.maxHp = 10;
        this.att = 3;
        this.def = 1;
        this.exp = 0;
        this.level = 1;
        this.potionNo = 3;
        this.isMember = false;
    }
    public Person(boolean isMember)
    {
        // DEBUG CODE: If you want to test the game, uncomment
        // below and comment out the release code segment.
        //this.hp = 999;
        //this.maxHp = 999;
        //this.att = 999;
        //this.def = 999;
        //this.exp = 9999999;
        //this.level = 20;
        //this.potionNo = 2000;
        //this.isMember = false;

        // Release Code:
        this.hp = 10;
        this.maxHp = 10;
        this.att = 3;
        this.def = 1;
        this.exp = 0;
        this.level = 1;
        this.potionNo = 3;
        this.isMember = isMember;
    }
    public Person(int hp, int maxHp, int att, int def, int exp, int level, int potionNo, boolean isMember)
    {
        this.hp = hp;
        this.maxHp = maxHp;
        this.att = att;
        this.def = def;
        this.exp = exp;
        this.level = level;
        this.potionNo = potionNo;
        this.isMember = isMember;
    }
    public int getHp()
    {
        return hp;
    }
    public int getMaxHp()
    {
        return maxHp;
    }
    public int getAtt()
    {
        return att;
    }
    public int getDef()
    {
        return def;
    }
    public int getExp()
    {
        return exp;
    }
    public int getLevel()
    {
        return level;
    }
    public int getPotionNo()
    {
        return potionNo;
    }
    public boolean getIsMember()
    {
        return isMember;
    }
    public void takeHit(int damage)
    {
        hp -= damage;
    }
    public void healPerson()
    {
        hp = maxHp;
    }
    public void setExp(int enemyExp)
    {
        hp = maxHp;
        exp += enemyExp;
        chartCheck();
    }
    public void setPotionNo()
    {
        potionNo++;
    }
    public int depletePotion()
    {
        potionNo--;
        int healFor = maxHp-hp;
        hp = hp+(maxHp-hp);
        return healFor;
    }
    public void chartCheck()
    {
        if(level == 1 && exp >= 12)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 2;
            att+= 3;
            maxHp += 10;
            def += 1;
            exp -= 12;
        }
        if(level == 2 && exp >= 26)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 3;
            att+= 3;
            maxHp += 10;
            def += 1;
            exp -= 26;
        }
        if(level == 3 && exp >= 57)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 4;
            att+= 3;
            maxHp += 10;
            def += 1;
            exp -= 57;
        }
        if(level == 4 && exp >= 92)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 5;
            att+= 3;
            maxHp += 10;
            def += 1;
            exp -= 92;
        }
        if(level == 5 && exp >= 156)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 6;
            att+= 3;
            maxHp += 10;
            def += 2;
            exp -= 156;
        }
        if(level == 6 && exp >= 275)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 7;
            att+= 3;
            maxHp += 10;
            def += 2;
            exp -= 275;
        }
        if(level == 7 && exp >= 524)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 8;
            att+= 3;
            maxHp += 10;
            def += 2;
            exp -= 524;
        }
        if(level == 8 && exp >= 1382)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 9;
            att+= 3;
            maxHp += 10;
            def += 2;
            exp -= 1382;
        }
        if(level == 9 && exp >= 2768)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 10;
            att+= 3;
            maxHp += 10;
            def += 2;
            exp -= 2768;
        }
        if(level == 10 && exp >= 4125)
        {
            if(isMember)
            {
                JOptionPane.showMessageDialog (null,"For levels above 10, you need to have preminum Membership.  Memebers get:\n40 extra levels on character,\naccess to 10 extra levels and\neven funnier content.\nTry it now.  It's only $9.99." , "Buy Preminum now!", JOptionPane.PLAIN_MESSAGE);
                //By the way, this is a joke, because many games do this, even though this game has Membership
            }
            else
            {
                JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
                level = 11;
                att+= 5;
                maxHp += 15;
                def += 3;
                exp -= 4125;
            }
        }
        //Members get easier lives, with only a +1200 addition every level.
        if(level == 11 && exp >= 5325)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 12;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 5325;
        }
        if(level == 12 && exp >= 6525)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 13;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 6525;
        }
        if(level == 13 && exp >= 7725)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 14;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 7725;
        }
        if(level == 14 && exp >= 8925)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 15;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 8925;
        }
        if(level == 15 && exp >= 10125)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 16;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 10125;
        }
        if(level == 16 && exp >= 11325)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 17;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 11325;
        }
        if(level == 17 && exp >= 12525)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 18;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 12525;
        }
        if(level == 18 && exp >= 13725)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 19;
            att+= 5;
            maxHp += 15;
            def += 3;
            exp -= 13725;
        }
        if(level == 19 && exp >= 14925)
        {
            JOptionPane.showMessageDialog (null,"You are now level " + (level+1) + "." , "Level Up!", JOptionPane.PLAIN_MESSAGE);
            level = 20;
            att+= 7;
            maxHp += 20;
            def += 3;
            exp -= 14925;
        }
        if(level == 20 && exp >= 16125)
        {
            JOptionPane.showMessageDialog (null,"Because of time constraints, the Developer only had time to make 20 levels.  Sorry for that. I could give you a refund, but then I get your 10 levels.  Sorry again for that." , "Lies Were Told", JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            
            
            
        }
        hp = maxHp;
    }
}
