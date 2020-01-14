/*
    By: Dylan Snelgrove
    Date: January 2018

    Game:
     - holds all the decks required for the game
     - also has score, center, and width/height data
     - stores mouseData
     - stores a moveDeckDisplayToggle, a flag for whether or not the "moveDeck", a deck used for cards in transit while being dragged.
     - has a contructor which removes the clock card foundations, shuffles the deck, and deals.
     - has methods for mouseMovements
     - contains the execution of the rules
     - draw draws all the decks.

*/

import java.awt.*;
import java.util.*;
import java.awt.image.*;

public class Game
{
    //encapsulated data

    //game data
    private int score;
    private int centerX;
    private int centerY;
    private int width;
    private int height;
    private boolean mddt; //flag / toggle for whether or not moveDeck will be displayed. (Move Deck Display Toggle) was too long.
    private int oldDeckNum; //stores the index of TableauDeck that the moveDeck removed cards from so that upon an illegal release, cards can be returned.

    //decks
    private FoundationDeck[] clock = new FoundationDeck [12];
    private TableauDeck[] tableau = new TableauDeck [8];
    private TableauDeck moveDeck;


    //mouse data (used by the class to handle mouse action)
    private int lastX; //position of mouse at lastEvent
    private int lastY;
    private int clickX;
    private int clickY;

    public Game ()
    {

	//game data (defealt size is 1024 centered at (512, 288) [16:9] ratio.)
	score = 0;
	centerX = 512;
	centerY = 288;
	width = 1024;
	height = 576;
	mddt = false;
	oldDeckNum = -1;

	//defeault mouse data
	setLastMouse (0, 0);
	clickX = 0;
	clickY = 0;

	//decks
	moveDeck = new TableauDeck ();

	Deck dealDeck = new Deck ("standard");
	//deletes the starting clock cards from the deal deck.
	int[] indicies = {37, 10, 46, 20, 31, 6, 39, 15, 25, 2, 32, 10}; //progressive indecies of the cards to be removed.
	for (int i = 0 ; i < 12 ; i++)
	{
	    dealDeck.delete (indicies [i]);
	}
	dealDeck.shuffle ();

	//deal shuffled, filtered deck to the tableau decks
	for (int i = 0 ; i < 8 ; i++)
	{
	    tableau [i] = new TableauDeck ();
	    tableau [i].setCenter (width / 32 + width / 15 * i, height * 5 / 17);
	    for (int j = 0 ; j < 5 ; j++)
	    {
		tableau [i].add (dealDeck.delete (0), true); //uses the unchecked overloaded version
	    }

	}

	//makes the clock
	for (int i = 0 ; i < 12 ; i++)
	{
	    clock [i] = new FoundationDeck ((2 + i), width - height / 3 - 56, height / 2, height / 3);
	}
    }


    public int getScore ()
    {
	return score;
    }


    public void restart ()
    {
	//complier does not allow the call of the constructor

	//game data (defealt size is 1024 centered at (512, 288) [16:9] ratio.)
	score = 0;
	centerX = 512;
	centerY = 288;
	width = 1024;
	height = 576;
	mddt = false;
	oldDeckNum = -1;

	//defeault mouse data
	setLastMouse (0, 0);

	//decks
	moveDeck = new TableauDeck ();

	Deck dealDeck = new Deck ("standard");
	//deletes the starting clock cards from the deal deck.
	int[] indicies = {37, 10, 46, 20, 31, 6, 39, 15, 25, 2, 32, 10}; //progressive indecies of the cards to be removed.
	for (int i = 0 ; i < 12 ; i++)
	{
	    dealDeck.delete (indicies [i]);
	}
	dealDeck.shuffle ();

	//deal shuffled, filtered deck to the tableau decks
	for (int i = 0 ; i < 8 ; i++)
	{
	    tableau [i] = new TableauDeck ();
	    tableau [i].setCenter (width / 32 + width / 15 * i, height * 5 / 17);
	    for (int j = 0 ; j < 5 ; j++)
	    {
		tableau [i].add (dealDeck.delete (0), true); //uses the unchecked overloaded version
	    }

	}

	//makes the clock
	for (int i = 0 ; i < 12 ; i++)
	{
	    clock [i] = new FoundationDeck ((2 + i), width - height / 3 - 56, height / 2, height / 3);
	}
    }


