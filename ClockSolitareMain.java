// The "ClockSolitareMain" class.
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class ClockSolitareMain extends Applet implements MouseListener, MouseMotionListener
{
    // Place instance variables here


    //images
    Image[] [] images = new Image [13] [4];
    Image[] otherImages = new Image [3];

    //the Game object
    Game a;

    int highScore;

    boolean isStart;
    int isWon; //0 = no No Decision, 1= win, 2 = loss.
    int time;
    long oldTime;

    Graphics g;

    //Layout:
    GridBagLayout lm = new GridBagLayout ();
    GridBagConstraints p = new GridBagConstraints ();


    //labels:
    Label scoreLabel = new Label ("Score: ");
    Label authorLabel = new Label ("Developer: Dylan Snelgrove ");
    Label musicLabel = new Label ("Music by: Lobo Loco");
    Label title = new Label ("Clock Solitare");
    Label highScoreLabel = new Label ("High Score: ");
    Label timeLabel = new Label ("Time: ");

    //text field:
    TextField scoreField = new TextField (5);
    TextField highScoreField = new TextField (5);
    TextField timeField = new TextField (5);

    //buttons:
    Button restart = new Button (" Start! ");


    //double buffering
    Graphics bufferGraphics;
    Image offscreen;

    public void init ()
    {
	setSize (1080, 640);
	resize (1080, 640);
	setLayout (lm);

	p.gridx = 0;
	p.gridy = 0;
	p.anchor = GridBagConstraints.NORTHWEST;
	p.weightx = 0;
	p.weighty = 0;

	lm.setConstraints (title, p);
	add (title);
	p.gridx = 1;
	p.gridy = 0;
	lm.setConstraints (authorLabel, p);
	add (authorLabel);
	p.gridx = 0;
	p.gridy = 1;

	lm.setConstraints (scoreLabel, p);
	add (scoreLabel);
	p.gridx = 1;
	lm.setConstraints (scoreField, p);
	add (scoreField);
	p.gridx = 0;
	p.gridy = 3;
	lm.setConstraints (highScoreLabel, p);
	add (highScoreLabel);
	p.gridx = 1;
	lm.setConstraints (highScoreField, p);
	add (highScoreField);
	p.gridx = 0;
	p.gridy = 4;
	lm.setConstraints (restart, p);
	add (restart);
	p.gridx = 0;
	p.gridy = 5;
	p.weighty = 1;
	lm.setConstraints (timeLabel, p);
	add (timeLabel);
	p.gridx = 1;
	lm.setConstraints (timeField, p);
	add (timeField);






	//double buffering set-up
	//back-color set-up
	Color clr = new Color (18, 185, 24);
	setBackground (clr);
	offscreen = createImage (1024, 576);
	bufferGraphics = offscreen.getGraphics ();

	//add mouse listeners
	addMouseListener (this);
	addMouseMotionListener (this);

	a = new Game ();

	highScore = 0;
	isStart = false;
	isWon = 0;
	time = 60 * 1000;
	oldTime = System.currentTimeMillis ();

	scoreField.setText ("" + a.getScore ());
	highScoreField.setText ("" + highScore);
	timeField.setText ("" + time / 1000);



	//loads images
	for (int i = 1 ; i < 14 ; i++)
	{
	    for (int j = 0 ; j < 4 ; j++)
	    {
		String a = "";
		String b = "";
		if (i == 1)
		{
		    a = "a";
		}
		else if (i < 10)
		{
		    a = "" + i;
		}
		else if (i == 10)
		{
		    a = "t";
		}
		else if (i == 11)
		{
		    a = "j";
		}
		else if (i == 12)
		{
		    a = "q";
		}
		else if (i == 13)
		{
		    a = "k";
		}

		if (j == 0)
		{
		    b = "d";
		}
		else if (j == 1)
		{
		    b = "h";
		}
		else if (j == 2)
		{
		    b = "c";
		}
		else if (j == 3)
		{
		    b = "s";
		}

		images [i - 1] [j] = getImage (getCodeBase (), "cards/" + a + b + ".gif");
	    }
	}
	otherImages [0] = getImage (getCodeBase (), "Instructions.png");
	otherImages [1] = getImage (getCodeBase (), "Win.png");
	otherImages [2] = getImage (getCodeBase (), "lose.png");
    } // init method


    public void mouseClicked (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent e)
    {
	a.clickHandler (e.getX (), e.getY ());
	repaint ();
    }


    public void mouseReleased (MouseEvent e)
    {
	int lastScore = a.getScore ();
	a.releaseHandler (e.getX (), e.getY ());
	int score = a.getScore ();
	scoreField.setText ("" + score);
	if (score > highScore)
	{
	    highScore = score;
	    highScoreField.setText ("" + highScore);
	}
	if (lastScore < score)
	{
	    time += 10 * 1000;
	}
	if (score > 39)
	{
	    isWon = 1;
	}
	repaint ();
    }


    public void mouseMoved (MouseEvent e)
    {
	repaint ();
    }


    public void mouseDragged (MouseEvent e)
    {


	a.dragHandler (e.getX (), e.getY ());
	repaint ();
    }


    public boolean action (Event e, Object o)
    {
	if (e.target instanceof Button)
	{
	    if (e.target == restart)
	    {
		if (!isStart)
		{
		    isStart = true;
		    oldTime = System.currentTimeMillis ();
		    restart.setLabel ("Restart");
		    repaint ();
		    return true;
		}
		if (isStart)
		{
		    isStart = false;
		    a.restart ();
		    isWon = 0; //0 = no No Decision, 1= win, 2 = loss.
		    time = 60 * 1000;
		    oldTime = System.currentTimeMillis ();
		    scoreField.setText ("" + a.getScore ());
		    timeField.setText ("" + (int) time / 1000);
		    restart.setLabel (" Start! ");
		    repaint ();
		    return true;
		}

	    }
	}
	repaint ();
	return true;

    }


    public void paint (Graphics g)
    {
	bufferGraphics.clearRect (0, 0, 1024, 576);
	if (isStart && isWon == 0)
	{

	    a.draw (bufferGraphics, images, this);

	}
	if (!isStart)
	{
	    bufferGraphics.drawImage (otherImages [0], 0, 150, this);
	}
	if (isWon == 1)
	{
	    bufferGraphics.drawImage (otherImages [1], 0, 150, this);
	}
	if (isWon == 2)
	{
	    bufferGraphics.drawImage (otherImages [2], 0, 150, this);
	}
	g.drawImage (offscreen, 0, 0, this);
	// Place the body of the drawing method here
    } // paint method


    public void update (Graphics g)
    {
	if (isStart && isWon == 0)
	{
	    time = time - (int) ((System.currentTimeMillis () - oldTime));
	    timeField.setText ("" + (int) time / 1000);
	    oldTime = System.currentTimeMillis ();
	    if (isStart && isWon == 0 && time <= 0)
	    {
		isWon = 2;
	    }
	}


	paint (g);
    }
} // ClockSolitareMain class
