
public class Square{
	private int x,y;
	public Square(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void squareX(int x){
		this.x = x;
	}
	public void squareY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean isEqual(Square s){
		return x == s.x && y == s.y;
	}
	public boolean isStrictlySuperior(Square s){
		return y > s.y || ((y == s.y) && x > s.x);
	}
	public Square copy(){
		Square res = new Square(x,y);
		return res;
	}
}
