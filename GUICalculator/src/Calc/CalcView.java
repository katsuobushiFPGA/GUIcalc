package Calc;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcView extends JFrame{

/* 電卓のインスタンスを生成した時の初期化処理 */
	CalcView(String title){
		JPanel num = new JPanel();
		JPanel res = new JPanel();
		/* 電卓の数字ボタンを生成 */
		JButton[] btnGroup = new JButton[10];
		for(int i=0;i<btnGroup.length;i++){
			btnGroup[i] = new JButton("ボタン");
		}
		/* アイコン設定 */
	    ImageIcon icon = new ImageIcon("G:\\download\\icon.png");
	    setIconImage(icon.getImage());

	    /* レイアウトを設定する */
	    Container contentPane = getContentPane();
		contentPane.add(btnGroup[0],BorderLayout.NORTH);
		contentPane.add(btnGroup[0],BorderLayout.NORTH);
		contentPane.add(btnGroup[0],BorderLayout.NORTH);
	}
}
