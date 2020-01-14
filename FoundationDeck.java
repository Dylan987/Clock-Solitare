/* By: Dylan Snelgrove:
   Date: January 2018
   The Foundation Deck
    - add is only +1 and in suit
    - capped at max value
    - starts at given min value
    - given min value determines suit of start card, and thus pile
    - min value also determines position (clock arrangement)
    - no deletion.
    - draw is normal, except box when complete.
*/

import java.awt.*;
import java.util.*;
import java.awt.image.*;

public class FoundationDeck extends Deck
{
    private int suitValue;
    private int startValue;
    private int endValue;

    public FoundationDeck ()
    { //not to be used
	super ();
    }


    public FoundationDeck (int pStartValue, int gameX, int gameY, int gameRadius)  //only use this contructor.
    { // takes startValue of foundation, a parameters of the clock circle
	// gives correct card positions and end values.
	super ();
	if (pStartValue % 4 == 0)
	{
	    suitValue = 1;
	}
	else if (pStartValue % 4 == 1)
	{
	    suitValue = 3;
	}
	else if (pStartValue % 4 == 2)
	{
	    suitValue = 2;
	}
	else if (pStartValue % 4 == 3)
	{
	    suitValue = 4;
	}

	//switch statment
	switch (pStartValue)
	{
	    case 13:
		endValue = 4;
		setCenter ((int) (gameX + Math.cos (Math.toRadians (30)) * gameRadius), (int) (gameY + Math.sin (Math.toRadians (30)) * gameRadius));
		break;
	    case 2:
		endValue = 5;
		setCenter ((int) (gameX + Math.cos (Math.toRadians (60)) * gameRadius), (int) (gameY + Math.sin (Math.toRadians (60)) * gameRadius));
		break;
	    case 3:
		endValue = 6;
		setCenter (gameX, gameY + gameRadius);
		break;
	    case 4:
		endValue = 7;
		setCenter ((int) (gameX - Math.cos (Math.toRadians (60)) * gameRadius), (int) (gameY + Math.sin (Math.toRadians (60)) * gameRadius));
		break;
	    case 5:
		endValue = 8;
		setCenter ((int) (gameX - Math.cos (Math.toRadians (30)) * gameRadius), (int) (gameY + Math.sin (Math.toRadians (30)) * gameRadius));
		break;
	    case 6:
		endValue = 9;
		setCenter (gameX - gameRadius, gameY);
		break;
	    case 7:
		endValue = 10;
		setCenter ((int) (gameX - Math.cos (Math.toRadians (30)) * gameRadius), (int) (gameY - Math.sin (Math.toRadians (30)) * gameRadius));
		break;
	    case 8:
		endValue = 11;
		setCenter ((int) (gameX - Math.cos (Math.toRadians (60)) * gameRadius), (int) (gameY - Math.sin (Math.toRadians (60)) * gameRadius));
		break;
	    case 9:
		endValue = 12;
		setCenter (gameX, gameY - gameRadius);
		break;
	    case 10:
		endValue = 1;
		setCenter ((int) (gameX + Math.cos (Math.toRadians (60)) * gameRadius), (int) (gameY - Math.sin (Math.toRadians (60)) * gameRadius));
		break;
	    case 11:
		endValue = 2;
		setCenter ((int) (gameX + Math.cos (Math.toRadians (30)) * gameRadius), (int) (gameY - Math.sin (Math.toRadians (30)) * gameRadius));
		break;
	    case 12:
		endValue = 3;
		setCenter (gameX + gameRadius, gameY);
		break;
	    default:
		endValue = 4;
		setCenter ((int) (gameX + Math.cos (Math.toRadians (30)) * gameRadius), (int) (gameY + Math.sin (Math.toRadians (30)) * gameRadius));
		break;
	}
	startValue = pStartValue;
	Card c = new Card ();
	c.setFaceValue (startValue);
	c.setSuit (suitValue);
	super.add (c, 0);
    }


    public boolean okToAdd (Card proposedCard)  // checks if a given card should be allowed into the deck
    {
	Card topCard = peek (0);

	//test for suit
	if (topCard.getSuit () != proposedCard.getSuit ())
	{
	    return false;
	}


	//test for value
	if (((topCard.getFaceValue () + 1) % 13 != proposedCard.getFaceValue ()) && (!(topCard.getFaceValue () == 12 && proposedCard.getFaceValue () == 13)))
	{
	    return false;
	}

	//test for max
	if (topCard.getFaceValue () == endValue)
	{
	    return false;
	}

	return true;

    }


    public void add (Card cd)  //overrides the add of deck with a legality check
    {
	if (okToAdd (cd))
	{
	    super.add (cd, 0);
	}
    }


    public Card delete (int pos)  // deletion is not allowed, so it returns null. This should cause an error if accidentally called.
    {
	return null; //remember to check for null in the main.
    }


    public boolean isInside (int x, int y)
    {
	int cx = getCenterX ();
	int cy = getCenterY ();
	int sx = getWidth () / 2;
	int sy = getHeight () / 2;

	if (x > cx - sx && x < cx + sx && y > cy - sy && y < cy + sy)
	{
	    return true;
	}
	return false;
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)  //Draws the foundation deck using the image. Box added if deck is complete
    {
	super.draw (g, images, imgObs);
	if (peek (0).getFaceValue () == endValue)
	{
	    g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	}
    }
}


