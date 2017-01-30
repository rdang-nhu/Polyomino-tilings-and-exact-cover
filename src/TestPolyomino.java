import java.util.ArrayList;

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
    
    
    public static void test_dilate(int n, int k ){
    	PolyominoList toTest = PolyominoList.fixedPolyomino(n);
    	int count = 0;
    	//ArrayList<PolyominoList> toPrint = new ArrayList<PolyominoList>();
    	PolyominoTilings toPrint1 = new PolyominoTilings();
    	for (int i = 0; i < toTest.size(); i++){
    		Polyomino toTile = toTest.get(i);
    		Polyomino tile0 = Polyomino.clone(toTest.get(i));
    		Polyomino tile1 = Polyomino.clone(tile0);
    		Polyomino tile2 = Polyomino.clone(tile0);
    		Polyomino tile3 = Polyomino.clone(tile0);
    		tile1.rotation();tile2.rotation();tile2.rotation();tile3.rotation();tile3.rotation();tile3.rotation();tile3.rotation();
    		toTile.dilatation(k);
    		PolyominoList toTest_l = new PolyominoList();
    		toTest_l.add(tile0);toTest_l.add(tile1);toTest_l.add(tile2);toTest_l.add(tile3);
    		PolyominoTilings res = new PolyominoTilings(toTile,toTest_l, "reusable", "return_one");
//    		if(res.counter > 0){
//    			count ++;
//    		}
    		if(!res.isEmpty()){
    			toPrint1.add(res.get(0));
    		}
    	}
    	//System.out.println(count);
    	toPrint1.register("register.txt");
    	toPrint1.draw(10,10);
    	
    }
    
    
    
    
    public static void main(String[] args){
		//test_rectangle(4, 4, 4, "reusable", "print_one");
		test_diagonal(5,5, "reusable", "print_one");
		//test_pyramide(6,6, "reusable", "number");
		//test_cross(6,8, "reusable", "number");
		test_dilate(4,4);
//		PolyominoList resultat = PolyominoList.fixedPolyomino(4);
//		resultat.draw(10, 10);
//		Polyomino p0 = resultat.get(2);
//		Polyomino toTile = resultat.get(0);
//		Polyomino toTest = Polyomino.copy(resultat.get(0));
//		resultat.clear();
//		resultat.add(toTest);
////		toTile.dilatation(4);
//		resultat.clear();
//		PolyominoList resultat = new PolyominoList();
//		Polyomino p0 = new Polyomino("[(0,0),(0,1),(0,2),(2,0),(2,1),(2,2),(1,2),(3,2)]/n");
//		Polyomino p1 = Polyomino.clone(p0);
//		Polyomino p2 = Polyomino.clone(p0);
//		Polyomino p3 = Polyomino.clone(p0);
//		p1.rotation(); p2.rotation(); p2.rotation(); p3.rotation(); p3.rotation(); p3.rotation();
//		Polyomino toTile = Polyomino.clone(p0);
//		resultat.clear();
//		resultat.add(p0);resultat.add(p1);resultat.add(p2);resultat.add(p3);
//		toTile.dilatation(4);
//		PolyominoTilings toPrint = new PolyominoTilings(toTile, resultat, "reusable", "print_one");
		//toPrint.print();
	}
}
