package gret;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import myMath.Polynom;
/**
 * This class will present a polynomial in a visual form. 
 * also be able to find the extreme points of the function , and paint them.
 * We used garal open source so,
 * Credit to garl http://trac.erichseifert.de/gral/wiki/Tutorials/XYPlot.
 */
public class LinePlotTest extends JFrame {
    
	private Polynom poly;
	private double from;
	private double to;
	private double eps = 0.25;
	
	public LinePlotTest(Polynom _p, double x0, double x1, double _eps) 
	{
        poly= new Polynom(_p);
        from = x0;
        to = x1;
        eps = _eps;
        
        LinkedList<Double> extreme = poly.extremaPoints(x0, x1, eps);
        if (!extreme.isEmpty()) {
        	System.out.println("Extreme points: (+-"+eps+")");
        	Iterator<Double> it = extreme.iterator();
        	while (it.hasNext()) {
        		double x = it.next();
        		System.out.println("(" + x +","+ poly.f(x) + ")");
        	}
        }
        
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);

        
        DataTable data = new DataTable(Double.class, Double.class);
        DataTable dataDer = new DataTable(Double.class, Double.class);
        for (double x = from; x <= to; x+=eps) {
        	double y = poly.f(x);
        	if (extreme.contains(x)) 
        		dataDer.add(x, y);
        	else 
        		data.add(x, y);
        }
        
        XYPlot plot = new XYPlot(data, dataDer);
        getContentPane().add(new InteractivePanel(plot));
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
        Color color = new Color(0f, 0f, 1f);
        Color colorDer = new Color(1f, 0f, 0f);
        
        
        plot.getPointRenderers(data).get(0).setColor(color);
        plot.getPointRenderers(dataDer).get(0).setColor(colorDer);
        plot.getLineRenderers(data).get(0).setColor(color);
    }
/**
 * Main funtion that enable the gui on the polynom .
 * The GUI get (start range, End rang, epsilon)
 * 
 */
    public static void main(String[] args) {
    	Polynom p = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
    	p.GUI(-2, 6, 0.01);
    }
}