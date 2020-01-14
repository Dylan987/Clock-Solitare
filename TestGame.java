// The "TestGame" class.
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class TestGame extends Applet implements MouseListener, MouseMotionListener
{
    // Place instance variables here

    Image[] [] images = new Image [13] [4];
    Game test = new Game ();

    //double buffering
    Graphics bufferGraphics;
    Image offscreen;
    Dimension dim;
    int curX, curY;




    public void init ()
    {
	setSize(1024,576);
	dim = getSize ();
	Color clr = new Color(18, 185, 24);
	setBackground (clr);
	offscreen = createImage (dim.width, dim.height);
	bufferGraphics = offscreen.getGraphics ();

	addMouseListener (this);
	addMouseMotionListener (this);


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
	test.clickHandler (e.getX (), e.getY ());
	repaint ();
    }


    public void mouseReleased (MouseEvent e)
    {
	test.releaseHandler (e.getX (), e.getY ());
	repaint ();
    }


    public void mouseMoved (MouseEvent e)
    {
    }


    public void mouseDragged (MouseEvent e)
    {
	test.dragHandler (e.getX (), e.getY ());
	repaint ();
    }


    public void paint (Graphics g)
    {
	bufferGraphics.clearRect (0, 0, dim.width, dim.width);
	test.draw (bufferGraphics, images, this);
	g.drawImage (offscreen, 0, 0, this);
	// Place the body of the drawing method here
    } // paint method
    
    public void update (Graphics g)
    {
	paint (g);
    }
} // TestGame class
