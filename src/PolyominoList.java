<<<<<<< HEAD
import java.util.*;
import java.io.FileReader;
import java.awt.Color;
import java.io.BufferedReader;

public class PolyominoList extends ArrayList<Polyomino>{ //ne gère pas les exceptions en cas de non-lecture du fichier
	static Square case0 = new Square(0,0);
	static int[] case1 = {0,0};
	
	PolyominoList(String nom){
		super();
		try
		{
			FileReader fichier = new FileReader(nom);
			BufferedReader br = new BufferedReader(fichier);
			
			String line = br.readLine();
			while(line != null){
				Polyomino p = new Polyomino(line);
				this.add(p);
				line = br.readLine();
			}
			br.close();
		} catch(Exception e){
		}
	}
	
	PolyominoList(){
		super();
	}
	public void draw_aux(int taille, int g, Image2d img){
		for(int i = 0; i < this.size(); i++){
			// Pour chaque Polyomino de la liste
			Random c = new Random();
			Polyomino a = this.get(i);
			a.translater(taille/2+(g*taille*i)%1250/g, 1+taille*(g*taille*i/1250));
			
			
			Color couleur = Color.getHSBColor(c.nextFloat() ,1,1);
					
			
			for(Square j : a.cases){
				//on récupère tous les carrés et on les dessine de cette couleur
				int x = j.getX();
				int y = j.getY();
				int x1 = g * x;  
				int y1 = g * y;
				int[] xc = {x1,x1,x1+g,x1+g};
				int[] yc = {y1,y1+g,y1+g,y1};
				img.addPolygon(xc, yc, couleur);
			}
		}
	}
	
