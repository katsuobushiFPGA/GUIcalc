package Calc;

import javax.swing.JLabel;

//ユーザー入力系
public interface Controller {
	void buttonClickListener();
	void textFieldRewriter(JLabel t,String text);

}
