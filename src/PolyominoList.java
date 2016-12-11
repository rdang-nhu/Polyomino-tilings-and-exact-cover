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
	
	PolyominoList(){
		super();
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
		new Image2dComponent(img);
		new Image2dViewer(img);
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
	
	public static PolyominoList fixedPolyomino(int area, LinkedList<int[]> c_vois){
		PolyominoList resultat = new PolyominoList();
		if (area == 1){
			Polyomino e = new Polyomino("(0,0)");
			resultat.addFirst(e);
			return resultat;
		}
		
		for(int i = 0; i < c_vois.size(); i++){
			int[] Pcase = c_vois.get(i);
			LinkedList<int[]> c_vois_aux = new LinkedList<int[]>(c_vois);
			int x = Pcase[0];
			int y = Pcase[1];
			int[] voisin1 = {x+1,y};
			int[] voisin2 = {x,y+1};
			int[] voisin3 = {x-1,y};
			int[] voisin4 = {x,y-1};
			c_vois_aux.add(voisin1);
			c_vois_aux.add(voisin2);
			c_vois_aux.add(voisin3);
			c_vois_aux.add(voisin4);
			
			
			for(int j = 0; j < c_vois_aux.size(); j++){
				int[] aux = c_vois_aux.get(j);
				if(aux == Pcase){
					c_vois_aux.remove(i);
				}
			}
			
			PolyominoList resultat_aux = fixedPolyomino(area-1, c_vois_aux);
			for(int k = 0; k < resultat_aux.size(); k++){
				Polyomino p = resultat_aux.get(k);
				p.liste.add(Pcase);
			}
			resultat.addAll(resultat_aux);
		}
		return resultat;
	}
	
	public static void test1(){
		PolyominoList test = new PolyominoList("src/polyominoesINF421.txt");
		for(int i = 0; i < test.size(); i++){
			Polyomino p = test.get(i);
			p.translater(5*i, 0);
		}
		test.draw();
	}
	
	public static void main(String[] args){
		//test1();
		int[] a1 = {0,1};
		int[] a2 = {1,0};
		LinkedList<int[]> b = new LinkedList<int[]>();
		b.add(a1);
		b.add(a2);
		PolyominoList test2 = fixedPolyomino(3,b);
		System.out.print(test2);
		
	}
}
