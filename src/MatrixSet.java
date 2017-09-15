<<<<<<< HEAD

public class MatrixSet {
	
	
	public static int[][] allSubsets(int n){
		int [][] res = new int[(int)Math.pow(2, n)][n];
	    String s;
		for (int i=0; i<(int)Math.pow(2,n); i++){
			s = Integer.toBinaryString(i);
			int l = s.length();
			for (int j = 0; j < l; j++){
				if(s.charAt(l-j-1) == '1'){
					res[i][j] = 1;
				}
			}
		}
		return res;
	}
	
	public static int[][] kSubsets(int n, int k){
		int nsets = Binomial.binomial(n,k);
		int [][] res = new int[nsets][n];
		String s;
		int line_pointer = 0;
		for(int i = 0; i < Math.pow(2,n); i ++){
			int counter = 0;
			s = Integer.toBinaryString(i);
			int l = s.length();
			if(l > k-1){
				for(int j = 0; j<l; j++){
					if (s.charAt(j) == '1'){
						counter ++;
					}
				}
			}
			if (counter == k){
				for (int j = 0; j < l; j++){
					if (s.charAt(l - j - 1) == '1'){
						res[line_pointer][j] = 1;
					}
				}
				line_pointer ++;	
			}
		}
		return res;
	}
}
=======

public class MatrixSet {
	
	
	public static int[][] allSubsets(int n){
		int [][] res = new int[(int)Math.pow(2, n)][n];
	    String s;
		for (int i=0; i<(int)Math.pow(2,n); i++){
			s = Integer.toBinaryString(i);
			int l = s.length();
			for (int j = 0; j < l; j++){
				if(s.charAt(l-j-1) == '1'){
					res[i][j] = 1;
				}
			}
		}
		return res;
	}
	
	public static int[][] kSubsets(int n, int k){
		int nsets = Utils.binomial(n,k);
		int [][] res = new int[nsets][n];
		String s;
		int line_pointer = 0;
		for(int i = 0; i < Math.pow(2,n); i ++){
			int counter = 0;
			s = Integer.toBinaryString(i);
			int l = s.length();
			if(l > k-1){
				for(int j = 0; j<l; j++){
					if (s.charAt(j) == '1'){
						counter ++;
					}
				}
			}
			if (counter == k){
				for (int j = 0; j < l; j++){
					if (s.charAt(l - j - 1) == '1'){
						res[line_pointer][j] = 1;
					}
				}
				line_pointer ++;	
			}
		}
		return res;
	}
}
>>>>>>> origin/master
