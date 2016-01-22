import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;


class Main {
	
	public static void main(String[] args) {
		
		Window window = new Window();
		window.create();
		
	}
	
}

class Window extends JFrame {
	
	private Button btn1 = new Button("Sans Serif", new Font(Font.SANS_SERIF, Font.PLAIN, 12));
	private Button btn2 = new Button("Serif", new Font(Font.SERIF, Font.PLAIN, 12));
	private Button btn3 = new Button("Monospaced", new Font(Font.MONOSPACED, Font.PLAIN, 12));
	private Button btn4 = new Button("Dialog", new Font(Font.DIALOG, Font.PLAIN, 12));
	
	private JLabel label = new JLabel("Dette er en tekst.");
	
	private JPanel pane1 = new JPanel();
	private JPanel pane2 = new JPanel();
	
	public void create() {
		
		setTitle("Fonter");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Legg innhold i paneler
		pane1.add(btn1);
		pane1.add(btn2);
		pane1.add(btn3);
		pane1.add(btn4);
		pane2.add(label);
		
		// Legg till paneler i vindu
		add(pane1, BorderLayout.NORTH);
		add(pane2, BorderLayout.SOUTH);
		
		// legger til en knappelytter og kobler denne til knappene
		BtnAction btnAction = new BtnAction();
		btn1.addActionListener(btnAction);
		btn2.addActionListener(btnAction);
		btn3.addActionListener(btnAction);
		btn4.addActionListener(btnAction);
		
		pack();
		setLocationRelativeTo(null);
		setSize(500, 100);
		setVisible(true);
	}
	
	private class BtnAction implements ActionListener {
		public void actionPerformed(ActionEvent action) {
			// Finner hvilken knapp som ble trykket på
			JButton btnPressed = (JButton) action.getSource();
			double rate = 0.03;
		 	// Åpner riktig vindu
		 	if (btnPressed.equals(btn1)) {
				label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		 	} else if (btnPressed.equals(btn2)) {
				label.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
		 	} else if (btnPressed.equals(btn3)) {
				label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		 	} else if (btnPressed.equals(btn4)) {
				label.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		 	}
		}
	}
	
}

class Button extends JButton {
	
	public Button(String text, Font font) {
		setText(text);
		setFont(font);
	}
}