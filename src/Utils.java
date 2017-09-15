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
	
	public static String CoordinateStandardization(String entry){
		//Convertit un string (g�n�ralement convertit via la m�thode Arrays.toString() avec le format [a, b] en string au format (a,b) qui correspond au format propos� par l'�nonc�
		entry = entry.replaceFirst(" ", "");
		entry = entry.replace('[', '(');
		entry = entry.replace(']', ')');
		return(entry);
	}
}
