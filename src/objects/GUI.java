package objects;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import utils.GdGMain;
import g4p_controls.*;

public class GUI{
PApplet c = GdGMain.canvas;
PShape s;
String[] files;

public GImageButton play, cog;


public GUI(){
	s = c.createShape(PConstants.RECT, c.width*.10f/2, c.height*.10f/2, c.width*.9f, c.height*.9f);
	s.setFill(255);
}

public void set(){
	files = new String[]{
			"Ressources/Buttons/paly_normal.png", "Ressources/Buttons/play_hover.png", "Ressources/Buttons/settings_pressed.png"
	};

	play = new GImageButton(c, 130,180,files);
	play.tag = "play";

	files = new String[]{
			"Ressources/Buttons/settings_normal.png", "Ressources/Buttons/settings_hover.png", "Ressources/Buttons/settings_clicked.png"
	};

	cog = new GImageButton(c, 750, 180, files);
	cog.tag = "cog";

}

public void display(){

	c.strokeWeight(2);
	if(GdGMain.g != 255){
		c.stroke(255);
		c.fill(255, 255, 255, 120);
	} else{
		c.stroke(0);
		c.fill(0, 0, 0, 90);
	}
	c.rect(c.width*.10f/2, c.height*.10f/2, c.width*.9f, c.height*.9f);

	displayButtons();
}

void displayButtons(){
	if(GdGMain.inGui && !GdGMain.inGui2){
		play.setVisible(true);
		cog.setVisible(true);
	}
}
}
