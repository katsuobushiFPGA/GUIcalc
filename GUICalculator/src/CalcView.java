import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CalcView extends JFrame{
	/* keys */
	String[] keys = {"0","1","2","3","4","5","6","7","8","9","+","-","*","/","=",".","BS","(",")","^","C"};
	/* main,key,result,clearのPanel,Layoutの生成 */
	JPanel mainPanel = new JPanel();
	JPanel keyPanel = new JPanel();
	JPanel resPanel = new JPanel();
	JPanel clearPanel = new JPanel();

	/* keyPanel-電卓の数字ボタンを生成 */
	JButton[] Jkeys = new JButton[keys.length];

	/* resPanel-過程・結果の表示部分を生成 */
	JLabel processLabel = new JLabel(" ");
	JLabel resultLabel = new JLabel(" ");

	/* 電卓のインスタンスを生成した時の初期化処理 */
	public CalcView(String title,int width,int height){
		/* 生成時、終了時の処理 */
		setSize(width,height);
		setTitle(title);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for(int i=0;i < Jkeys.length;i++){
			Jkeys[i] = new JButton(keys[i]);
			Jkeys[i].setFont(new Font("MS ゴシック",Font.BOLD,40));
		}
		//Font
		processLabel.setFont(new Font("MS ゴシック",Font.BOLD,40));
		resultLabel.setFont(new Font("MS ゴシック",Font.BOLD,40));

		/* resPanel-右づめにする。*/
		processLabel.setHorizontalAlignment(JLabel.RIGHT);
		resultLabel.setHorizontalAlignment(JLabel.RIGHT);

		/* レイアウト関係 */
		/* contentPaneを設定 */
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);

		/* レイアウトを設定する */
		mainPanel.setLayout(new BorderLayout());
		keyPanel.setLayout(new GridLayout(5,4));
		resPanel.setLayout(new GridLayout(2,2));
		clearPanel.setLayout(new GridLayout(1,1));

		/* mainPanel-resultPanelとkeyPanelを設定する */
		mainPanel.add(resPanel,BorderLayout.NORTH);
		mainPanel.add(keyPanel,BorderLayout.CENTER);
		mainPanel.add(clearPanel,BorderLayout.SOUTH);

		/* 貼り付け */
		/* resPanel-resLabelをresPanelに貼り付ける。 */
		resPanel.add(processLabel);
		resPanel.add(resultLabel);

		/* keyPanel-keyをkeyPanelに貼り付ける */
		CalcView.addButton(keyPanel, Jkeys);

		/* clearPanel-clearをclearPanelに貼り付ける*/
		clearPanel.add(Jkeys[20]);

		/* イベントの登録 */
		Jkeys[0].addActionListener(e -> processLabel.setText(processLabel.getText() + "0"));
		Jkeys[1].addActionListener(e -> processLabel.setText(processLabel.getText() + "1"));
		Jkeys[2].addActionListener(e -> processLabel.setText(processLabel.getText() + "2"));
		Jkeys[3].addActionListener(e -> processLabel.setText(processLabel.getText() + "3"));
		Jkeys[4].addActionListener(e -> processLabel.setText(processLabel.getText() + "4"));
		Jkeys[5].addActionListener(e -> processLabel.setText(processLabel.getText() + "5"));
		Jkeys[6].addActionListener(e -> processLabel.setText(processLabel.getText() + "6"));
		Jkeys[7].addActionListener(e -> processLabel.setText(processLabel.getText() + "7"));
		Jkeys[8].addActionListener(e -> processLabel.setText(processLabel.getText() + "8"));
		Jkeys[9].addActionListener(e -> processLabel.setText(processLabel.getText() + "9"));

		Jkeys[10].addActionListener(e -> {
			if(resultLabel != null){
				processLabel.setText(resultLabel.getText() + processLabel.getText() + "+");
				resultLabel.setText("");
			}else{
				processLabel.setText(processLabel.getText() + "+");
			}
		});
		Jkeys[11].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "-");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "-");
				}
		});
		Jkeys[12].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "*");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "*");
				}
		});
		Jkeys[13].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "/");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "/");
				}
		});
		Jkeys[14].addActionListener(e -> {

				processLabel.setText(processLabel.getText() + "=");

				InputStream bais = null;
				try {
				 bais = new ByteArrayInputStream(processLabel.getText().getBytes("utf-8"));
				} catch (IOException eio) {
					JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました。\nクリアします。");
					processLabel.setText(" ");
					resultLabel.setText(" ");
				}
				final Reader r = new InputStreamReader(bais);
				final Parser parser = new Parser(r);

				try {
					BigDecimal result;
					result = parser.start();
					resultLabel.setText(String.valueOf(result));
					processLabel.setText(" ");
				}catch (ParseException ep) {
					JOptionPane.showMessageDialog(null, "不正な入力です。\nクリアします。");
					processLabel.setText(" ");
					resultLabel.setText(" ");
				}catch(NumberFormatException en){
					JOptionPane.showMessageDialog(null, "不正な入力です。\nクリアします。");
					processLabel.setText(" ");
					resultLabel.setText(" ");
				}catch(ArithmeticException ea){
					JOptionPane.showMessageDialog(null, "不正な入力です。\nクリアします。");
					processLabel.setText(" ");
					resultLabel.setText(" ");
				}
		});
		Jkeys[15].addActionListener(e -> {
				processLabel.setText(processLabel.getText() + ".");
		});

		Jkeys[16].addActionListener(e -> {
			if(processLabel.getText().length() != 0){
				processLabel.setText(processLabel.getText().substring(0,processLabel.getText().length()-1));
				if(processLabel.getText().length() == 0)
					processLabel.setText(" ");
			}else{
				processLabel.setText(" ");
			}
		});
		Jkeys[17].addActionListener(e -> processLabel.setText(processLabel.getText() + "("));
		Jkeys[18].addActionListener(e -> processLabel.setText(processLabel.getText() + ")"));
		Jkeys[19].addActionListener(e ->{
			if(resultLabel != null){
				processLabel.setText(resultLabel.getText() + processLabel.getText() + "^");
				resultLabel.setText("");
			}else{
				processLabel.setText(processLabel.getText() + "^");
			}
		});
		Jkeys[20].addActionListener(e -> {
			processLabel.setText(" ");
			resultLabel.setText(" ");
		});
	}
	public static void addButton(JPanel panel,JButton[] button){
		panel.add(button[16]);
		panel.add(button[17]);
		panel.add(button[18]);
		panel.add(button[19]);
		panel.add(button[7]);
		panel.add(button[8]);
		panel.add(button[9]);
		panel.add(button[10]);
		panel.add(button[4]);
		panel.add(button[5]);
		panel.add(button[6]);
		panel.add(button[11]);
		panel.add(button[1]);
		panel.add(button[2]);
		panel.add(button[3]);
		panel.add(button[12]);
		panel.add(button[0]);
		panel.add(button[15]);
		panel.add(button[13]);
		panel.add(button[14]);
	}
}