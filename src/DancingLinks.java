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
	
	
	public static ColumnObject cM2dL(int[][] cM){
		
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
	
	public static ColumnObject cM2dL(int[][] cM, String[] name){
		
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
	
	public static ArrayList<ArrayList<String>> exactCover_one(ColumnObject H){
		
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
				if (!exactCover(H).isEmpty()){
					ArrayList<String> res_= exactCover(H).get(0);
					res_.add(t_set + "]");
					res.add(res_);
					return res;
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
				//if(j%10000 ==0){
				//System.out.println(j);
				bw.write(j + " : ");
				for(int v = 0; v<eC.size();v++){
					bw.write(eC.get(v));
				}
				bw.newLine();
						
					}
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
			return 1;
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
				for(DataObject y = t.L; y != t; y = y.L){
					coverColumn(y.C);
				}
				res += exactCover_count(H);
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
		ColumnObject A = cM2dL(A_0);
		System.out.println(exactCover_count(A));
		ArrayList<ArrayList<String>> res = exactCover(A);
		for (ArrayList<String> s : res){
			System.out.println(" ");
			System.out.println("newcover composed of sets ");
			for(String i : s){
				System.out.print(i + " ");
			}
		}
		
	}
	
		public static void test_allSubsets(int n){

			int[][] A = MatrixSet.allSubsets(n);
			ColumnObject H = cM2dL(A);
			System.out.println(exactCover_count(H));
		}
		
		public static void testkSubsets(int n, int k){
			int[][] A = MatrixSet.kSubsets(n,k);
			ColumnObject H = cM2dL(A);
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
	
	public static void main(String[] args){
		test_allSubsets(3);
		test1();
		//test2polyomino(8,4);
		//testkSubsets(10,5);
	}
	
	
	
}
