

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalcView extends JFrame{

	/* main,key,result,clearのPanel,Layoutの生成 */
	JPanel mainPanel = new JPanel();
	JPanel keyPanel = new JPanel();
	JPanel resPanel = new JPanel();
	JPanel clearPanel = new JPanel();

	BorderLayout mainLayout = new BorderLayout();

	GridLayout keyLayout = new GridLayout(5,4);
	GridLayout resLayout = new GridLayout(2,2);
	GridLayout clearLayout = new GridLayout(1,1);

	/* keyPanel-電卓の数字ボタンを生成 */
	JButton[][] btnGroup = new JButton[5][4];
	JButton clear = new JButton("C");

	/* resPanel-過程・結果の表示部分を生成 */
	JLabel processLabel = new JLabel("");
	JLabel resultLabel = new JLabel("");

	/* */
	Reader reader = null;
	/* 電卓のインスタンスを生成した時の初期化処理 */
	public CalcView(String title,int width,int height){
		/* 生成時、終了時の処理 */
		setSize(width,height);
		setTitle(title);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* resPanel-過程・結果の表示部分を生成 */
		processLabel.setFont(new Font("MS ゴシック",Font.BOLD,40));
		resultLabel.setFont(new Font("MS ゴシック",Font.BOLD,40));

		/* keyの登録 */
		btnGroup[0][0] = new JButton("7");
		btnGroup[0][1] = new JButton("8");
		btnGroup[0][2] = new JButton("9");
		btnGroup[0][3] = new JButton("÷");
		btnGroup[1][0] = new JButton("4");
		btnGroup[1][1] = new JButton("5");
		btnGroup[1][2] = new JButton("6");
		btnGroup[1][3] = new JButton("×");
		btnGroup[2][0] = new JButton("1");
		btnGroup[2][1] = new JButton("2");
		btnGroup[2][2] = new JButton("3");
		btnGroup[2][3] = new JButton("-");
		btnGroup[3][0] = new JButton(".");
		btnGroup[3][1] = new JButton("0");
		btnGroup[3][2] = new JButton("+");
		btnGroup[3][3] = new JButton("=");
		btnGroup[4][0] = new JButton("(");
		btnGroup[4][1] = new JButton(")");
		btnGroup[4][2] = new JButton("");
		btnGroup[4][3] = new JButton("");


		/* keyPanel-文字の大きさを設定する。 */
		for(int i=0;i < 5;i++){
			for(int j=0;j<4;j++){
				btnGroup[i][j].setFont(new Font("MS ゴシック",Font.BOLD,40));
			}
		}
		clear.setFont(new Font("MS ゴシック",Font.BOLD,40));

		/* resPanel -表示する文字を設定する */
		processLabel.setText("");
		resultLabel.setText(" ");

		/* resPanel-右づめにする。*/
		processLabel.setHorizontalAlignment(JLabel.RIGHT);
		resultLabel.setHorizontalAlignment(JLabel.RIGHT);

		/* アイコン設定 */
		ImageIcon icon = new ImageIcon("G:\\download\\icon.png");
		setIconImage(icon.getImage());

		/* レイアウトを設定する */
		mainPanel.setLayout(mainLayout);
		keyPanel.setLayout(keyLayout);
		resPanel.setLayout(resLayout);
		clearPanel.setLayout(clearLayout);

		/* contentPaneを設定 */
		Container contentPane = getContentPane();
		contentPane.add(mainPanel);

		/* mainPanel-resultPanelとkeyPanelを設定する */
		mainPanel.add(resPanel,BorderLayout.NORTH);
		mainPanel.add(keyPanel,BorderLayout.CENTER);
		mainPanel.add(clearPanel,BorderLayout.SOUTH);

		/* Panel-背景色変更*/
		mainPanel.setBackground(Color.CYAN);
		resPanel.setBackground(Color.LIGHT_GRAY);

		/* resPanel-resLabelをresPanelに貼り付ける。 */
		resPanel.add(processLabel);
		resPanel.add(resultLabel);


		/* keyPanel-keyをkeyPanelに貼り付ける */
		for(int i=0;i < 5;i++){
			for(int j=0;j < 4;j++){
				keyPanel.add(btnGroup[i][j]);
			}
		}

		/* clearPanel-clearをclearPanelに貼り付ける*/
		clearPanel.add(clear);

		/* イベントの登録 */

		/* 数字群 */
		btnGroup[2][0].addActionListener(e -> processLabel.setText(processLabel.getText() + "1"));
		btnGroup[2][1].addActionListener(e -> processLabel.setText(processLabel.getText() + "2"));
		btnGroup[2][2].addActionListener(e -> processLabel.setText(processLabel.getText() + "3"));
		btnGroup[1][0].addActionListener(e -> processLabel.setText(processLabel.getText() + "4"));
		btnGroup[1][1].addActionListener(e -> processLabel.setText(processLabel.getText() + "5"));
		btnGroup[1][2].addActionListener(e -> processLabel.setText(processLabel.getText() + "6"));
		btnGroup[0][0].addActionListener(e -> processLabel.setText(processLabel.getText() + "7"));
		btnGroup[0][1].addActionListener(e -> processLabel.setText(processLabel.getText() + "8"));
		btnGroup[0][2].addActionListener(e -> processLabel.setText(processLabel.getText() + "9"));
		btnGroup[3][1].addActionListener(e -> processLabel.setText(processLabel.getText() + "0"));

		/* 数の次にくること */
		btnGroup[3][0].addActionListener(e -> processLabel.setText(processLabel.getText() + "."));

		/* 記号は連続して入力できないようにする */
		btnGroup[3][2].addActionListener(e -> {
			if(resultLabel != null){
				processLabel.setText(resultLabel.getText() + processLabel.getText() + "+");
				resultLabel.setText("");
			}else{
				processLabel.setText(processLabel.getText() + "+");
			}
		});
		btnGroup[2][3].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "-");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "-");
				}
		});
		btnGroup[1][3].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "*");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "*");
				}
		});
		btnGroup[0][3].addActionListener(e -> {
				if(resultLabel != null){
					processLabel.setText(resultLabel.getText() + processLabel.getText() + "/");
					resultLabel.setText("");
				}else{
					processLabel.setText(processLabel.getText() + "/");
				}
		});
		/* = resultLabelに結果を表示させ、プロセスラベルをクリアする*/
		//btnGroup[3][3].addActionListener(e -> processLabel.setText(processLabel.getText() + "="));

		/* パーサに掛けて結果を表示する イベント*/
		/* エラー処理もそこでやる、パターンを考える。*/
		btnGroup[3][3].addActionListener(e -> {

				processLabel.setText(processLabel.getText() + "=");
			try {
				InputStream bais = new ByteArrayInputStream(processLabel.getText().getBytes("utf-8"));
				final Reader r = new InputStreamReader(bais);
				final Parser parser = new Parser(r);
				final double tree = parser.start();
				resultLabel.setText(String.valueOf(tree));
				processLabel.setText(" ");
			} catch (IOException e1) {
		//		JOptionPane.showMessageDialog(null, "処理中にエラーが発生しました。\nクリアします。");
				  processLabel.setText(" ");
				  resultLabel.setText(" ");
			}catch(ParseException ep){
				processLabel.setText(" ");
			    resultLabel.setText(" ");
			}
		});

		/* カッコは数字の前、数字の後にくる。符号の前には開始カッコうてない */
		btnGroup[4][0].addActionListener(e -> processLabel.setText(processLabel.getText() + "("));
		btnGroup[4][1].addActionListener(e -> processLabel.setText(processLabel.getText() + ")"));

		/* result,process共に消す */
		clear.addActionListener(e -> processLabel.setText(" "));
		clear.addActionListener(e -> resultLabel.setText(" "));

	}
	public JLabel getLabel(){
		return processLabel;
	}

}