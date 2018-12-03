package objects;


import processing.core.PConstants;

public class Rect extends Circle{


	public Rect(float xPos, float yPos, float width, float height,Color color){
		super(xPos,yPos,width,height,color);
	}

	public Rect(float xPos, float yPos, float size,Color color){
		super(xPos,yPos,size,color);
	}

	@Override
	void createObject(){
		shape = c.createShape(PConstants.RECT,xPos,yPos,width,height);
		shape.setFill(255);
	}

	@Override
	public void display(){
		c.fill(r,g,b,alpha);
		c.rect(xPos,yPos,width,height);
	}
}
