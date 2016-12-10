import java.util.*;

public class Polyomino {
	LinkedList<int[]> liste = new LinkedList<int[]>(); // un tuple est stocké comme un tableau de deux entiers
	Polyomino(String s){ // il faut lire une chaîne
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
				int[] coordonnées = {Integer.parseInt(x),Integer.parseInt(y)}; //fonction qui convertit une chaîne en entier
				liste.addLast(coordonnées);
			}
		}
	}
	

	public void translater(int x, int y){
		for(int i = 0; i < this.liste.size(); i++){
			this.liste.get(i)[0] += x;
			this.liste.get(i)[1] += y;
		}
	}
	
	public void rotation(int x, int y){ //quart de tour sens trigo
		for(int i = 0; i < this.liste.size(); i++){
			int u =  this.liste.get(i)[0];
			this.liste.get(i)[0] = -this.liste.get(i)[1];
			this.liste.get(i)[1] = u;
		}
	}
	
	public void dilatation(int x){ //quart de tour sens trigo
		for(int i = 0; i < this.liste.size(); i++){
			this.liste.get(i)[0] = x*this.liste.get(i)[0];
			this.liste.get(i)[1] = x*this.liste.get(i)[0];
		}
	}
	
	public void symetrie_x(){
		for(int i = 0; i < this.liste.size(); i++){
			this.liste.get(i)[0] = -this.liste.get(i)[0];
		}
	}
	
	public void symetrie_y(){
		for(int i = 0; i < this.liste.size(); i++){
			this.liste.get(i)[1] = -this.liste.get(i)[1];
		}
	}
	
	@Override
	
	
	
	public String toString(){
		String s = "";
		for(int i = 0; i < this.liste.size(); i++){
			int[] a = this.liste.get(i);
			s += a[0]+",";
			s += a[1]+"\n";
		}
		return s;
	}
	
	public static void test1(){
		Polyomino p = new Polyomino("[(12,23),(45,600),(123,4500)]/n");
		System.out.print(p.toString());
	}
	
	
	public static void main(String[] args){
		//test1(); //à commenter pour éviter d'avoir toujours le résultat
		
	}
}