    public void setLastMouse (int x, int y)
    {
	lastX = x;
	lastY = y;
    }


    public int getLastMouseX ()
    {
	return lastX;
    }


    public int getLastMosueY ()
    {
	return lastY;
    }


    public void setOldDeckNum (int n)
    {
	n = oldDeckNum;
    }


    public int getOldDeckNum ()
    {
	return oldDeckNum;
    }


    //called when the mousePressed event is called.
    public void clickHandler (int mx, int my)  //records mouse position into "lastX, lastY"
    {
	clickX = mx;
	clickY = my;
	lastX = mx;
	lastY = my;

    }


    //called when the mouseDragged event is called.
    public void dragHandler (int mx, int my)
    {
	if (!mddt)
	{
	    //check which deck/card is being clicked on

	    //use deck/card referencing
	    int deckNum = -1;
	    if (clickX < (tableau [0].getCenterX () + tableau [0].getWidth () / 2) && clickX > (tableau [0].getCenterX () - tableau [0].getWidth () / 2))
	    {
		deckNum = 0;
	    }
	    else if (clickX < (tableau [1].getCenterX () + tableau [1].getWidth () / 2) && clickX > (tableau [1].getCenterX () - tableau [1].getWidth () / 2))
	    {
		deckNum = 1;
	    }
	    else if (clickX < (tableau [2].getCenterX () + tableau [2].getWidth () / 2) && clickX > (tableau [2].getCenterX () - tableau [2].getWidth () / 2))
	    {
		deckNum = 2;
	    }
	    else if (clickX < (tableau [3].getCenterX () + tableau [3].getWidth () / 2) && clickX > (tableau [3].getCenterX () - tableau [3].getWidth () / 2))
	    {
		deckNum = 3;
	    }
	    else if (clickX < (tableau [4].getCenterX () + tableau [4].getWidth () / 2) && clickX > (tableau [4].getCenterX () - tableau [4].getWidth () / 2))
	    {
		deckNum = 4;
	    }
	    else if (clickX < (tableau [5].getCenterX () + tableau [5].getWidth () / 2) && clickX > (tableau [5].getCenterX () - tableau [5].getWidth () / 2))
	    {
		deckNum = 5;
	    }
	    else if (clickX < (tableau [6].getCenterX () + tableau [6].getWidth () / 2) && clickX > (tableau [6].getCenterX () - tableau [6].getWidth () / 2))
	    {
		deckNum = 6;
	    }
	    else if (clickX < (tableau [7].getCenterX () + tableau [7].getWidth () / 2) && clickX > (tableau [7].getCenterX () - tableau [7].getWidth () / 2))
	    {
		deckNum = 7;
	    }
	    int cardNum = -1; //if assinged -1, click is not on a card.
	    if (deckNum != -1)
	    {
		for (int i = tableau [deckNum].getNumberOfCards () - 1 ; i > -1 ; i--)
		{
		    int cCenterY = tableau [deckNum].getCenterY ();
		    int cHeight = tableau [deckNum].getHeight () / 2;
		    int cMult = tableau [deckNum].getNumberOfCards () - 1 - i;

		    if (i != 0)
		    {
			//not the last/top card (looks for the small bit of the card)
			if ((clickY > cCenterY - cHeight + 30 * cMult) && (clickY < cCenterY - cHeight + 30 + 30 * cMult))
			{
			    cardNum = i;
			}
		    }
		    else
		    {
			//the last / top card (looks for the entire card)
			if ((clickY > cCenterY - cHeight + 30 * cMult) && (clickY < cCenterY + cHeight + 30 * cMult))
			{
			    cardNum = i;
			}
		    }
		}
	    }

	    //transfer cards to moveDeck if legal
	    if (cardNum != -1 && deckNum != -1)
	    {
		boolean isLegal = tableau [deckNum].isString (cardNum);
		if (isLegal)
		{
		    mddt = true;
		    moveDeck.multipleAdd (tableau [deckNum].multipleDelete (cardNum));
		    moveDeck.setCenter (tableau [deckNum].getCenterX (), tableau [deckNum].getCenterY () + 30 * tableau [deckNum].getNumberOfCards ());
		    oldDeckNum = deckNum;
		}
	    }

	}
	if (mddt)
	{
	    moveDeck.setCenter (moveDeck.getCenterX () + (mx - lastX), moveDeck.getCenterY () + (my - lastY));
	    setLastMouse (mx, my);
	}

    }


