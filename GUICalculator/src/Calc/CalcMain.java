package Calc;
import javax.swing.JFrame;

public class CalcMain {
	public static void main(String args[]){
		CalcView v = new CalcView("電卓");
		v.setVisible(true);
		v.setSize(400,500);
		v.setLocationRelativeTo(null);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
