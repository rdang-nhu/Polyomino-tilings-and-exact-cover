import java.util.*;
import java.io.FileReader;
import java.awt.Color;
import java.io.BufferedReader;

public class PolyominoList extends LinkedList<Polyomino>{ //ne gère pas les exceptions en cas de non-lecture du fichier
	PolyominoList(String nom){
		super();
		try
		{
			FileReader fichier = new FileReader(nom);
			BufferedReader br = new BufferedReader(fichier);
			
			String line = br.readLine();
			while(line != null){
				Polyomino p = new Polyomino(line);
				this.addLast(p);
				line = br.readLine();
			}
			br.close();
		} catch(Exception e){
		}
	}
	
	
	public void draw(){
		Image2d img = new Image2d(1000,500); //régler le paramétrage auto
		
		for(int i = 0; i < this.size(); i++){
			Polyomino a = this.get(i);
			for(int j = 0; j < a.liste.size(); j++){
				int[] carré = a.liste.get(j);//on récupère tous les polygones et on les dessine de la même couleur
				int x = carré[0];
				int y = carré[1];
				int x1 = 10*x;  
				int y1 = 200-10*y;
				int[] xc = {x1,x1,x1+10,x1+10};
				int[] yc = {y1,y1+10,y1+10,y1};
				img.addPolygon(xc, yc, Color.red);
			}
		}
		Image2dComponent imgC = new Image2dComponent(img); //vérifier si affecter est nécéssaire
		Image2dViewer imgV = new Image2dViewer(img);
	}
	
	@Override
	
	public String toString(){
		String s = "";
		for(int i = 0; i < this.size(); i++){
			Polyomino p = this.get(i);
			s += p.toString()+"\n";
		}
		return s;		
	}
	
	public static void main(String[] args){
		PolyominoList test = new PolyominoList("src/polyominoesINF421.txt");
		for(int i = 0; i < test.size(); i++){
			Polyomino p = test.get(i);
			p.translater(5*i, 0);
		}
		test.draw();
		
	}
}
