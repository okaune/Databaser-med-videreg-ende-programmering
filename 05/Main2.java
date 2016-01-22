import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;

class Main2 {
	
	public static void main(String[] args) {
		
		Window window = new Window();
		window.create();
		
	}
	
}

class Window extends JFrame {
	
	private JButton btn1 = new JButton("Til svensk.");
	private JButton btn2 = new JButton("Til norsk.");
	private JTextField input = new JTextField("", 20);
	private JLabel label2 = new JLabel("Resultatet av omregningen kommer her.");
	
	private JPanel pane1 = new JPanel();
	private JPanel pane2 = new JPanel();
	private JPanel pane3 = new JPanel();
	
	public void create() {
		
		setTitle("Valutakalkulator");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Pane 1
		JLabel label1 = new JLabel("Beløp:");
		label1.setFont(new Font("Arial", Font.BOLD, 14));
		label1.setForeground(Color.BLACK);
		pane1.add(label1);
		pane1.add(input);
		
		// Pane 2
		pane2.add(label2);
		
		// Pane 3
		pane3.add(btn1);
		pane3.add(btn2);
		
		// Legg til i vinduet
		add(pane1, BorderLayout.NORTH);
		add(pane2, BorderLayout.CENTER);
		add(pane3, BorderLayout.SOUTH);
		
		// legger til en knappelytter og kobler denne til knappene
		BtnAction btnAction = new BtnAction();
		btn1.addActionListener(btnAction);
		btn2.addActionListener(btnAction);
		
		pack();
		setLocationRelativeTo(null);
		setSize(500, 200);
		setVisible(true);
	}
	
	// What happens when a button is pressed?
	private class BtnAction implements ActionListener {
		
		public void actionPerformed(ActionEvent action) {
			
			// Finner hvilken knapp som ble trykket på
			JButton btnPressed = (JButton) action.getSource();
			
			// Forskjellen i valutaene 1 NOK = 0,97 SEK, 1 SEK = 1,03 NOK
			double rate = 0.03;
			
		 	// Gjør riktig utregning basert på hvilken knapp som er trykket
		 	if (btnPressed.equals(btn1)) {
				
				try {
					label2.setText(String.valueOf(Double.parseDouble(input.getText()) * (1 + rate)));
				} catch (Exception e) { // Vil slå inn for NaN og null
					System.out.println(e); // Debugginformasjon
					label2.setText("Noe er galt med det du skrev inn. Prøv igjen."); // Feilmelding til brukeren - burde egentlig håndteres et annet sted
				}
				
		 	} else if (btnPressed.equals(btn2)) {
				
				try {
					label2.setText(String.valueOf(Double.parseDouble(input.getText()) * (1 - rate)));
				} catch (Exception e) { // Vil slå inn for NaN og null
					System.out.println(e); // Debugginformasjon
					label2.setText("Noe er galt med det du skrev inn. Prøv igjen."); // Feilmelding til brukeren - burde egentlig håndteres et annet sted
				}
				
		 	}
		}
	}
	
}