import java.util.*;


public class ExactCover {
	
	
	public static ArrayList<List<Integer>> exactCover(int[][] cM){
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		
		//Si la collection est vide il n'existe pas de couverture exacte du problème à partir de la collection, si l'ensemble est vide l'ensemble vide est une couverture exacte du problème
		int n = cM.length;
		if(n==0) return res;
		int m = cM[0].length;
		if(m==0) return res;

		//On définit 2 files qui contiennent les éléments du tableau qui sont encore à explorer, et 2 compteurs qui les dénombres
		int count_C = n;
		int count_X = m;
		
		int[] remain_C = new int[n];
		int[] remain_X = new int[m];
		
		res = exactCoverAux(cM, count_C, count_X, n, m, remain_C, remain_X);
		return res;
	}
		
	public static ArrayList<List<Integer>> exactCoverAux(int[][] cM, int count_C, int count_X, int n, int m, int[] remain_C, int[] remain_X){
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		
		if (count_X == 0){
			return res;
		}
		
		else{
			//On choisit l'élément de X à couvrir en premier
			int j_0 = first_def_elem(remain_X);
			//System.out.println("j_0 = " + j_0);
			
			//On parcout les éléments de C restants qui contiennent l'élément choisit
			for (int i=0; i<n; i++){
				if (remain_C[i] == 0 && cM[i][j_0] ==1){
			
			//On copie les tableaux des éléments restants, que l'on modifie
					int[] new_remain_C = remain_C.clone();
					int[] new_remain_X = remain_X.clone();
					int new_count_C = count_C;
					int new_count_X = count_X;
					for(int l = 0; l < m; l++){
						if (new_remain_X[l] == 0 && cM[i][l] == 1){
							new_count_X --;
							new_remain_X[l] = -1;
							for (int k = 0; k < n; k++){
								if (new_remain_C[k] == 0 && cM[k][l] == 1){
									new_count_C --;
									new_remain_C[k] = -1;}
							}
						}
					}
					//System.out.println("new_count_C = " + new_count_C);
					//System.out.println("new_count_X = " + new_count_X);
					if (new_count_X == 0){
						List<Integer> toadd = new ArrayList<Integer>();
						toadd.add(i);
						res.add(toadd);
					}
					else if(new_count_C > 0){
						ArrayList<List<Integer>> new_res = exactCoverAux(cM, new_count_C, new_count_X, n, m, new_remain_C, new_remain_X);
						for (List<Integer> eC : new_res){
							eC.add(i);
							res.add(eC);
						}
					}
				}
			}
		return res;	
		}
	}
	
	public static int first_def_elem(int[] A){
		int n = A.length;
		int i = 0;
		int j = -1;
		while (i<n && (j==-1)){
			if (A[i]!= -1) j=i;
			i ++;
		}
		return j;
	}
	
	public static void test1(){
		int[][] A_0 = {{0,0,1,0,1,1,0},{1,0,0,1,0,0,1},{0,1,1,0,0,1,0},{1,0,0,1,0,0,0},{0,1,0,0,0,0,1},{0,0,0,1,1,0,1}};
		int[][] A_1 = {{1,0},{0,1},{0,1}};
		int[][] A_3 = {{1,1},{1,0},{0,1}};
		System.out.println(Arrays.deepToString(A_0));
		ArrayList<List<Integer>> res = exactCover(A_3);
		for (List<Integer> s : res){
			System.out.println(" ");
			System.out.print("newcover composed of sets ");
			for(Integer i : s){
				System.out.print(i + " ");
			}
		}
	}
	
	//Test avec l'ensemble des sous-ensembles de {1,...,n}
	
	public static void test2(int n){
		int[][] A = MatrixSet.allSubsets(n);
		System.out.println(Arrays.deepToString(A));
		ArrayList<List<Integer>> res = exactCover(A);
		for (List<Integer> s : res){
			System.out.println(" ");
			System.out.print("newcover composed of sets ");
			for(Integer i : s){
				System.out.print(i + " ");
			}
		}
	}
	
	//Test avec l'ensemble des sous-ensembles de taille k de {1,..,n}
	
	public static void test3(int n, int k){
		int[][] A = MatrixSet.kSubsets(n , k);
		System.out.println(Arrays.deepToString(A));
		ArrayList<List<Integer>> res = exactCover(A);
		for (List<Integer> s : res){
			System.out.println(" ");
			System.out.print("newcover composed of sets ");
			for(Integer i : s){
				System.out.print(i + " ");
			}
		}
	}
	
	public static void main(String[] args){
		//test1();
		//test2(15);
		//test3(4,2);
		
	}
}
