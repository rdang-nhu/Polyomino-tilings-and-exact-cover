

public class ColumnObject extends DataObject{
	public int S;
	public String N;
	
	public ColumnObject(DataObject U, DataObject D, DataObject L, DataObject R, ColumnObject C, int size, String name){
		super(U, D, L, R, C);
		this.S = size;
		this.N = name;
	}
	
	
	
}
