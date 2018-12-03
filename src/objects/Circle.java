package objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import utils.GdGMain;


public class Circle{
    PApplet c = GdGMain.canvas;
    float xPos, yPos, width, height;
    static int r,g,b;
    int alpha;
    public Color color;
    PShape shape;

    public Circle(){

    }

    public Circle(float xPos, float yPos, float width, float height, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        setColor(color);
        this.alpha = 255;
        createObject();
    }

    public Circle (float xPos, float yPos, float size, Color color){

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = size;
        this.height = size;
        setColor(color);
        this.alpha = 255;
        createObject();
    }

    public enum Color{
        WHITE, BLACK
    }

    public static void setColor(Color c){
        switch(c){
            case WHITE:
                r = 255;
                b = 255;
                g = 255;
                break;
            case BLACK:
                r = 0;
                b = 0;
                g = 0;
                break;
        }
    }



    void createObject(){
        shape = c.createShape(PConstants.ELLIPSE, xPos, yPos,width,height);
        shape.setFill(255);
    }

    public void display(){
        c.noStroke();
        c.fill(r,g,b,alpha);
        c.ellipse(xPos, yPos,width,height);
    }

    public void vanish(){
        c.noFill();
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
    this.xPos = xPos;
}

    public float getyPos() {
    return yPos;
}

    public void setyPos(float yPos) {
    this.yPos = yPos;
}

    public float getWidth() {
    return width;
}

    public void setWidth(float width) {
    this.width = width;
}

    public float getHeight() {
    return height;
}

    public void setHeight(float height) {
    this.height = height;
}

    public int getR(){
        return r;
    }

    public void setR(int r){
        this.r = r;
    }

    public int getG(){
        return g;
    }

    public void setG(int g){
        this.g = g;
    }

    public int getB(){
        return b;
    }

    public void setB(int b){
        this.b = b;
    }

    public int getAlpha(){
        return alpha;
    }

    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    public PShape getShape(){
        return shape;
    }

    public Color getColor(){
        return color;
    }

    public void setPos(float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }


public void setSize(float size){
        this.width = size;
        this.height = size;
       }

    }