    public void releaseHandler (int mx, int my)
    {
	int nx = moveDeck.getCenterX ();
	int ny = moveDeck.getCenterY ();

	if (mddt)
	{
	    mddt = false;
	    //release:
	    //clock OR not Clock

	    if (nx < 549) //not clock
	    {


		//do a deck check on the nx.
		int deckNum = -1;
		if (nx < (tableau [0].getCenterX () + tableau [0].getWidth () / 2) && nx > (tableau [0].getCenterX () - tableau [0].getWidth () / 2))
		{
		    deckNum = 0;
		}
		else if (nx < (tableau [1].getCenterX () + tableau [1].getWidth () / 2) && nx > (tableau [1].getCenterX () - tableau [1].getWidth () / 2))
		{
		    deckNum = 1;
		}
		else if (nx < (tableau [2].getCenterX () + tableau [2].getWidth () / 2) && nx > (tableau [2].getCenterX () - tableau [2].getWidth () / 2))
		{
		    deckNum = 2;
		}
		else if (nx < (tableau [3].getCenterX () + tableau [3].getWidth () / 2) && nx > (tableau [3].getCenterX () - tableau [3].getWidth () / 2))
		{
		    deckNum = 3;
		}
		else if (nx < (tableau [4].getCenterX () + tableau [4].getWidth () / 2) && nx > (tableau [4].getCenterX () - tableau [4].getWidth () / 2))
		{
		    deckNum = 4;
		}
		else if (nx < (tableau [5].getCenterX () + tableau [5].getWidth () / 2) && nx > (tableau [5].getCenterX () - tableau [5].getWidth () / 2))
		{
		    deckNum = 5;
		}
		else if (nx < (tableau [6].getCenterX () + tableau [6].getWidth () / 2) && nx > (tableau [6].getCenterX () - tableau [6].getWidth () / 2))
		{
		    deckNum = 6;
		}
		else if (nx < (tableau [7].getCenterX () + tableau [7].getWidth () / 2) && nx > (tableau [7].getCenterX () - tableau [7].getWidth () / 2))
		{
		    deckNum = 7;
		}


		if (deckNum != -1)
		{
		    //Case 2: Legal Release
		    //cards can and are added to the new deck
		    if (tableau [deckNum].okToAdd (moveDeck.peek (moveDeck.getNumberOfCards () - 1)))
		    {
			tableau [deckNum].multipleAdd (moveDeck.multipleDelete (moveDeck.getNumberOfCards () - 1));
		    }
		    else
		    {
			//Cases: 1. Accidental Release / Illegal Release
			//cards cannot be added to destination deck (if exists) and cards are returned to the old deck. "Must add data to remember old deck"
			tableau [getOldDeckNum ()].multipleAdd (moveDeck.multipleDelete (moveDeck.getNumberOfCards () - 1));
		    }
		}
		else
		{

		    //Cases: 1. Accidental Release / Illegal Release
		    //cards cannot be added to destination deck (if exists) and cards are returned to the old deck. "Must add data to remember old deck"
		    tableau [getOldDeckNum ()].multipleAdd (moveDeck.multipleDelete (moveDeck.getNumberOfCards () - 1));
		}
	    }
	    else
	    { //clock
		boolean flg = false;
		if (moveDeck.getNumberOfCards () <= 1)
		{
		    for (int i = 0 ; i < 12 ; i++)
		    {
			if (clock [i].isInside (nx, ny) && clock [i].okToAdd (moveDeck.peek (0)))
			{
			    clock [i].add (moveDeck.delete (moveDeck.getNumberOfCards () - 1));
			    flg = true;
			    score += 1;
			}
		    }
		}
		if (!flg)
		{
		    tableau [getOldDeckNum ()].multipleAdd (moveDeck.multipleDelete (moveDeck.getNumberOfCards () - 1));
		}
	    }

	}
	//of course their is the case there are no cards being dragged. - do nothing.
    }


    public void draw (Graphics g, Image[] [] images, ImageObserver imgObs)
    {
	for (int i = 0 ; i < 12 ; i++)
	{
	    clock [i].draw (g, images, imgObs);
	}


	for (int i = 0 ; i < 8 ; i++)
	{
	    tableau [i].draw (g, images, imgObs);
	}

	if (mddt)
	{
	    moveDeck.draw (g, images, imgObs);
	}

    }
}


