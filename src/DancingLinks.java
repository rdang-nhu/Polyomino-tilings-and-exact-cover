import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;




public class DancingLinks {
	static Charset utf8 = StandardCharsets.UTF_8;
	
	ColumnObject H;
	
	public DancingLinks(ColumnObject H){
		this.H = H;
	}
	
	
	
	public static ColumnObject eC2dL(int[][] cM){
		
		//Définition des dimensions du problème de couverture exacte
		int n = cM.length;
		int m = cM[0].length;
		
		//Définition de la colonne H
		ColumnObject home = new ColumnObject(null, null, null, null, null, 0, "H");
		home.C = home;
		home.R = home;
		home.L = home;
		ColumnObject current_column = home;
		
		//On garde en mémoire le premier élément de chaque ligne et le dernier élément visité de chaque ligne dans 2 tableaux
		DataObject[] first_elem = new DataObject[n];
		DataObject[] current_elem = new DataObject[n];
		

		
		for(int j = 0; j < m; j++){
			
			//Définition de la colonne visitée
			ColumnObject new_column = new ColumnObject(null, null, current_column, null, null, 0, String.valueOf(j + 1));
			new_column.C = new_column; new_column.U = new_column; new_column.D = new_column;
			current_column.R = new_column;
			current_column = new_column;
			

			DataObject current_node = current_column;
			int count = 0;
			
			for (int i = 0; i < n; i++){
				if (cM[i][j] == 1){
					count ++;
					DataObject new_node = new DataObject(current_node, null, null, null, current_column);
					current_node.D = new_node;
					current_node = new_node;
					if (first_elem[i] == null){
						first_elem[i] = current_node;
						current_node.L = current_node;
						current_node.R = current_node;
					}
					else{
						current_node.L = current_elem[i];
						current_elem[i].R = current_node;
					}
					current_elem[i] = current_node;
				}
			}
			
			for(int i = 0; i < n; i++){
				if (first_elem[i] != null && first_elem[i] != current_elem[i]){
					first_elem[i].L = current_elem[i];
					current_elem[i].R = first_elem[i];
				}
			}
			
			current_column.S = count;
			current_column.U = current_node;
			current_node.D = current_column;
		}
		
		current_column.R = home;
		home.L = current_column;
		
		
		
		return home;
	}
	
	public static ColumnObject eC2dL(int[][] cM, String[] name){
		
		//Définition des dimensions du problème de couverture exacte
		int n = cM.length;
		int m = cM[0].length;
		
		//Définition de la colonne H
		ColumnObject home = new ColumnObject(null, null, null, null, null, 0, "H");
		home.C = home;
		home.R = home;
		home.L = home;
		ColumnObject current_column = home;
		
		//On garde en mémoire le premier élément de chaque ligne et le dernier élément visité de chaque ligne dans 2 tableaux
		DataObject[] first_elem = new DataObject[n];
		DataObject[] current_elem = new DataObject[n];
		

		
		for(int j = 0; j < m; j++){
			
			//Définition de la colonne visitée
			ColumnObject new_column = new ColumnObject(null, null, current_column, null, null, 0, name[j]);
			new_column.C = new_column; new_column.U = new_column; new_column.D = new_column;
			current_column.R = new_column;
			current_column = new_column;
			

			DataObject current_node = current_column;
			int count = 0;
			
			for (int i = 0; i < n; i++){
				if (cM[i][j] == 1){
					count ++;
					DataObject new_node = new DataObject(current_node, null, null, null, current_column);
					current_node.D = new_node;
					current_node = new_node;
					if (first_elem[i] == null){
						first_elem[i] = current_node;
						current_node.L = current_node;
						current_node.R = current_node;
					}
					else{
						current_node.L = current_elem[i];
						current_elem[i].R = current_node;
					}
					current_elem[i] = current_node;
				}
			}
			
			for(int i = 0; i < n; i++){
				if (first_elem[i] != null && first_elem[i] != current_elem[i]){
					first_elem[i].L = current_elem[i];
					current_elem[i].R = first_elem[i];
				}
			}
			
			current_column.S = count;
			current_column.U = current_node;
			current_node.D = current_column;
		}
		
		current_column.R = home;
		home.L = current_column;
		
		
		
		return home;
	}

	private static void coverColumn(ColumnObject x){
		x.R.L = x.L;
		x.L.R = x.R;
		for(DataObject t = x.D; t != x; t = t.D){
			for(DataObject y = t.R; y != t; y = y.R){
				y.D.U = y.U;
				y.U.D = y.D;
				y.C.S --   ;
			}
		}
	}
	
	private static void uncoverColumn(ColumnObject x){
		x.R.L = x;
		x.L.R = x;
		for(DataObject t = x.U; t != x; t = t.U){
			for(DataObject y = t.L; y != t; y = y.L){
				y.D.U = y;
				y.U.D = y;
				y.C.S ++ ;
			}
		}
	}

