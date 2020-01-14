/*
    Name: Dylan Snelgrove
    Date: January 12, 2018
    
    Card
     - contains all the data to draw the card
     - has contructors and a draw to draw it all
*/

import java.awt.*;
import java.applet.*;
import java.awt.image.*;

public class Card extends ShapeClass
{

    //instantiated data
    private int faceValue; //1 - 13
    private int suit;  //Diamond, Heart, Club, Spade
    private boolean isShowing;
    private int size; //1-4, 1 = small, 4 = extra large

    //constructors
    public Card ()
    {
	faceValue = 1;
	suit = 1;
	super.setCenterX (200);
	super.setCenterY (200);
	super.setWidth ((int) (80 * 0.7));
	super.setHeight (80);
	super.setColor (Color.black);
	isShowing = true;
	size = 2;
    }


    public Card (int pFaceValue, int pSuit)
    {
	faceValue = 1;
	suit = 1;
	super.setCenterX (200);
	super.setCenterY (200);
	super.setWidth ((int) (80 * 0.7));
	super.setHeight (80);
	size = 2;
	super.setColor (Color.black);
	isShowing = true;

	if (pFaceValue >= 1 && pFaceValue <= 13)
	{
	    faceValue = pFaceValue;
	}
	if (pSuit >= 1 && pSuit <= 4)
	{
	    suit = pSuit;
	}


    }


    //communicators

    public void setSize (int ipSize)
    {
	if (ipSize >= 1 && ipSize <= 4)
	{
	    size = ipSize;
	}

	if (ipSize == 1)
	{
	    super.setWidth ((int) (60 * 0.7));
	    super.setHeight (60);
	}
	else if (ipSize == 2)
	{
	    super.setWidth ((int) (80 * 0.7));
	    super.setHeight (80);
	}
	else if (ipSize == 3)
	{
	    super.setWidth ((int) (100 * 0.7));
	    super.setHeight (100);
	}
	else if (ipSize == 4)
	{
	    super.setWidth ((int) (120 * 0.7));
	    super.setHeight (120);
	}
    }


    public void setFaceValue (int ipFaceValue)
    {
	if (ipFaceValue >= 1 && ipFaceValue <= 13)
	{
	    faceValue = ipFaceValue;
	}
    }


    public void setSuit (int ipSuit)
    {
	if (ipSuit >= 1 && ipSuit <= 4)
	{
	    suit = ipSuit;
	}
    }


    public void setIsShowing (boolean ipIsShowing)
    {
	isShowing = ipIsShowing;
    }


    int getSize ()
    {
	return size;
    }


    int getSuit ()
    {
	return suit;
    }


    int getFaceValue ()
    {
	return faceValue;
    }


    boolean getIsShowing ()
    {
	return isShowing;
    }


    public void setWidth (int ipWidth) //overrides
    {
    }


    public void setHeight (int ipHeight)
    {
    }


    //actions
    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	if (getColor () == Color.white)
	{
	    g.setColor (Color.white);
	    g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth () + 1, getHeight () + 1);
	}
	else
	{
	    if (isShowing)
	    {
		g.drawImage (images [faceValue - 1] [suit - 1], getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight (), imgObs);
	    }
	}
    }
}

