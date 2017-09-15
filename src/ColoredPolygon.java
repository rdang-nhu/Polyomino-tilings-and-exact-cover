import java.awt.Polygon;
import java.awt.Color;

public class ColoredPolygon{
	Polygon polygon;
	Color color;
	ColoredPolygon(int[] xcoords, int[] ycoords, Color color){
		polygon = new Polygon(xcoords, ycoords, xcoords.length);
		this.color = color;
		
	}
}
