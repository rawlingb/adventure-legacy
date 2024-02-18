import javax.swing.JOptionPane;
public class StartScreen implements ScreenFace
{
    private int tick;//Checks how many times someone presses enter on the start screen
    private boolean canLeave = false;
    public int loadScreen()
    {
        while(!canLeave)
        {
            try
            {
                String start = JOptionPane.showInputDialog("Adventures of Bob (Yeah, everyone's name is now Bob) \nStart \nLoad \nInstructions \nExit");
                if(start.equals(""))
                {
                    tick++;
                    if(tick > 4)
                    {
                        JOptionPane.showMessageDialog (null, "Seriously, you don't know how to type start, instructions, or exit?", "Seriously?!", JOptionPane.ERROR_MESSAGE);
                        tick = 0;
                    }
                }
                else if(start.equalsIgnoreCase("start"))
                {
                    return 0;  
                }
                else if(start.equalsIgnoreCase("load"))
                {
                    return 2;  
                }
                else if(start.equalsIgnoreCase("instructions"))
                {
                    tick = 0;
                    instructions();
                }
                else if(start.equalsIgnoreCase("exit"))
                {
                    return 1;
                }
                else if(start.equalsIgnoreCase("278992343")||start.equalsIgnoreCase("231245767")||start.equalsIgnoreCase("298731452"))
                {
                    return 3;
                }
            }
            catch(NullPointerException e)
            {
                return 1;
            }
        }  
        return 0;
        //Return 0 if not exiting program, 1 if program exits because of pressing cancel or typing in exit.
    }
    public void instructions()
    {
        JOptionPane.showMessageDialog (null, "This is a rpg game where you can move around by typing in certain key words.\nOccasionally, you can enter battles, where you fight monsters and may be able to\nget some cool stuff.  There is a boss at the end.  P is your character, o is terrain,\nx is unaccessable areas, E is the end of the level, and B takes you to the boss.  \nThere is also membership for $9.99, but if you ask me nicely, maybe I can give you a good deal.", "Instructions", JOptionPane.PLAIN_MESSAGE);
    }
}
