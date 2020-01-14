/*
    By: Dylan Snelgrove
    Date: January 2018

    The Tableau Deck
    - add is only -1 and offcolor
    - capped at Ace (no wrap around, ace low).
    - must display all cards in fan
    - deletion complex:
	- if top card selected, all good
	- if not top card selected, check for string (perfect sequence to top card).
	- transport as a deck.
*/

import java.awt.*;
import java.util.*;
import java.awt.image.*;

public class TableauDeck extends Deck
{

    public TableauDeck ()
    { //should be the only one used., Tableau decks start empty
	super ();
    }


    public boolean okToAdd (Card proposedCard)  //Returns true is adding is allowed based on the rules
    {
	if (getNumberOfCards () == 0)
	{
	    return true;
	}
	Card topCard = peek (0);

	//test for suit
	if ((topCard.getSuit () <= 2) == (proposedCard.getSuit () <= 2))
	{
	    return false;
	}

	if ((topCard.getSuit () >= 3) == (proposedCard.getSuit () >= 3))
	{
	    return false;
	}

	//test for value
	if ((topCard.getFaceValue ()) != (proposedCard.getFaceValue () + 1))
	{
	    return false;
	}

	return true;

    }


    public void add (Card cd)  //overrides add
    {
	if (okToAdd (cd))
	{
	    super.add (cd, 0);
	}
    }


    public void add (Card cd, boolean passCheck)  // overloads add to get aroung the 'okToAdd' check. This is useful when dealing, and when adding cards back when moves are rejected
    {
	super.add (cd, 0);
    }


    public void multipleAdd (Deck d1)  //assumes legality
    {
	for (int i = d1.getNumberOfCards () - 1 ; i > -1 ; i--)
	{
	    super.add (d1.peek (i), 0);
	}
    }



    public boolean isString (int pos)  //Helper to delete. This takes a deck position and checks if there is a string (perfect sequence of cards) below it.
    {
	int left = pos;

	//check down the list of cards, returning false if an out of string card is found. If end is reached, return true.
	while (left > 0)
	{
	    //okToAdd cannot be used because it tests off pos 0.

	    //Face value check
	    if ((peek (pos).getFaceValue ()) != (peek (pos - 1).getFaceValue () + 1))
	    {

		return false;
	    }
	    //Color check: red first, then black
	    if ((peek (pos).getSuit () <= 2) && (peek (pos - 1).getSuit () <= 2))
	    {

		return false;
	    }
	    //black
	    if ((peek (pos).getSuit () >= 3) && (peek (pos - 1).getSuit () >= 3))
	    {
		return false;
	    }
	    left--;

	}
	return true;
    }


    public TableauDeck multipleDelete (int dpos)  //this cannot override delete in deck because this return a deck, (multiple cards).
    {
	TableauDeck d1 = new TableauDeck ();
	if (getNumberOfCards () == 0)
	{
	    return null;
	}
	else
	{
	    if (dpos == 0) // position is 0-counted
	    {
		d1.add (super.delete (0), 0); //this deletes the top card, and adds it to the return deck
	    }
	    else
	    {
		for (int i = 0 ; i < dpos + 1 ; i++)
		{
		    //add cards to return deck
		    d1.add (super.delete (dpos - i), 0);
		}
	    }
	}
	//returns a deck of cards deleted (many cards can be moved at once)
	return d1;
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)  //draws the deck with fan down, so all cards are visible, using images
    {
	//draw the bottom card first
	//offset the ypos by 30 px
	if (getColor () == Color.white) //if told to erase
	{
	    g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	}
	else
	{

	    if (getNumberOfCards () == 0) //if there are not cards
	    {
		g.setColor (getColor ());
		g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	    }
	    else
	    {
		for (int i = getNumberOfCards () ; i > 0 ; i--)
		{
		    Card drawCard = peek (i - 1);
		    drawCard.setCenterY (getCenterY () + 30 * (getNumberOfCards () - i));
		    drawCard.draw (g, images, imgObs);
		}

	    }
	}

    }
}
