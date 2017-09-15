import java.util.*;

public class Polyomino{
	// un tuple est stocké comme un tableau de deux entiers
	ArrayList<Square> cases = new ArrayList<Square>(); 
	
	// On ajoute également les cases_voisines (utilisé pour générer des polyominos)
	ArrayList<Square> cVNI;
	ArrayList<Square> cVI;
	// Pour tester l'égalité, on garde en mémoire la case en bas à gauche (ordre lexicographique) en permanence
	Square case0;
	
	// Constructeur de base
	Polyomino(){
		cases = new ArrayList<Square>();
		cVNI = new ArrayList<Square>();
		cVI = new ArrayList<Square>();
		case0 = new Square(0,0);
	}
	
	public void computeCase0(){
		case0 = cases.get(0);
		for(Square a : cases){
			if(case0.isStrictlySuperior(a)){
				case0 = a.copy();
			}
		}
	}
	
	// Création à partir d'une chaîne
	Polyomino(String s){ 
		int i = 0;
		int l = s.length();
		while(i < l){
			char car = s.charAt(i);
			i ++;
			if(car == '('){
				String x = "";
				String y = "";
				char lecture = s.charAt(i); 
				i++;
				while(lecture != ','){ //lecture de x
					x = x + lecture;
					lecture = s.charAt(i);
					i++;
				}
				lecture = s.charAt(i); //lecture de la virgule
				i++;
				while(lecture != ')'){ //lecture de y
					y = y + lecture;
					lecture = s.charAt(i);
					i++;
					
				}
				Square c = new Square(Integer.parseInt(x),Integer.parseInt(y)); //fonction qui convertit une chaîne en entier
				cases.add(c);
			}
		}
	}
	
	public boolean contains(Square c, int[] translation){
		boolean res = false;
		for(Square a : cases){
			if(a.getX() == c.getX()+translation[0] && a.getY() == c.getY()+translation[1]){
				res = true;
			}
		}
		return res;
	}
	
	public boolean contains(Polyomino l, int[] translation){ // sert dans fixedPolyominoTilings
		for(Square a : l.cases){
			if(! this.contains(a, translation) ) return false;
		}
		return true;
	}
	
	public boolean vContains(Square c){
		boolean res = false;
		for(Square a : cVNI){
			if(a.isEqual(c)){
				res = true;
			}
		}
		for(Square a : cVI){
			if(a.isEqual(c)){
				res = true;
			}
		}
		return res;
	}
	
	public static Polyomino copy(Polyomino p){
		Polyomino res = new Polyomino();
		for(Square a : p.cases){
			Square aux = a.copy();
			res.cases.add(aux);
		}
		res.case0 = p.case0.copy();
		return res;
	}
	
	// fonction qui compare 2 polyominos de même aire
	public boolean isEqualFixed(Polyomino p){
		p.computeCase0();
		this.computeCase0();
		boolean res = true;
		for(Square c : p.cases){
			Square a = new Square(c.getX() - p.case0.getX() + this.case0.getX(),c.getY() - p.case0.getY() + this.case0.getY());
			int[] caseTest = {0,0};
			if (! this.contains(a, caseTest)){
				res = false;
			}
		}
		return res;
	}
	
	public boolean isEqualFree(Polyomino p){
		
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		
		p.symetrie_x();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.symetrie_x();
		return false;
	}
	
	public void translater(int x, int y){
		for(Square a : cases){
			a.squareX(a.getX()+x);
			a.squareY(a.getY()+y);
		}
	}
	
	public void rotation(){ //quart de tour sens trigo
		for(Square a : cases){
			int u = a.getX();
			a.squareX(-a.getY());
			a.squareY(u);
		}
	}
	public void rotation2(){ //quart de tour sens trigo
		for(Square a : cases){
			int u = -a.getX();
			a.squareX(a.getY());
			a.squareY(u);
		}
	}
	
	public void symetrie_x(){
		for(Square a : cases){
			a.squareX(-a.getX());
		}
	}
	
	public void symetrie_y(){
		for(Square a : cases){
			a.squareY(-a.getY());
		}
	}
	
	@Override

	public String toString(){
		String s = "";
		for(Square a : cases){
			s += "("+a.getX()+",";
			s += a.getY()+")";
		}
		s += "\n";
		return s;
	}
	
	public void printCasesV(){
		String s = "";
		for(Square a : cVNI){
			s += "("+a.getX()+",";
			s += a.getY()+")";
		}
		s += "\n";
		System.out.print(s);
	}
	
	
	public static void test1(){
		Polyomino p = new Polyomino("[(12,23),(45,600),(123,4500)]/n");
		System.out.print(p.toString());
	}
	
	
	public static void main(String[] args){
		test1(); 
		
	}
}
	