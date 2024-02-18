public class EnemyChart
{
    int hp;
    int att;
    int def;
    int expReward;
    int level;
    String examineText;
    String name;
    public Enemy getEnemy(int num)
    {
        if(num == 1)
        {
            hp = 7;
            att = 2;
            def = 1;
            expReward = 3;
            level = 1;
            name = "Garbage Monster";
            examineText = "Just a Garbage Monster.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 2)
        {
            hp = 3;
            att = 2;
            def = 1;
            expReward = 1;
            level = 1;
            name = "Garbage Pile";
            examineText = "Nothing special here.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 3)
        {
            hp = 12;
            att = 3;
            def = 1;
            expReward = 5;
            level = 2;
            name = "Furry";
            examineText = "Awww. It's so cute.  Ok, I'm bored, LET'S KILL IT!";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 4)
        {
            hp = 23;
            att = 5;
            def = 3;
            expReward = 8;
            level = 3;
            name = "Dark Furry";
            examineText = "Not so cute anymore";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 5)
        {
            hp = 20;
            att = 3;
            def = 7;
            expReward = 11;
            level = 3;
            name = "Lingo";
            examineText = "What is that thing?";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 6)
        {
            hp = 32;
            att = 11;
            def = 4;
            expReward = 15;
            level = 4;
            name = "Swordsman";
            examineText = "A wielder of fine swords.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 7)
        {
            hp = 55;
            att = 10;
            def = 8;
            expReward = 19;
            level = 5;
            name = "Star Warrior";
            examineText = "Hmmm... Sounds like something from Kirby.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 8)
        {
            hp = 67;
            att = 15;
            def = 10;
            expReward = 22;
            level = 6;
            name = "Augmented Garbage Monster";
            examineText = "Chemically strengthented...and chemically smellier too.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 9)
        {
            hp = 78;
            att = 8;
            def = 13;
            expReward = 25;
            level = 6;
            name = "Armed Camel";
            examineText = "What is this, 2078 taken over by camels?!";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 10)
        {
            hp = 38;
            att = 25;
            def = 8;
            expReward = 32;
            level = 7;
            name = "Secret Agent P.E.N.G";
            examineText = "Definitely not a Penuin.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 11)
        {
            hp = 92;
            att = 18;
            def = 12;
            expReward = 31;
            level = 7;
            name = "Garbage Mafia";
            examineText = "A Member high up in The Garbage Ranks.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 12)
        {
            hp = 150;
            att = 22;
            def = 15;
            expReward = 150;
            level = 7;
            name = "Garbage Godfather";
            examineText = "The boss of this game.  And yes, I did take this from Town of Salem.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        if(num == 13)
        {
            hp = 350;
            att = 57;
            def = 32;
            expReward = 106;
            level = 15;
            name = "Exiled Wanderer";
            examineText = "This is the reason why people pay me $9.99, because the membership helps so much against this guy.";
            Enemy enemy = new Enemy(hp, att, def, expReward, level, name, examineText);
            return enemy;
        }
        else
        {
            return null;
        }
    }
}
