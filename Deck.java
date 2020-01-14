/*
    By: Dylan Snelgrove
    Date: January, 2018

    The "Deck" Class
    - holds many cards using a vector
    - the parent class for "TableauDeck" and "FoundationDeck"
    - uses a vector to store cards
    - communicators also
    - contructor option for standard deck of 52 cards.
    - shuffle available


*/

import java.awt.*;
import java.util.*;
import java.awt.image.*;


public class Deck extends ShapeClass
{

    private int size;
    private boolean isShowing;
    private Vector deck = new Vector (0, 1);

    //constructor
    public Deck ()
    {
	size = 2;
	isShowing = true;

	setCenter (200, 200);
	setDimensions ((int) (80 * 0.7), 80);
	setColor (Color.black);


    }


    public Deck (String type)
    {
	this ();

	if (type == "standard")
	{
	    for (int i = 1 ; i <= 4 ; i++)
	    {
		for (int j = 1 ; j <= 13 ; j++)
		{
		    Card toAdd = new Card (j, i);
		    deck.insertElementAt (toAdd, 0);
		}
	    }
	}

    }


    //communicators

    public void setSize (int size)
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    Card newCard = (Card) deck.elementAt (i);
	    newCard.setSize (size);
	    deck.setElementAt (newCard, i);

	    setHeight (40 + 20 * size);
	    setWidth ((int) (0.7 * (40 + 20 * size)));
	}
    }


    public void setCenter (int ipCenterX, int ipCenterY)
    {
	super.setCenter (ipCenterX, ipCenterY);

	for (int i = 0 ; i < deck.size () ; i++)
	{
	    Card newCard = (Card) deck.elementAt (i);
	    newCard.setCenter (ipCenterX, ipCenterY);
	    deck.setElementAt (newCard, i);
	}

    }


    public int getSize ()
    {
	return size;
    }


    int getNumberOfCards ()
    {
	return deck.size ();
    }


    public void setShowing (boolean bpIsShowing)
    {
	isShowing = bpIsShowing;

	for (int i = 0 ; i < deck.size () ; i++)
	{
	    Card newCard = (Card) deck.elementAt (i);
	    newCard.setIsShowing (bpIsShowing);
	    deck.setElementAt (newCard, i);
	}
    }


    public boolean getShowing ()
    {
	return isShowing;
    }


    //action

    public void add (Card cd, int pos)
    {
	cd.setCenter (getCenterX (), getCenterY ());
	cd.setSize (getSize ());
	deck.insertElementAt (cd, pos);
	setCenter(getCenterX(), getCenterY());
    }


    public Card delete (int pos)
    {
	return (Card) deck.remove (pos);
    }


    public Card peek (int pos)
    {
	return (Card) deck.elementAt (pos);
    }


    public void shuffle ()
    {
	for (int i = 0 ; i < deck.size () * 4 ; i++)
	{
	    deck.insertElementAt (deck.remove (0), ((int) (Math.random () * (deck.size () + 1))));
	}
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	if (getColor () == Color.white)
	{
	    g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	}
	else
	{

	    if (deck.size () == 0)
	    {
		g.setColor (getColor ());
		g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	    }
	    else
	    {
		Card drawCard = (Card) deck.elementAt (0);
		drawCard.draw (g, images, imgObs);

	    }
	}
    }
}