	public static ArrayList<ArrayList<String>> exactCover(ColumnObject H){
		
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
		if(H.R == H){
			ArrayList<String> empty_list = new ArrayList<String>();
			res.add(empty_list);
			return res;
		}
		else{
			
			//Sélection de la colonne avec x.S minimal
			ColumnObject x = (ColumnObject)H.R;
			int counter = 0;
			for(ColumnObject c_pointer = x; c_pointer != H; c_pointer = (ColumnObject)c_pointer.R){
				if(c_pointer.S > counter){
					x = c_pointer;
					counter = c_pointer.S;
				}
			}
			
			coverColumn(x);
			
			for(DataObject t = x.U; t != x; t = t.U){
				String t_set = "[" + x.N;
				for(DataObject y = t.L; y != t; y = y.L){
					coverColumn(y.C);
					if(!(y.C.N.equals("control_key"))){
						t_set = t_set + ", " + y.C.N;
					}
				}
				for (ArrayList<String> eC : exactCover(H)){
					eC.add(t_set + "]");
					res.add(eC);
				}
				for(DataObject y = t.R; y != t; y = y.R){
					uncoverColumn(y.C);
				}
			}
			uncoverColumn(x);
			
			return res;
		}

	}
	
	public static void exactCover_write(ColumnObject H, String file){
		
		int j = 0;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file)))
		{
			
		//Sélection de la colonne avec x.S minimal
		ColumnObject x = (ColumnObject)H.R;
		int counter = 0;
		for(ColumnObject c_pointer = x; c_pointer != H; c_pointer = (ColumnObject)c_pointer.R){
			if(c_pointer.S > counter){
				x = c_pointer;
				counter = c_pointer.S;
			}
		}
		
		coverColumn(x);
		
		for(DataObject t = x.U; t != x; t = t.U){
			String t_set = "[" + x.N;
			for(DataObject y = t.L; y != t; y = y.L){
				coverColumn(y.C);
				if(!(y.C.N.equals("control_key"))){
					t_set = t_set + ", " + y.C.N;
				}
			}
			for (ArrayList<String> eC : exactCover(H)){
				j += 1;
				if(j%10000 ==0){
				System.out.println(j);
				bw.write(j + " : ");
				for(int v = 0; v<eC.size();v++){
					bw.write(eC.get(v));
				}
				bw.write(t_set + "]"); 
				bw.newLine();
						
					}}
			for(DataObject y = t.R; y != t; y = y.R){
				uncoverColumn(y.C);
			}
		}
		uncoverColumn(x);
		
	}
		catch (IOException e) {

			e.printStackTrace();
		
		}

	}
	
	public static int exactCover_count(ColumnObject H){
		
		int res = 0;
		
		if(H.R == H){
			return 0;
		}
		else{
			
			//Sélection de la colonne avec x.S minimal
			ColumnObject x = (ColumnObject)H.R;
			int counter = 0;
			for(ColumnObject c_pointer = x; c_pointer != H; c_pointer = (ColumnObject)c_pointer.R){
				if(c_pointer.S > counter){
					x = c_pointer;
					counter = c_pointer.S;
				}
			}
			
			coverColumn(x);
			
			for(DataObject t = x.U; t != x; t = t.U){
				String t_set = "[" + x.N;
				for(DataObject y = t.L; y != t; y = y.L){
					coverColumn(y.C);
					if(!(y.C.N.equals("control_key"))){
						t_set = t_set + ", " + y.C.N;
					}
				}
				res += exactCover(H).size();
				for(DataObject y = t.R; y != t; y = y.R){
					uncoverColumn(y.C);
				}
			}
			uncoverColumn(x);
			
			return res;
		}

	}
	
	
	public static void test1(){
		int[][] A_0 = {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1}};
		int[][] A_1 = {{1,0,0},{0,1,1},{0,0,1}};
		int[][] A_2 = {{1}};
		int[][] A_3 = {{1,1},{1,0},{0,1}};
		int[][] A_4 = {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1}, {1,1,0,1,0,0,1}, {0,1,1,0,1,1,0}};
		int[][] A_5 = {{0,1,0},{1,0,0},{0,0,1},{1,1,0},{0,1,1},{1,0,1},{1,1,1}};
		ColumnObject A = eC2dL(A_5);
		ArrayList<ArrayList<String>> res = exactCover(A);
		for (ArrayList<String> s : res){
			System.out.println(" ");
			System.out.println("newcover composed of sets ");
			for(String i : s){
				System.out.print(i + " ");
			}
		}
		
	}
	
		public static void test2(int n){

			int[][] A = MatrixSet.allSubsets(n);
			System.out.println(A.length);
			ColumnObject H = eC2dL(A);
			System.out.println(Arrays.deepToString(A));
			exactCover_write(H, "file3.txt");
		}
		
		public static void test3(int n, int k){
			int[][] A = MatrixSet.kSubsets(n,k);
			ColumnObject H = eC2dL(A);
			System.out.println(Arrays.deepToString(A));
			ArrayList<ArrayList<String>> res = exactCover(H);
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
	
		static void test2polyomino(int n, int k){
	    	Polyomino p = new Polyomino();
	    	for(int i = 0; i < n; i++){
	    		for(int j = 0; j < n; j++){
	    			int[] a = {i,i/2+j};
	    			p.cases.add(a);
	    		}
	    	}
	    	
			PolyominoList l = PolyominoList.fixedPolyomino(k);
			//PolyominoTilings toPrint = new PolyominoTilings(p,l);
			
			//toPrint.draw(10,10);
	    }
	public static void main(String[] args){
		//test1();
		test2polyomino(8,4);
		//test3(10,5);
	}
	
	
	
}