	public void draw(int taille, int g){ 
		// L'argument taille est l'écart entre les polyominos
		// g est le facteur de dilatation
		
		Image2d img = new Image2d(1300,500); 
		
		draw_aux(taille, g, img);
		
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
	
	public static PolyominoList fixedPolyomino(int area){
		if(area == 1){
			PolyominoList resultat = new PolyominoList();
			Polyomino a = new Polyomino();
			Square case1 = new Square(0,0);
			Square case2 = new Square(0,1);
			Square case3 = new Square(1,0);
			a.cases.add(case1);
			a.cVNI.add(case2);
			a.cVNI.add(case3);
			resultat.add(a);
			return resultat;
		}

		PolyominoList resultat = new PolyominoList();
		PolyominoList rec = fixedPolyomino(area-1);
		for(int i = 0; i < rec.size(); i++){
			
			
			Polyomino p = rec.get(i);
			ArrayList<Square> cVI = new ArrayList<Square>();
			ArrayList<Square> cVNI = new ArrayList<Square>();
			// On recopie p.cVNI et p.cI
			for(Square j : p.cVNI){
				Square c = j.copy();
				cVNI.add(c);
			}
			for(Square j : p.cVI){
				Square c = j.copy();
				cVI.add(c);
			}
			
			for(Square j : p.cVNI){
				// On ajoute la case voisine au cases interdites
				Square aux  = j.copy();
				cVI.add(aux);
				cVNI.remove(0);
				
				// On copie p
				Polyomino a = Polyomino.copy(p);
				// on ajoute la case voisine choisie
				Square aux2 = j.copy();
				a.cases.add(aux2);

				// On met à jour les CVNI
				for(Square k : cVNI){
					Square c = k.copy();
					a.cVNI.add(c);
				}
				
				// On met à jour les CI
				for(Square k : cVI){
					Square c = k.copy();
					a.cVI.add(c);
				}
				
				// On ajoute les nouvelles cases voisines, en évitant de prendre une case déjà occupée
				int x = aux.getX();
				int y = aux.getY();
				Square v1 = new Square(x+1,y);
				Square v2 = new Square(x,y+1);
				Square v3 = new Square(x-1,y);
				Square v4 = new Square(x,y-1);
				
				if(! a.contains(v1, case1) && ! a.vContains(v1)){
					a.cVNI.add(v1);
				}
				if(! a.contains(v2, case1) && ! a.vContains(v2)){
					a.cVNI.add(v2);
				}
				if(v3.isStrictlySuperior(case0) && ! a.contains(v3, case1) && ! a.vContains(v3)){
					a.cVNI.add(v3);
				}
				if(v4.isStrictlySuperior(case0) && ! a.contains(v4, case1) && ! a.vContains(v4)){
					a.cVNI.add(v4);
				}
				
				// on vérifie que res ne contient pas déjà le polyomino
				resultat.add(a);
			}
		}
	return resultat;
	
}
	public static PolyominoList freePolyomino(int area){
		PolyominoList resultat = fixedPolyomino(area);
		for(int l = 0; l < resultat.size(); l++){
			Polyomino test = resultat.get(l);
			for(int k = l+1; k < resultat.size();){
				if(test.isEqualFree(resultat.get(k))){
					resultat.remove(k);
				}
				else{
					k = k+1;
				}
			}
		}
		
	return resultat;
	
}
	public static void test1(){
		PolyominoList test = new PolyominoList("src/polyominoesINF421.txt");
		test.draw(5,10);
	}

	
	public static void main(String[] args){
		long debut = System.currentTimeMillis();
		PolyominoList resultat = fixedPolyomino(11);
		System.out.println(resultat.size());
		long fin = System.currentTimeMillis();
		System.out.print((fin-debut)/1000.);
		resultat.draw(7,1);		
	}
=======
import java.util.*;
import java.io.FileReader;
import java.awt.Color;
import java.io.BufferedReader;

public class PolyominoList extends ArrayList<Polyomino>{ //ne gï¿½re pas les exceptions en cas de non-lecture du fichier
	static int[] caseUtile = {0,0};
	
	PolyominoList(String nom){
		super();
		try
		{
			FileReader fichier = new FileReader(nom);
			BufferedReader br = new BufferedReader(fichier);
			
			String line = br.readLine();
			while(line != null){
				Polyomino p = new Polyomino(line);
				this.add(p);
				line = br.readLine();
			}
			br.close();
		} catch(Exception e){
		}
	}
	
	PolyominoList(){
		super();
	}
	public void draw_aux(int taille, int g, Image2d img){
		for(int i = 0; i < this.size(); i++){
			// Pour chaque Polyomino de la liste
			Random c = new Random();
			Polyomino a = this.get(i);
			a.translater(taille+(g*taille*i)%1180/g, taille+taille*(g*taille*i/1180));
			
			
			Color couleur = Color.getHSBColor(c.nextFloat() ,1,1);
					
			
			for(int j = 0; j < a.cases.size(); j++){
				//on rï¿½cupï¿½re tous les carrï¿½s et on les dessine de cette couleur
				int[] carre = a.cases.get(j);
				int x = carre[0];
				int y = carre[1];
				int x1 = g * x;  
				int y1 = g * y;
				int[] xc = {x1,x1,x1+g,x1+g};
				int[] yc = {y1,y1+g,y1+g,y1};
				img.addPolygon(xc, yc, couleur);
			}
		}
	}
	
	public void draw(int taille, int g){ 
		// L'argument taille est l'ï¿½cart entre les polyominos
		// g est le facteur de dilatation
		// dï¿½but est la case ï¿½ partir de laquelle on commence
		
		Image2d img = new Image2d(1300,500); 
		
		draw_aux(taille, g, img);
		
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
	
	public static PolyominoList fixedPolyomino(int area){
		if(area == 1){
			PolyominoList resultat = new PolyominoList();
			Polyomino a = new Polyomino();
			int[] case1 = {0,0};
			int[] case2 = {0,1};
			int[] case3 = {1,0};
			a.cases.add(case1);
			a.casesVoisines.add(case2);
			a.casesVoisines.add(case3);
			resultat.add(a);
			return resultat;
		}

		PolyominoList resultat = new PolyominoList();
		PolyominoList rec = fixedPolyomino(area-1);
		for(int i = 0; i < rec.size(); i++){
			Polyomino p = rec.get(i);
			for(int j = 0; j < p.casesVoisines.size(); j++){
				
				// On copie p
				Polyomino a = Polyomino.copy(p);
				// on ajoute la case voisine choisie
				a.cases.add(p.casesVoisines.get(j));
				
				//On enlï¿½ve la case voisine indexï¿½e par j
				a.casesVoisines.remove(j);
				
				// On ajoute les nouvelles cases voisines, en ï¿½vitant de prendre une case dï¿½jï¿½ occupï¿½e
				int x = p.casesVoisines.get(j)[0];
				int y = p.casesVoisines.get(j)[1];
				int[] v1 = {x+1,y};
				int[] v2 = {x,y+1};
				int[] v3 = {x-1,y};
				int[] v4 = {x,y-1};
				
				if(! a.contains(v1, caseUtile) && ! a.vContains(v1)){
					a.casesVoisines.add(v1);
				}
				if(! a.contains(v2, caseUtile) && ! a.vContains(v2)){
					a.casesVoisines.add(v2);
				}
				if((x>1 || (x == 1 && y > 0)) && ! a.contains(v3, caseUtile) && ! a.vContains(v3)){
					a.casesVoisines.add(v3);
				}
				if((x>0 || (x == 1 && y > 1)) && ! a.contains(v4, caseUtile) && ! a.vContains(v4)){
					a.casesVoisines.add(v4);
				}
				
				// on vï¿½rifie que res ne contient pas dï¿½jï¿½ le polyomino
				boolean verif = true;
				for(int l = 0; l < resultat.size(); l++){
					if(a.isEqualFixed(resultat.get(l))){
						verif = false;
					}
				}
				if(verif == true) resultat.add(a);
			}
		}
	return resultat;
	
}
	public static PolyominoList freePolyomino(int area){
		PolyominoList resultat = fixedPolyomino(area);
		for(int l = 0; l < resultat.size(); l++){
			Polyomino test = resultat.get(l);
			for(int k = l+1; k < resultat.size();){
				if(test.isEqualFree(resultat.get(k))){
					resultat.remove(k);
				}
				else{
					k = k+1;
				}
			}
		}
		
	return resultat;
	
}
	
	public static PolyominoList oneSidedPolyomino(int area){
		PolyominoList resultat = fixedPolyomino(area);
		for(int l = 0; l < resultat.size(); l++){
			Polyomino test = resultat.get(l);
			for(int k = l+1; k < resultat.size();){
				if(test.isEqualOneSided(resultat.get(k))){
					resultat.remove(k);
				}
				else{
					k = k+1;
				}
			}
		}
		
	return resultat;
	
}
	
	public static void test1(){
		PolyominoList test = new PolyominoList("src/polyominoesINF421.txt");
		test.draw(7,10);
	}

	
	public static void main(String[] args){
		//test1();
		PolyominoList resultat = oneSidedPolyomino(4);
		resultat.draw(8, 10);
		System.out.print(resultat.size());

	}
>>>>>>> origin/master
}