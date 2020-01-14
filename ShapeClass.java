/*
    By: Dylan Snelgrove
    Date: January, 2018

    Shape class

     - an abstract class that is the parent class  of all shapes: card, deck.
*/

import java.awt.*;

public abstract class ShapeClass
{
    //encapsulated data
    private int iCenterX;
    private int iCenterY;
    private int iWidth;
    private int iHeight;
    private Color cColor;

    //constructors (--none--)

    //communicator methods
    //sets
    public void setCenterX (int ipCenterX)
    {
	iCenterX = ipCenterX;
    }


    public void setCenterY (int ipCenterY)
    {
	iCenterY = ipCenterY;
    }


    public void setCenter (int ipCenterX, int ipCenterY)
    {
	iCenterX = ipCenterX;
	iCenterY = ipCenterY;
    }


    public void setDimensions (int ipWidth, int ipHeight)
    {
	iWidth = ipWidth;
	iHeight = ipHeight;
    }


    public void setWidth (int ipWidth)
    {
	iWidth = ipWidth;
    }


    public void setHeight (int ipHeight)
    {
	iHeight = ipHeight;
    }


    public void setColor (Color cpColor)
    {
	cColor = cpColor;
    }


    //gets
    int getCenterX ()
    {
	return iCenterX;
    }


    int getCenterY ()
    {
	return iCenterY;
    }


    int getWidth ()
    {
	return iWidth;
    }


    int getHeight ()
    {
	return iHeight;
    }


    Color getColor ()
    {
	return cColor;
    }


    //any action methods

    public void delay (int time_ms)
    {
	long lFinalTime = System.currentTimeMillis () + time_ms;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());

    }

}
