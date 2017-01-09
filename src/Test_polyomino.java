
public class Test_polyomino {
	
	
    static void test_rectangle(int x, int y, int k){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < x; i++){
    		for(int j = 0; j < y; j++){
    			int[] a = {i,j};
    			p.cases.add(a);
    		}
    	}
		PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l, "reusable", "number");
    }
    
    static void test_diagonal(int n, int k){
    	Polyomino p = new Polyomino();
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			int[] a = {i,i/2+j};
    			p.cases.add(a);
    		}
    	}
    	
		PolyominoList l = PolyominoList.fixedPolyomino(k);
		PolyominoTilings toPrint = new PolyominoTilings(p,l, "reusable", "number");
    }
    
    static void test_pyramide(int n, int k){
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
		PolyominoTilings toPrint = new PolyominoTilings(p,l, "reusable", "print");
    }
    
    static void test_cross(int n, int k){
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
    	PolyominoTilings toPrint = new PolyominoTilings(p,l, "reusable", "print");
    }
    
    
    
    
    
    public static void main(String[] args){
		//test_rectangle(3, 5, 5);
		test_diagonal(5,5);
		//test_pyramide(6,6);
		//test_cross(6,8);
		//test3(3);
		//test2(8,4);
		//System.out.println(CoordinateStandardization(Arrays.toString(p.cases.get(0))));
		
	}
}
