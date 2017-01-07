import java.util.*;


public class PolyominoTilings {
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
	
	public static ArrayList<PolyominoList> allTilings(ColumnObject H){
		//Initilisation et calcul des pavages
		ArrayList<PolyominoList> toPrint = new ArrayList<PolyominoList>();
		ArrayList<ArrayList<String>> res = DancingLinks.exactCover(H);
		
		for (List<String> s : res){
			PolyominoList pl = new PolyominoList();
			for(String i : s){
				pl.add(new Polyomino(i));
			}
			toPrint.add(pl);
		}
		return toPrint;
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

	public static void main(String[] args){
		Polyomino p = new Polyomino("(0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)");
		//System.out.println(CoordinateStandardization(Arrays.toString(p.cases.get(0))));
		PolyominoList l = PolyominoList.fixedPolyomino(3);
		ColumnObject H = fixedPolyominoTilings(p,l);
		ArrayList<PolyominoList> toPrint = allTilings(H);
		PolyominoList pl = toPrint.get(0);
		Polyomino pol = pl.get(0);
		System.out.print(pol.toString());
		//pl.draw(5, 10);
		//System.out.print(p);
		//System.out.print(l);
		ArrayList<ArrayList<String>> res = DancingLinks.exactCover(H);
		
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
}	