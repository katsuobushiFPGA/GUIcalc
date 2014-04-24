package Calc;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcView extends JFrame{

/* 電卓のインスタンスを生成した時の初期化処理 */
	CalcView(String title){
		JPanel num = new JPanel();
		JPanel res = new JPanel();
		GridLayout number = new GridLayout(3,3);

		/* 電卓の数字ボタンを生成 */
		JButton[] btnGroup = new JButton[10];
		for(int i=1;i<btnGroup.length;i++){
			btnGroup[i-1] = new JButton("");
		}
		/* アイコン設定 */
	    ImageIcon icon = new ImageIcon("G:\\download\\icon.png");
	    setIconImage(icon.getImage());

	    /* レイアウトを設定する */
	    num.setLayout(number);
	    Container contentPane = getContentPane();
	    contentPane.add(num);

	    num.add(btnGroup[0]);
	    num.add(btnGroup[1]);
	    num.add(btnGroup[2]);
	    num.add(btnGroup[3]);
	    num.add(btnGroup[4]);
	    num.add(btnGroup[5]);
	    num.add(btnGroup[6]);
	    num.add(btnGroup[7]);
	    num.add(btnGroup[8]);

	}
}
