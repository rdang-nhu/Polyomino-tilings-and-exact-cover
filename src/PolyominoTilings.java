import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class PolyominoTilings extends ArrayList<PolyominoList>{
	ColumnObject H;
	ArrayList<ArrayList<String>> res;
	int counter;
	
	PolyominoTilings(){
		
	}
	
    PolyominoTilings(Polyomino p, PolyominoList l, String problem_type, String result_type){
    	
    	//problem_type définit le type de problème reusable : on réutilise au temps de fois qu'on veut le même polyomino, sans forcément tous les utiliser
    	//										   all_once : on doit utiliser chaque polyomino une fois exactement
    	//										   once : on ne peut utiliser qu'une seule fois un polyomino donné
    	
    	//result_type définit la forme du résultat renvoyé : print : affichage graphique des solutions
    	//													 number : retourne le nombre de pavage
    	//													 autre : on suppose que l'on veut enregistrer le résultat et que result_type indique le fichier
    	//													 return : on enregistre simplement les solutions calculées dans l'objet PolyominoTillings créé
    	//													 one : on affiche une seule solution au problème de couverture exacte
    	super();
    	
		boolean not_reusable = (problem_type.equals("once") || problem_type.equals("all_once"));
		boolean not_all  = problem_type.equals("once");
		
    	this.H = fixedPolyominoTilings(p,l,not_reusable, not_all);
    	
    	if (result_type == "print"){
    		this.res = DancingLinks.exactCover(H);
    		for (List<String> s : this.res){
    			PolyominoList pl = new PolyominoList();
    			for(String i : s){
    				pl.add(new Polyomino(i));
    			}
    			this.add(pl);
    		}
    		this.draw(10,15);
    	}
    	
    	else if(result_type == "return"){
    		this.res = DancingLinks.exactCover(H);
    		for (List<String> s : res){
    			PolyominoList pl = new PolyominoList();
    			for(String i : s){
    				pl.add(new Polyomino(i));
    			}
    			this.add(pl);
    		}
    	}
    	
    	else if (result_type == "number"){
    		this.counter = DancingLinks.exactCover_count(H);
    		//System.out.println(this.counter);
    	}
    	
    	else if (result_type == "print_one"){
    		this.res = DancingLinks.exactCover_one(H);
    		for (List<String> s : this.res){
    			PolyominoList pl = new PolyominoList();
    			for(String i : s){
    				pl.add(new Polyomino(i));
    			}
    			this.add(pl);
    		}
    		this.draw(10,15);
    	}
    	
    	else if(result_type == "return_one"){
    		this.res = DancingLinks.exactCover_one(H);
    		for (List<String> s : res){
    			PolyominoList pl = new PolyominoList();
    			for(String i : s){
    				pl.add(new Polyomino(i));
    			}
    			this.add(pl);
    		}
    	}
    	
       	else{
    		DancingLinks.exactCover_write(H, result_type);
    	}
    }
    
	public static ColumnObject fixedPolyominoTilings(Polyomino p, PolyominoList l, boolean not_reusable, boolean not_all){
		
		// On suppose que tous les Polyominos de l ont leur case0 en {0,0}
		// Comme on ne connaît pas le nombre de polyominos qui conviennent, on part sur une ArrayList
		
		//On calcul la matrice de couverture du problème
		
		ArrayList<int[]> matriceCouv = new ArrayList<int[]>();
		for(int i = 0; i < l.size(); i++){
			Polyomino test = l.get(i);
			for(int j = 0; j < p.cases.size(); j++){
				int[] ligne = new int[p.cases.size() + ( not_reusable ? 1 : 0 ) * l.size()];
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
					if (not_reusable){
						ligne[i + p.cases.size()] = 1;
					}
					matriceCouv.add(ligne);	
				}
			}
			if (not_all){
				int[] ligne = new int[p.cases.size() + l.size()];
				ligne[p.cases.size() + i]=1;
				matriceCouv.add(ligne);
			}
		}
		
		//Création du tableau contenant le nom des colonnes
		String[] name = new String[p.cases.size() + ( not_reusable ? 1 : 0 ) * l.size()];
		for (int i = 0; i < p.cases.size(); i++){
			name[i] = Utils.CoordinateStandardization(Arrays.toString(p.cases.get(i)));
		}
		for (int i = p.cases.size(); i < p.cases.size() + ( not_reusable ? 1 : 0 )*l.size(); i++){
			name[i] = "control_key";
		}
		
		
		//Conversion de l'ArrayList<int[]> en int[][], format utilisé pour le problème de couverture exacte
		int[][] cM = Utils.convertA2M(matriceCouv);
		
		//Création de l'objet associé au problème de couverture exacte
		ColumnObject H = DancingLinks.cM2dL(cM, name);
		//Utils.print_asMatrix(cM);
		
		return H;
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
    
    public void register(String file){
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
		{int j = 1;
		for (PolyominoList s : this){
			bw.write(j + " :"); 
			for(Polyomino i : s){
				bw.write("[");
				bw.write(i.toString());
				bw.write("]");
			}
			bw.newLine();
			j++;
		}}
		catch (IOException e) {

			e.printStackTrace();
		
		}

	}
    
    

	// Je passe la méthode en dynamique pour rester proche du paradigme objet, PolyominoTilings devient un constructeur

    
}	