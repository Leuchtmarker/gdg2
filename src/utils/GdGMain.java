package utils;

import ddf.minim.analysis.FFT;
import objects.Circle;
import objects.GUI;
import objects.GUI2;
import objects.Rect;
import processing.core.*;
import ddf.minim.*;
import de.looksgood.ani.*;
import g4p_controls.*;

import java.io.*;
import java.util.ArrayList;

import static objects.Circle.Color.*;

public class GdGMain extends PApplet{
private Minim minim;
private FFT fft;
private AudioPlayer player;

private final String audio = "Ressources/16_i+v_Karin Park_Look What Youve Done(1).mp3";

//general settings------------------------------------------------------------------------------------------------------
private final  int WIDTH = 1240;
private final  int HEIGHT = 720;
public static  int r = 77, g = 0, b = 77; //background color
String dColor = "purple";
private boolean isDebbuging = false;
//----------------------------------------------------------------------------------------------------------------------

//songPosition vis------------------------------------------------------------------------------------------------------
private Circle songPos, songPos2;
private boolean overSongPos;
//----------------------------------------------------------------------------------------------------------------------

//instruments vis-------------------------------------------------------------------------------------------------------
private Circle drum1, drum2, drum3, ultima, voc, hihat, gi1;

private float sCsize = 200;
private final float FIXSIZE_SCS = 200;

private float bCsize = 240;
private final float FIXSIZE_BCS = 240;

private Rect g1, g2, g3, g4, g5, g6;
private float UltimaSize = 1000;
private final float FIXSIZE_U = 1000;

private ArrayList<Circle> o = new ArrayList<>();
//----------------------------------------------------------------------------------------------------------------------

//animationvars---------------------------------------------------------------------------------------------------------
	//testing...........................................................................................................
	private int t_start, t_end;
	//..................................................................................................................

	//drums.............................................................................................................
	private int drum1S, drum1E;
	private int drum2S, drum2E;
	private int drum3S, drum3E;
	//..................................................................................................................


	//guitars (circles).................................................................................................
	private int gi1S, gi1E;
	//..................................................................................................................


	//guitars (rects)...................................................................................................
	private int g1S = 26000, g1E = 26450;
	private int g2S = 26500, g2E = 26950;
	private int g3S = 27000, g3E = 27450;
	private int g4S = 27500, g4E = 27950;
	private int g5S = 28000, g5E = 28450;
	//..................................................................................................................

	//vocals............................................................................................................
	private int vocS, vocE;
	//..................................................................................................................


