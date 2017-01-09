
public class TestPolyomino {
	
	
    static void test_rectangle(int x, int y, int k, String problem_type, String result_type){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < x; i++){
    		for(int j = 0; j < y; j++){
    			int[] a = {i,j};
    			p.cases.add(a);
    		}
    	}
		PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l, problem_type, result_type);
    }
    
    static void test_diagonal(int n, int k, String problem_type, String result_type){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			int[] a = {i,i/2+j};
    			p.cases.add(a);
    		}
    	}
    	
		PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l, problem_type, result_type);
    }
    
    static void test_pyramide(int n, int k, String problem_type, String result_type){
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
		PolyominoTilings toPrint = new PolyominoTilings(p,l, problem_type, result_type);
    }
    
    static void test_cross(int n, int k, String problem_type, String result_type){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			if(n/2 - i - 1 <= j && j <= n/2 + i && i < n/2){
	    			int[] a = {i,j};
	    			p.cases.add(a);
    			}
    			else if(j<= n-i+n/2 -1 && j >= Math.abs(n/2 - n + i) && i > n/2){
    				int[] a = {i,j};
	    			p.cases.add(a);
    			}
    		}
    	}
    	if (n%2 != 0){
	    	for(int j=-1; j < n;j++){
	    		int[] a = {n/2,j};
	    		p.cases.add(a);
	    	}
    	}
    	else{
    		for(int j=0; j < n;j++){
	    		int[] a = {n/2,j};
	    		p.cases.add(a);
    		}
    	}
    	PolyominoList l = PolyominoList.fixedPolyomino(k);
    	PolyominoTilings toPrint = new PolyominoTilings(p,l, problem_type, result_type);
    }
    
    
    
    
    
    public static void main(String[] args){
		test_rectangle(2, 5, 5, "reusable", "print");
		//test_diagonal(4,4, "reusable", "number");
		//test_pyramide(6,6, "reusable", "number");
		//test_cross(6,8, "reusable", "number");	
	}
}
