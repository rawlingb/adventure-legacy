public class Enemy
{
    private int hp;
    private int att;
    private int def;
    private int expReward;
    private int level;
    private String examineText;
    private String name;
    public Enemy()
    {
        
    } 
    public Enemy(int hp, int att, int def, int expReward, int level, String name, String eText)
    {
        this.hp = hp;
        this.att = att;
        this.def = def;
        this.expReward = expReward;
        this.level = level;
        this.name = name;
        this.examineText = eText;
    }
    public int getHp()
    {
        return hp;
    }
    public int getAtt()
    {
        return att;
    }
    public int getDef()
    {
        return def;
    }
    public int getExpReward()
    {
        return expReward;
    }
    public int getLevel()
    {
        return level;
    }
    public String getName()
    {
        return name;
    }
    public String getExamineText()
    {
        return examineText;
    }
    public void takeHit(int damage)
    {
        hp -= damage;
    }
}