	//hithat............................................................................................................
	private int hithatS, hithatE;
	//..................................................................................................................


//Images
PImage esc, space;
//----------------------------------------------------------------------------------------------------------------------

//animation bools-------------------------------------------------------------------------------------------------------
private boolean ani1 = false;
private boolean ani2 = false;
//----------------------------------------------------------------------------------------------------------------------

//gui
private GUI gui;
private GUI2 gui2;
public static boolean inGui = true;
public static boolean inGui2 = false;
public static boolean inSong = false;

//canvas
public static PApplet canvas;


@Override
public void settings(){
	setSize(WIDTH, HEIGHT);
}

@Override
public void setup(){
	canvas = this;
	frameRate(120);

	Ani.init(this);

	//minim-------------------------------------------------------------------------------------------------------------
	minim = new Minim(this);
	player = minim.loadFile(audio);
	fft = new FFT(player.bufferSize(), player.sampleRate());
	//------------------------------------------------------------------------------------------------------------------

	//song pos----------------------------------------------------------------------------------------------------------
	songPos = new Circle(0, 700, 20, WHITE);
	songPos2 = new Circle(0, 700, 15, WHITE);
	overSongPos = false;
	//------------------------------------------------------------------------------------------------------------------

	//instruments-------------------------------------------------------------------------------------------------------
	drum1 = new Circle(WIDTH/4, HEIGHT/2 - 50, sCsize, WHITE);
	drum2 = new Circle(WIDTH/2, HEIGHT/3, sCsize,WHITE);
	drum3 = new Circle(WIDTH*3/4, HEIGHT/2 - 50, sCsize,WHITE);

	voc = new Circle(WIDTH*2/5, HEIGHT/2, bCsize,WHITE);
	voc.vanish();
	hihat = new Circle(WIDTH*3/5, HEIGHT/2, bCsize, WHITE);

	gi1 = new Circle(WIDTH/2, HEIGHT/3*2+50 + 50, sCsize, WHITE);


	ultima = new Circle(WIDTH/2, HEIGHT/2, UltimaSize,WHITE);

	g1 = new Rect(-2481,57.5f,1240,5,WHITE);
	g2 = new Rect(-2481,177.5f,1240,5,WHITE);
	g3 = new Rect(-2481,297.5f,1240,5,WHITE);
	g4 = new Rect(-2481,417.5f,1240,5,WHITE);
	g5 = new Rect(-2481,537.5f,1240,5,WHITE);
	g6 = new Rect(-2481,657.5f,1240,5,WHITE);

	voc.setAlpha(0);
	hihat.setAlpha(0);
	gi1.setAlpha(0);

	//gui
	gui = new GUI();
	gui2 = new GUI2();
	gui.set();
	gui2.set();
	player.play();

	//img
	esc = loadImage("Ressources/keys/esc..png");
	space = loadImage("Ressources/keys/space.png");

	//------------------------------------------------------------------------------------------------------------------

}


@Override
public void draw(){
	if(player.position() > 68000){
		inGui = true;
		inGui2 = false;
		inSong = false;
		gui2.p.setVisible(false);
		gui2.r.setVisible(false);
		gui2.y.setVisible(false);
		gui2.g.setVisible(false);
	}

	o.add(songPos);
	o.add(songPos2);
	o.add(drum1);
	o.add(drum2);
	o.add(drum3);
	o.add(voc);
	o.add(hihat);
	o.add(gi1);
	o.add(ultima);
	o.add(g1);
	o.add(g2);
	o.add(g3);
	o.add(g4);
	o.add(g5);
	o.add(g6);

	background(r, g, b);

	fft.forward(player.mix);

	/*Instruments-----------------------------------------------------------------------------------------------------*/
	if(inSong & !inGui & !inGui2){
		gui2.p.setVisible(false);
		gui2.r.setVisible(false);
		gui2.g.setVisible(false);
		gui2.y.setVisible(false);
		drum1.setAlpha(255);
		drum2.setAlpha(255);
		drum3.setAlpha(255);
		songPos.setAlpha(255);
		g1.setAlpha(255);
		g2.setAlpha(255);
		g3.setAlpha(255);
		g4.setAlpha(255);
		g5.setAlpha(255);
		g6.setAlpha(255);

		if(!ani1){
			voc.setAlpha(0);
			hihat.setAlpha(0);
			gi1.setAlpha(0);
		}
		if(ultima.getB() == 255){
			ultima.setAlpha(122);
		} else{
			ultima.setAlpha(50);
		}
	ultima.display();

	drum1.display();
	drum2.display();
	drum3.display();

	voc.display();
	hihat.display();
	gi1.display();

	g1.display();
	g2.display();
	g3.display();
	g4.display();
	g5.display();
	g6.display();

	songPos.display();

	if(player.position() <= 7000){
		PFont roboto = createFont("Ressources/fonts/Roboto-Regular.ttf",12);
		textFont(roboto);
		image(esc,20,20);
		text("back", 80,50);
		image(space,20,80);
		text("play/pause", 80,110);
	}

	//------------------------------------------------------------------------------------------------------------------

	//after ani1--------------------------------------------------------------------------------------------------------
	if(ani1){
		voc.setAlpha(255);
		//hihat.setAlpha(255);
		gi1.setAlpha(255);
	}

	//------------------------------------------------------------------------------------------------------------------

	//readAnis----------------------------------------------------------------------------------------------------------
	if(player.isPlaying()){
		//test----------------------------------------------------------------------------------------------------------
		try{
			BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/ani_t.ti"));
			String line;
			while((line = in.readLine()) != null){
				String[] times = line.split(",");
				t_start = Integer.parseInt(times[0]);
				t_end = Integer.parseInt(times[1]);
				if(player.position() < t_end && player.position() > t_start){
					//System.out.println("ok. " + player.position());
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		//--------------------------------------------------------------------------------------------------------------

		//vocals--------------------------------------------------------------------------------------------------------
		try{
			BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/Vocals.ti"));
			String line;
			while(((line = in.readLine()) != null)){
				String[] times = line.split(",");
				vocS = Integer.parseInt(times[0]);
				vocE = Integer.parseInt(times[1]);
				if(player.position() <= 61945){
					if(player.position() < vocE && player.position() > vocS){
						float volume = avgFreq(2800, 9000)/5f;
						float size = lerp(voc.getHeight(), volume*FIXSIZE_BCS, 0.025f);
						voc.setSize(size);
					}
					Ani.to(voc, 1f, "height", FIXSIZE_BCS);
					Ani.to(voc, 1f, "width", FIXSIZE_BCS);
				} else{
					Ani.to(voc, 3.5f, "height", 0);
					Ani.to(voc, 3.5f, "width", 0);
					voc.vanish();
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	//------------------------------------------------------------------------------------------------------------------

	//hithat------------------------------------------------------------------------------------------------------------

	try{
		BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/hithat.ti"));
		String line;
		while((line = in.readLine()) != null){
			String[] times = line.split(",");
			hithatS = Integer.parseInt(times[0]);
			hithatE = Integer.parseInt(times[1]);
			if(player.position() <= 61945){
				if(player.position() < hithatE && player.position() > hithatS){
					float volume = player.mix.get(1);
					float size = lerp(hihat.getHeight(), volume*FIXSIZE_BCS, 0.025f);
					hihat.setSize(size);
				}
				Ani.to(hihat, 0.5f, "height", FIXSIZE_BCS);
				Ani.to(hihat, 0.5f, "width", FIXSIZE_BCS);
			}
		}
	} catch(IOException e){
		e.printStackTrace();
	}

	//------------------------------------------------------------------------------------------------------------------

	//drum1
	try{
		BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/drum1.csv"));
		String line;
		while((line = in.readLine()) != null){
			String[] times = line.split(",");
			if(Integer.parseInt(times[0]) < Integer.parseInt(times[1])){
				drum1S = Integer.parseInt(times[0]);
				drum1E = Integer.parseInt(times[1]);
			} else{
				drum1S = Integer.parseInt(times[1]);
				drum1E = Integer.parseInt(times[0]);
			}
			if(player.position() <= 58000){
				if(player.position() < drum1E && player.position() > drum1S){
					float volume = avgFreq(0, 2800)/15f;
					float size = lerp(drum1.getHeight(), volume*FIXSIZE_SCS, 0.055f);
					drum1.setSize(size);
				}
				Ani.to(drum1, 0.5f, "height", FIXSIZE_SCS);
				Ani.to(drum1, 0.5f, "width", FIXSIZE_SCS);
			} else{
				Ani.to(drum1, 3.5f, "height", 0);
				Ani.to(drum1, 3.5f, "width", 0);
			}
		}
	} catch(IOException e){
		e.printStackTrace();
	}

	//drum2
	try{
		BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/drum2.csv"));
		String line;
		while((line = in.readLine()) != null){
			String[] times = line.split(",");
			if(Integer.parseInt(times[0]) < Integer.parseInt(times[1])){
				drum2S = Integer.parseInt(times[0]);
				drum2E = Integer.parseInt(times[1]);
			} else{
				drum2S = Integer.parseInt(times[1]);
				drum2E = Integer.parseInt(times[0]);
			}
			if(player.position() <= 58000){
				if(player.position() < drum2E && player.position() > drum2S){
					float volume = avgFreq(200, 1400)/22.5f;
					float size = lerp(drum2.getHeight(), volume*FIXSIZE_SCS, 0.025f);
					drum2.setSize(size);
				}
				Ani.to(drum2, 0.5f, "height", FIXSIZE_SCS);
				Ani.to(drum2, 0.5f, "width", FIXSIZE_SCS);
			} else{
				Ani.to(drum2, 3.5f, "height", 0);
				Ani.to(drum2, 3.5f, "width", 0);
			}
		}
	} catch(IOException e){
		e.printStackTrace();
	}

	//drum3
	try{
		BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/drum3.csv"));
		String line;
		while((line = in.readLine()) != null){
			String[] times = line.split(";");
			if(Integer.parseInt(times[0]) < Integer.parseInt(times[1])){
				drum3S = Integer.parseInt(times[0]);
				drum3E = Integer.parseInt(times[1]);
			} else{
				drum3S = Integer.parseInt(times[1]);
				drum3E = Integer.parseInt(times[0]);
			}
			if(player.position() <= 58000){
				if(player.position() < drum3E && player.position() > drum3S){
					float volume = avgFreq(0, 2800)/1.5f;
					float size = lerp(drum1.getHeight(), volume*FIXSIZE_SCS, 0.025f);
					drum3.setSize(size);
				}
				Ani.to(drum3, 0.5f, "height", FIXSIZE_SCS);
				Ani.to(drum3, 0.5f, "width", FIXSIZE_SCS);
			} else{
				Ani.to(drum3, 3.5f, "height", 0);
				Ani.to(drum3, 3.5f, "width", 0);
			}
		}
	} catch(IOException e){
		e.printStackTrace();
	}

	//gi1
	try{
		BufferedReader in = new BufferedReader(new FileReader("Ressources/Animations/guitar.csv"));
		String line;
		while((line = in.readLine()) != null){
			String[] times = line.split(",");
			if(Integer.parseInt(times[0]) < Integer.parseInt(times[1])){
				gi1S = Integer.parseInt(times[0]);
				gi1E = Integer.parseInt(times[1]);
			} else{
				gi1S = Integer.parseInt(times[1]);
				gi1E = Integer.parseInt(times[0]);
			}
			if(player.position() <= 63000){
				if(player.position() < gi1E && player.position() > gi1S){
					float volume = avgFreq(0, 2800)/1.5f;
					float size = lerp(gi1.getHeight(), volume*FIXSIZE_SCS, 0.00125f);
					gi1.setSize(size);
				}
				Ani.to(gi1, 0.5f, "height", FIXSIZE_SCS);
				Ani.to(gi1, 0.5f, "width", FIXSIZE_SCS);
			} else{
				Ani.to(gi1, 3.5f, "height", 0);
				Ani.to(gi1, 3.5f, "width", 0);
			}
		}
	} catch(IOException e){
		e.printStackTrace();
	}

	//ani animations----------------------------------------------------------------------------------------------------

	if(player.position() < 65200){
		if(player.position() > 6000 && player.position() < 7200 && !ani1){ //ani1
			Ani.to(drum1, 10, "yPos", drum2.getyPos() - 100);
			Ani.to(drum2, 10, "yPos", drum2.getyPos() - 100);
			Ani.to(drum3, 10, "yPos", drum2.getyPos() - 100);
			Ani.to(voc, 3, "alpha", 255);
			Ani.to(hihat, 2.7f, "alpha", 255);
			Ani.to(gi1, 2.7f, "alpha", 255);
			ani1 = true;
		}
	} else{
		Ani.to(ultima, 2.8f, "height", 0);
		Ani.to(ultima, 2.8f, "width", 0);
	}

	if(player.position() > 33000){
		Ani.to(voc, 1.2f, "xPos", WIDTH/2);
		Ani.to(hihat, 1.2f, "xPos", WIDTH/2);
	}

	if(player.position() > 34500){
		Ani.to(hihat, 0.1f, "alpha", 0);
		hihat.vanish();
	}

	//ani2----------------------------------------------------------------------------------------------------------

	if(player.position() > 25950 && player.position() < 29000){
		ani2 = true;
	} else{
		ani2 = false;
	}

	if(ani2){
		if(player.position() > g1S && player.position() < g1E){
			Ani.to(g1, (g1E - g1S)/1000f, "xPos", 2481);
		}
		if(player.position() > g2S && player.position() < g2E){
			Ani.to(g2, (g2E - g2S)/1000f, "xPos", 2481);
		}
		if(player.position() > g3S && player.position() < g3E){
			Ani.to(g3, (g3E - g3S)/1000f, "xPos", 2481);
		}
		if(player.position() > g4S && player.position() < g4E){
			Ani.to(g4, (g4E - g4S)/1000f, "xPos", 2481);
		}
		if(player.position() > g5S && player.position() < g5E){
			Ani.to(g5, (g5E - g5S)/1000f, "xPos", 2481);
		}

	} else{
		g1.setPos(-2481, 57.5f);
		g2.setPos(-2481, 177.5f);
		g3.setPos(-2481, 297.5f);
		g4.setPos(-2481, 417.5f);
		g5.setPos(-2481, 537.5f);
		g6.setPos(-2481, 657.5f);
	}

	//------------------------------------------------------------------------------------------------------------------
	//set size of ultima
	float volume = player.mix.get(0) + 1.1f;
	UltimaSize = lerp(UltimaSize, volume*FIXSIZE_U, 0.005f);
	ultima.setSize(UltimaSize);
	//------------------------------------------------------------------------------------------------------------------

	/*SongPos---------------------------------------------------------------------------------------------------------*/
	songPos.display();

	overSongPos = mouseY > songPos.getyPos() - songPos.getHeight() && mouseY < songPos.getyPos() + songPos.getHeight();

	float playPos = player.position();
	float playLen = player.length();
	float xPos = (playPos/playLen)*WIDTH;
	if(overSongPos){
		songPos.setxPos(xPos);
		songPos2.setAlpha(122);
		songPos2.display();
		cursor(HAND);
	} else{
		songPos2.vanish();
		songPos.setxPos(xPos);
		cursor(ARROW);
	}
	songPos2.setxPos(mouseX);
	//------------------------------------------------------------------------------------------------------------------


	//in gui mode------------------------------------------------------------------------------------------------------------------
}else if(!inGui && inGui2 && !inSong){
gui2.display();
player.pause();
}else if (inGui && !inGui2 && !inSong){
		gui.display();
		gui2.p.setVisible(false);
		gui2.r.setVisible(false);
		gui2.g.setVisible(false);
		gui2.y.setVisible(false);
		gui2.back.setVisible(false);

		gui.play.setVisible(true);
		gui.cog.setVisible(true);

		drum1.setPos(WIDTH/4,HEIGHT/2-50);
		drum2.setPos(WIDTH/2,HEIGHT/3);
		drum3.setPos(WIDTH*3/4,HEIGHT/2-50);
		ani1 = false;
		ani2 = false;
		player.cue(0);
		player.pause();
		//-------------------------------------------------------------
		gui.display();
	for(Circle c:o){
		c.setAlpha(0);
	}
		if(player.isPlaying()){
			player.pause();
		}
	}
//debbuging information---------------------------------------------------------------------------------------------
	pushMatrix();
	fill(255);
	if(isDebbuging){
		text("x: " + mouseX + " y: " + mouseY + "\n" +
						"Over Bar: " + overSongPos + "\n" +
						"PlayerPos: " + player.position() + "\\" + player.length() + "ms\n" +
						"paused: " + !player.isPlaying() + "\n" +
						"color:" + dColor + "\n" +
						"fps: " + (int)frameRate,
				1100, 30);
	} else{
		text("", 0, 0);
	}
	popMatrix();
}
//draw end--------------------------------------------------------------------------------------------------------------

//mouse released--------------------------------------------------------------------------------------------------------
@Override
public void mouseReleased(){
	if(overSongPos){
		float pos = ((float) mouseX/WIDTH)*player.length();
		player.cue((int) pos);
		player.play();
	} else{
		if(player.isPlaying()){
			player.pause();
		} else{
			player.play();
		}
	}
	if(overSongPos){
		Ani.to(songPos,0.5f,"xPos",mouseX);
	}
}
//----------------------------------------------------------------------------------------------------------------------

//keyPressed------------------------------------------------------------------------------------------------------------
@Override
public void keyPressed(){
	if(keyCode == 114){
		isDebbuging = !isDebbuging;
	} else if(key == BACKSPACE){
		player.cue(0);
		player.play();
	} else if(keyCode == 39){
		player.play(player.position() + 1000);
	} else if(keyCode == 37){
		player.play(player.position() - 1000);
	} else if(key == 'r'){
		r = 160;
		g = 32;
		b = 32;
		Circle.setColor(Circle.Color.WHITE);
		dColor = "red";
	} else if(key == 'p'){
		r = 77;
		g = 0;
		b = 77;
		Circle.setColor(Circle.Color.WHITE);
		dColor = "purple";
	} else if(key == 'y'){
		setYellow();
	}else if(keyCode == 122){
		if (inGui){
			inGui = false;
		}else if (!inGui){
			inGui = true;
		}

		if(inSong){
			inSong = false;
		}

		if(inGui2){
			inGui2 = false;
		}
		player.play();
	}else if(key == ESC){
		key = 0;
		if(inGui && !inGui2 && !inSong){
			System.exit(0);
		}else if(!inGui && (inGui2 || inSong)){
			inGui = true;
			inGui2 = false;
			inSong = false;
			player.pause();
			player.cue(0);
			gui2.p.setVisible(false);
			gui2.r.setVisible(false);
			gui2.y.setVisible(false);
			gui2.g.setVisible(false);
			drum1.setPos(WIDTH/4,HEIGHT/2-50);
			drum2.setPos(WIDTH/2,HEIGHT/3);
			drum3.setPos(WIDTH*3/4,HEIGHT/2-50);
			voc.setPos(WIDTH*2/5,HEIGHT/2);
			hihat.setPos(WIDTH*3/5,HEIGHT/2);
		}
	}else if(key == '#'){
		System.out.println(inGui);
		System.out.println(inGui2);
		System.out.println(inSong);
	}
}
//----------------------------------------------------------------------------------------------------------------------

//keyReleased-----------------------------------------------------------------------------------------------------------
@Override
public void keyReleased(){
	if(key == ' '|| key == ENTER || key == RETURN){
		if(player.isPlaying()){
			player.pause();
		} else{
			player.play();
		}
	}
}
//----------------------------------------------------------------------------------------------------------------------

//avgFreq---------------------------------------------------------------------------------------------------------------
private float avgFreq(float min, float max){
	float out = 0;
	float i;
	for(i = min; i<=max; i++){
		out += fft.getFreq(i);
	}
	return out/i;
}

public void handleButtonEvents(GImageButton button, GEvent event){
	if(button.tag.equals("play") && event.equals(GEvent.CLICKED)){
		GdGMain.inGui = false;
		GdGMain.inGui2 = false;
		inSong = true;
		gui.play.setVisible(false);
		gui.cog.setVisible(false);
		ani1 = false;
		ani2 = false;
		drum1 = null;
		drum2 = null;
		drum3 = null;
		voc = null;
		hihat = null;

		drum1 = new Circle(WIDTH/4, HEIGHT/2 - 50, sCsize, WHITE);
		drum2 = new Circle(WIDTH/2, HEIGHT/3, sCsize,WHITE);
		drum3 = new Circle(WIDTH*3/4, HEIGHT/2 - 50, sCsize,WHITE);
		voc = new Circle(WIDTH*2/5, HEIGHT/2, bCsize,WHITE);
		hihat = new Circle(WIDTH*3/5, HEIGHT/2, bCsize, WHITE);

		if(dColor.equals("yellow")){
			Circle.setColor(BLACK);
		}

	}else if(button.tag.equals("cog") && event.equals(GEvent.CLICKED)){
		GdGMain.inGui = false;
		GdGMain.inGui2 = true;
		gui.play.setVisible(false);
		gui.cog.setVisible(false);
	}else if(button.tag.equals("p") && event.equals(GEvent.CLICKED)){
		r = 77;
		g = 0;
		b = 77;
		Circle.setColor(WHITE);
		dColor = "purple";
	}else if(button.tag.equals("y") && event.equals(GEvent.CLICKED)){
		setYellow();
	}else if(button.tag.equals("r") && event.equals(GEvent.CLICKED)){
		r = 160;
		g = 32;
		b = 32;
		Circle.setColor(WHITE);
		dColor = "red";
	}else if (button.tag.equals("g") && event.equals(GEvent.CLICKED)){
		r = 0;
		g = 143;
		b = 54;
		Circle.setColor(WHITE);
		dColor = "green";
	}else if (button.tag.equals("back") && event.equals(GEvent.CLICKED)){
		inGui = true;
		inGui2 = false;
		inSong = false;
		player.pause();
		player.cue(0);
		gui2.p.setVisible(false);
		gui2.r.setVisible(false);
		gui2.y.setVisible(false);
		gui2.g.setVisible(false);
	}

}

private void setYellow(){
	r = 255;
	g = 255;
	b = 0;
	Circle.setColor(BLACK);
	dColor = "yellow";
}

//----------------------------------------------------------------------------------------------------------------------

public static void main(String[] args){
	PApplet.main(new String[]{GdGMain.class.getName()});
}
}
