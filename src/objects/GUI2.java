package objects;

import g4p_controls.GImageButton;
import utils.GdGMain;

public class GUI2 extends GUI{
	public GImageButton g, p, r, y, back, info;


	public GUI2(){
		super();
	}

	@Override
	public void set(){
			files = new String[]{
					"Ressources/Buttons/p_normal.png", "Ressources/Buttons/p_hover.png", "Ressources/Buttons/p_clicked.png"
			};

			p = new GImageButton(c,300,95,files);
			p.tag = "p";

			files = new String[]{
				"Ressources/Buttons/r_normal.png","Ressources/Buttons/r_hover.png","Ressources/Buttons/r_clicked.png"
			};

			r = new GImageButton(c,700,95,files);
			r.tag = "r";

			files = new String[]{
					"Ressources/Buttons/y_normal.png", "Ressources/Buttons/y_hover.png", "Ressources/Buttons/y_clicked.png"
			};

			y = new GImageButton(c, 700, 400,files);
			y.tag = "y";

			files = new String[]{
					"Ressources/Buttons/g_normal.png","Ressources/Buttons/g_hover.png","Ressources/Buttons/g_clicked.png"
			};

			g = new GImageButton(c, 300, 400, files);
			g.tag = "g";

		files = new String[]{
				"Ressources/Buttons/back.png","Ressources/Buttons/back.png","Ressources/Buttons/back.png"
		};

		back  = new GImageButton(c, c.width*.10f/2+10f, c.height*.10f/2+10f, files);
		back.tag = "back";
	}

	@Override
	public void display(){
		super.display();
		displayButtons();
	}

	@Override
	void displayButtons(){
		if(!GdGMain.inGui && GdGMain.inGui2 && !GdGMain.inSong){
			r.setVisible(true);
			g.setVisible(true);
			p.setVisible(true);
			y.setVisible(true);
			back.setVisible(true);
		}else{
			r.setVisible(false);
			g.setVisible(false);
			p.setVisible(false);
			y.setVisible(false);
			back.setVisible(false);
		}
	}
}
