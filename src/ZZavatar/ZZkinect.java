import SimpleOpenNI.*;
import processing.core.PApplet;
import processing.core.PImage;

/********************************************
 * 
 * Classe de gestion de la kinect
 *
 ********************************************/
public class ZZkinect {
	protected SimpleOpenNI kinect;
	protected int height;	//hauteur de la capture
	protected int width;	//largeur de la capture
	
	protected PImage rgbImage;		//capture video normale
	protected PImage depthImage;	//capture de profondeur
	
	public ZZkinect(PApplet parent) {
		try {
			kinect = new SimpleOpenNI(parent);
			
			if(!kinect.isInit()){ //kinect reconnue ou pas
				PApplet.println("Kinect non reconnue ou non pr�sente");
				kinect = null;
			} else { //kinect reconnue
				kinect.enableDepth();	//chargement de la profondeur
				kinect.enableRGB();		//chargement de l'image couleur
				
				height = kinect.depthHeight();	//hauteur de la capture
				width = kinect.depthWidth();	//largeur de la capture
				
				if ((width != 640) || (height != 480)) { //reconnaissance des parametres
					PApplet.println("Erreur sur les dimension de capture kinect");
					kinect = null;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void refresh(){
		kinect.update();	//mise a jour de la kinect
		
		rgbImage = kinect.rgbImage();		//mise a jour de l'image couleur
		depthImage = kinect.depthImage();	//mise a jour de la profondeur
			
	}


        public boolean available(){
            return kinect != null;
        }
	
	@Override
	public String toString() {
		String out = "";
		
		if (kinect != null) {
			out += "Kinect ouverte en " + width + " x " + height; 
		} else {
			out += "Kinect non initialisee";
		}
		
		return out;
	}
	
} //class
