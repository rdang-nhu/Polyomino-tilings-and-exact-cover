import java.util.ArrayList;

public class Utils {
	
	public static int[][] convertA2M(ArrayList<int[]> matriceCouv){
		//Convertit un ArrayList<int[]> en int[][]
		int n = matriceCouv.size();
		int m = matriceCouv.get(0).length;
		int[][] cM = new int[n][m];
		for (int i = 0; i <n; i++){
			for (int j = 0; j <m; j++){
				cM[i][j] = matriceCouv.get(i)[j];
			}
		}
		return cM;
	}
	
	public static void print_asMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[i].length; j++) {
		        System.out.print(matrix[i][j] + " ");
		    }
		    System.out.println();
		}
	}
	
	public static String CoordinateStandardization(String entry){
		//Convertit un string (généralement convertit via la méthode Arrays.toString() avec le format [a, b] en string au format (a,b) qui correspond au format proposé par l'énoncé
		entry = entry.replaceFirst(" ", "");
		entry = entry.replace('[', '(');
		entry = entry.replace(']', ')');
		return(entry);
	}
	
    public static int binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;
 
        long b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return (int)b;
    }
}
