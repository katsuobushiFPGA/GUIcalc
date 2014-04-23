package View;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame{
	public static void main(String args[]){
		View view = new View("テスト");
		view.setSize(400,400);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
	}
	View(String title){
		JButton[] btnGroup = new JButton[10];
		for(int i=0;i<btnGroup.length;i++){
			btnGroup[i] = new JButton("ボタン");
		}
		Container contentPane = getContentPane();
		contentPane.add(btnGroup[0],BorderLayout.NORTH);
	}
}
