import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class PolyominoTilings extends ArrayList<PolyominoList>{
	ColumnObject H;
	ArrayList<ArrayList<String>> res;
	
	
    PolyominoTilings(Polyomino p, PolyominoList l){
    	super();
    	this.H = fixedPolyominoTilings(p,l);
    	//System.out.println(((ColumnObject)H.R).S);
    	System.out.println("DancingLinks problème identifié");
		//this.res = DancingLinks.exactCover(H);
    	DancingLinks.exactCover_write(H);
		
//		int j = 0;
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter("file3.txt")))
//		{for (List<String> s : res){
//			bw.write("La " + j + " ème couverture composée des ensembles : ");
//			for(String i : s){
//				bw.write(i); ;
//				}
//			bw.newLine();
//			j += 1;
//			//if (j%100 == 0) System.out.println(j);
//		}
//		}
//		catch (IOException e) {
//
//			e.printStackTrace();
//		
//		}
//		
//		for (List<String> s : res){
//			PolyominoList pl = new PolyominoList();
//			for(String i : s){
//				pl.add(new Polyomino(i));
//			}
//			this.add(pl);
//		}
    }
    
	public static ColumnObject fixedPolyominoTilings(Polyomino p, PolyominoList l){
		
		// On suppose que tous les Polyominos de l ont leur case0 en {0,0}
		// Comme on ne connaît pas le nombre de polyominos qui conviennent, on part sur une ArrayList
		ArrayList<int[]> matriceCouv = new ArrayList<int[]>();
		for(int i = 0; i < l.size(); i++){
			Polyomino test = l.get(i);
			for(int j = 0; j < p.cases.size(); j++){
				int[] ligne = new int[p.cases.size()];
				int[] caseTest = p.cases.get(j);
				if(p.contains(test,caseTest)){
					for(int k = 0; k < p.cases.size(); k++){
						 int[] case2 = new int[2];
						 case2[0] = -caseTest[0];
						 case2[1] = -caseTest[1];
						if(test.contains(p.cases.get(k), case2) ){
							ligne[k] = 1;
						}
						else ligne[k] = 0;
					}
					matriceCouv.add(ligne);
				}
			}
		}
		
		//Création du tableau contenant le nom des colonnes
		String[] name = new String[p.cases.size()];
		for (int i = 0; i < p.cases.size(); i++){
			name[i] = Utils.CoordinateStandardization(Arrays.toString(p.cases.get(i)));
		}
		
		//Conversion de l'ArrayList<int[]> en int[][], format utilisé pour le problème de couverture exacte
		int[][] cM = Utils.convertA2M(matriceCouv);

		
		//On crée l'objet associé au problème de couverture exacte
		ColumnObject H = DancingLinks.eC2dL(cM, name);
		
		return H;
	}
	
	
	

    public static void print(ArrayList<int[]> matriceCouv){
    	for(int i = 0; i < matriceCouv.size(); i ++){
    		int[] ligne = matriceCouv.get(i);
    		System.out.print("{");
     		for(int j = 0; j < ligne.length; j ++){
     			System.out.print("("+ligne[j]+")");
     		}
     		System.out.println("}");
    	}
    }
    
    
    // Passage en fonction de l'ensemble du problème
    
    public void draw(int taille, int g){
    	//On dessine tous les tilings
    			Image2d img = new Image2d(1300,500); 
    			
    			// Pour chaque tiling
    			for(int i = 0; i < this.size(); i++){
    				PolyominoList pl = this.get(i);
    				for(int j = 0; j < pl.size(); j++){
    					// On translate tous les polyominos du tiling
    					pl.get(j).translater(1+(taille*i)%(1250/g),1+taille*((taille*i)/(1250/g)));
    				}
    				
    				pl.draw_aux(0, g, img);
    			}
    			new Image2dComponent(img);
    			new Image2dViewer(img);
    }
    public void print(){
    	int j = 1;
		for (List<String> s : res){
			System.out.println(" ");
			System.out.print("newcover number " +j +" composed of sets ");
			for(String i : s){
				System.out.print(i + " ");
			}
			j++;
		}
    }

	// Je passe la méthode en dynamique pour rester proche du paradigme objet, PolyominoTilings devient un constructeur

    static void test1(){
    	Polyomino p = new Polyomino("(0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)");
		//System.out.println(CoordinateStandardization(Arrays.toString(p.cases.get(0))));
		PolyominoList l = PolyominoList.fixedPolyomino(3);
		PolyominoTilings toPrint = new PolyominoTilings(p,l);
		toPrint.draw(10,5);
    }
    
    static void test2(int n, int k){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			int[] a = {i,i/2+j};
    			p.cases.add(a);
    		}
    	}
    	
		PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l);
		
		//toPrint.draw(10,10);
    }
    
    static void test3(int n){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			int[] a = {i,j};
    			p.cases.add(a);
    		}
    	}
    	
		PolyominoList l = PolyominoList.fixedPolyomino(3);
		PolyominoTilings toPrint = new PolyominoTilings(p,l);
		//toPrint.draw(10,10);
    }
    
    static void test4(int n, int k){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			if(j<2*(i+1) && i < n/2){
	    			int[] a = {i,j};
	    			p.cases.add(a);
    			}
    			else if(j < 2*(n-i) && i > n/2-1){
    				int[] a = {i,j};
	    			p.cases.add(a);
    			}
    		}
    	}
    	PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l);
		//toPrint.draw(10,10);
    }
    
    static void test5(int n, int k){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			if(n/2 - i - 1 <= j && j <= n/2 + i && i < n/2){
	    			int[] a = {i,j};
	    			p.cases.add(a);
    			}
    			else if(j<= n-i+n/2 -1 && j >= Math.abs(n/2 - n + i) && i > n/2-1){
    				int[] a = {i,j};
	    			p.cases.add(a);
    			}
    		}
    	}
    	PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l);
		//toPrint.draw(10,10);
    }
    
    
	public static void main(String[] args){
		//test2(5);
		//test3(3);
		test2(8,4);
		//System.out.println(CoordinateStandardization(Arrays.toString(p.cases.get(0))));
		
	}
}	