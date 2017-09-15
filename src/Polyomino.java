<<<<<<< HEAD
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
=======
import java.util.*;

public class Polyomino{
	// un tuple est stockï¿½ comme un tableau de deux entiers
	ArrayList<int[]> cases = new ArrayList<int[]>(); 
	
	// On ajoute ï¿½galement les cases_voisines (utilisï¿½ pour gï¿½nï¿½rer des polyominos)
	ArrayList<int[]> casesVoisines;
	
	// Pour tester l'ï¿½galitï¿½, on garde en mï¿½moire la case en bas ï¿½ gauche en permanence
	Integer[] case0;
	
	// Constructeur de base
	Polyomino(){
		cases = new ArrayList<int[]>();
		casesVoisines = new ArrayList<int[]>();
		case0 = new Integer[2];
	}
	
	public void computeCase0(){
		Integer[] res = new Integer[2];
		res[0] = cases.get(0)[0];
		res[1] = cases.get(0)[1];
		for(int i = 0; i < cases.size(); i++){
			int[] a = cases.get(i);
			if((a[1] < res[1]) || (a[1]==res[1] && a[0]<res[0])){
				res[0] = a[0];
				res[1] = a[1];
			}
		}
		this.case0 = res;
	}
	
	// Crï¿½ation ï¿½ partir d'une chaï¿½ne
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
				int[] coordonnees = {Integer.parseInt(x),Integer.parseInt(y)}; //fonction qui convertit une chaï¿½ne en entier
				cases.add(coordonnees);
			}
		}
	}
	
	public boolean contains(int[] coord, int[] caseTest){
		boolean res = false;
		for(int i = 0; i < cases.size(); i++){
			if(cases.get(i)[0] == coord[0]+caseTest[0] && cases.get(i)[1] == coord[1]+caseTest[1]){
				res = true;
			}
		}
		return res;
	}
	
	public boolean contains(Polyomino l, int[] caseTest){ // sert dans fixedPolyominoTilings
		for(int i = 0; i < l.cases.size(); i++){
			if(! this.contains(l.cases.get(i), caseTest) ) return false;
		}
		return true;
	}
	
	public boolean vContains(int[] coord){
		boolean res = false;
		for(int i = 0; i < casesVoisines.size(); i++){
			
			if(casesVoisines.get(i)[0] == coord[0] && casesVoisines.get(i)[1] == coord[1]){
				res = true;
			}
		}
		return res;
	}
	
	public static Polyomino clone(Polyomino p){
		Polyomino res = new Polyomino();
		for(int i = 0; i<p.cases.size();i++){
			int x = p.cases.get(i)[0];
			int y = p.cases.get(i)[1];
			int[] aux = {x,y};
			res.cases.add(aux);
		}
		return res;
	}
		
	public static Polyomino copy(Polyomino p){
		Polyomino res = new Polyomino();
		for(int i = 0; i<p.cases.size();i++){
			int x = p.cases.get(i)[0];
			int y = p.cases.get(i)[1];
			int[] aux = {x,y};
			res.cases.add(aux);
		}
		for(int i = 0; i<p.casesVoisines.size();i++){
			int x = p.casesVoisines.get(i)[0];
			int y = p.casesVoisines.get(i)[1];
			int[] aux = {x,y};
			res.casesVoisines.add(aux);
		}
		res.case0[0] = p.case0[0];
		res.case0[0] = p.case0[0];
		return res;
	}
	
	// fonction qui compare 2 polyominos de mï¿½me aire
	public boolean isEqualFixed(Polyomino p){
		p.computeCase0();
		this.computeCase0();
		boolean res = true;
		for(int i = 0; i < p.cases.size(); i++){
			int[] a = new int[2];
			a[0] = p.cases.get(i)[0] - p.case0[0] + this.case0[0];
			a[1] = p.cases.get(i)[1] - p.case0[1] + this.case0[1];
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
	
	public boolean isEqualOneSided(Polyomino p){
		
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		if(this.isEqualFixed(p)) return true;
		p.rotation();
		
		return false;
	}
	
	public void translater(int x, int y){
		for(int l = 0; l < this.cases.size(); l++){
			this.cases.get(l)[0] += x;
			this.cases.get(l)[1] += y;
		}
	}
	
	public void rotation(){ //quart de tour sens trigo
		for(int i = 0; i < this.cases.size(); i++){
			int u =  this.cases.get(i)[0];
			this.cases.get(i)[0] = -this.cases.get(i)[1];
			this.cases.get(i)[1] = u;
		}
	}
	
	public void dilatation(int k){ 
		ArrayList<int[]> cases = new ArrayList<int[]>(); 
		for(int i = 0; i < this.cases.size(); i++){
			for(int j = 0; j < k; j++){
				for(int l = 0; l<k; l++){
					int[] coord = new int[2];
					coord[0] = k*this.cases.get(i)[0]+ l;
					coord[1] = k*this.cases.get(i)[1]+ j;
					cases.add(coord);
				}
			}
		}
		this.cases=cases;		
	}
	
	
	public void symetrie_x(){
		for(int i = 0; i < this.cases.size(); i++){
			this.cases.get(i)[1] = -this.cases.get(i)[1];
		}
	}
	
	public void symetrie_y(){
		for(int i = 0; i < this.cases.size(); i++){
			this.cases.get(i)[1] = -this.cases.get(i)[1];
		}
	}
	
	@Override
	
	
	
	public String toString(){
		String s = "";
		for(int j = 0; j < this.cases.size(); j++){
			int[] pr = this.cases.get(j);
			s += "("+pr[0]+",";
			s += pr[1]+")";
		}
		s += "\n";
		return s;
	}
	
	public void printCasesV(){
		String s = "";
		for(int j = 0; j < this.casesVoisines.size(); j++){
			int[] pr = this.casesVoisines.get(j);
			s += "("+pr[0]+",";
			s += pr[1]+")";
		}
		s += "\n";
		System.out.print(s);
	}
	
	
	public static void test1(){
		Polyomino p = new Polyomino("[(12,23),(45,600),(123,4500)]/n");
		Polyomino p0 = new Polyomino("[(0,0),(0,1),(0,2),(2,0),(2,1),(2,2),(1,2),(3,2)]/n");
		System.out.print(p.toString());
	}
	
	
	public static void main(String[] args){
		//test1(); //ï¿½ commenter pour ï¿½viter d'avoir toujours le rï¿½sultat
		
	}
}
>>>>>>> origin/master
	