import java.util.*;

public class Polyomino {
	LinkedList<int[]> liste = new LinkedList<int[]>(); // un tuple est stocké comme deux entiers
	Polyomino(String s){ // la fonction est un peu chiante, il faut lire une chaîne
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
	
	public static void test1(){
		Polyomino p = new Polyomino("[(12,23),(45,600),(123,4500)]/n");
		while(! p.liste.isEmpty()){
			int[] a = p.liste.pop();
			System.out.println(a[0]);
			System.out.println(a[1]);
		}
	}
	
	public static void main(String[] args){
		//test1(); //à commenter pour éviter d'avoir toujours le résultat
		
	}
}


