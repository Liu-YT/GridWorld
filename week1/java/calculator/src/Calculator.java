import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame {
	private String operator = "";
	private JPanel panel;
	private JTextField num1, num2, ans, operating;

	public Calculator() {
		this.setBounds(300, 200, 400, 300); //size of the window
		this.setTitle("Simple Calculator"); //name of the window
		this.setLayout(new BorderLayout()); //set the layout

		num1 = new JTextField("12");
		num2 = new JTextField("6");
		ans = new JTextField();
		operating = new JTextField();

		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 5));
	}

	public void fun() {
		//first line
		panel.add(num1);
		panel.add(operating);
		panel.add(num2);
		panel.add(useButton("="));
		panel.add(ans);

		//second line
		panel.add(useButton("+"));
		panel.add(useButton("-"));
		panel.add(useButton("*"));
		panel.add(useButton("/"));
		panel.add(useButton("OK"));

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton useButton(final String key) {
		JButton button = new JButton(key);
		button.setFont(new Font("SimSun",1,25));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JButton buttonT = (JButton) event.getSource();
				String keyT = buttonT.getText();
				action(keyT);
			}
		});
		return button;
	}



	private void action(String key) {
		switch(key) {
			case "+":
				operator = "+";
				operating.setText("+");
				break;
			case "-":
				operator = "-";
				operating.setText("-");
				break;
			case "*":
				operator = "*";
				operating.setText("*");
				break;
			case "/":
				operator = "/";
				operating.setText("/");
				break;
			case "=":
				calculate();
				break;
			default:
				calculate();
				break;
		}
	}

	//judge wheather a num
	public Boolean isNumber(String num) {
		for(int i = 0; i < num.length(); ++i) {
			if(num.charAt(i) < '0' || num.charAt(i) > '9'){
				return false;
			}
		}
		return true;
	}

	public void cal(int numberFirst, int numberSecond) {
		double answer = 0;
		switch(operator) {
					case "+":
						answer = 1.0*numberFirst+numberSecond;
						break;
					case "-":
						answer = 1.0*numberFirst-numberSecond;
						break;
					case "*":
						answer = 1.0*numberFirst*numberSecond;
						break;
					case "/":
						answer = 1.0*numberFirst/numberSecond;
						break;
					default:
						break;
				}
		ans.setText(String.valueOf(answer));
	}

	public void calculate() {
		//judge wheather can be calculated
		//set the invalid num be null
		String a = num1.getText();
		String b = num2.getText();
		if(!a.equals("") && !b.equals("")){
			if(!operator.equals("") && isNumber(num1.getText()) && isNumber(num2.getText())) {
				int numberFirst = Integer.parseInt(num1.getText());
				int numberSecond = Integer.parseInt(num2.getText());
				cal(numberFirst, numberSecond);
			}
		}
		else if(!isNumber(num1.getText())) {
			//set the invalid num be null
			num1.setText("");
		}
		else if(!isNumber(num2.getText())){
			//set the invalid num be null
			num2.setText("");
		}
	}

	//主函数
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calculate = new Calculator();
		calculate.fun();
	}
}