import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import static javax.swing.JOptionPane.*;

class Window extends JFrame {
	public static ArrayList<Currency> currencyList = new ArrayList<Currency>() {{
		add(new Currency("EUR", 8.10));
		add(new Currency("USD", 6.23));
		add(new Currency("GBP", 12.27));
		add(new Currency("SEK", 0.8896));
		add(new Currency("DKK", 1.0875));
		add(new Currency("JPY", 0.0514));
		add(new Currency("ISK", 0.0916));
		add(new Currency("NOK", 1));
	}};
	
	private DefaultListModel model = new DefaultListModel() {{
		addElement("EUR");
		addElement("USD");
		addElement("GBP");
		addElement("SEK");
		addElement("DDK");
		addElement("JPY");
		addElement("ISK");
		addElement("NOK");
	}};
	
	private JList listFrom = new JList(model);
	private JList listTo = new JList(model);
	
	public Window(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new TextPanel(), BorderLayout.NORTH);
		add(new ListPanelFrom(), BorderLayout.WEST);
		add(new ListPanelTo(), BorderLayout.EAST);
		add(new Button("Ny valuta"), BorderLayout.SOUTH);
		pack();
	}
	
	private class TextPanel extends JPanel {
		public TextPanel() {
			setLayout(new BorderLayout());
			add(new JLabel("Velg fravaluta og tilvaluta fra listene under."), BorderLayout.NORTH);
			add(new JLabel("Fra valuta:"), BorderLayout.WEST);
			add(new JLabel("Til valuta:"), BorderLayout.EAST);
		}
	}
	
	private class ListPanelFrom extends JPanel {
		public ListPanelFrom() {
			setLayout(new BorderLayout());
			//model.addElement("Nytt navn");
			listFrom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPaneFrom = new JScrollPane(listFrom);
			add(scrollPaneFrom);
			listFrom.addListSelectionListener(new ListListener());
		}
		
		public Dimension getPreferredSize(){
			return new Dimension(150,150);
		}
	}
	
	private class ListPanelTo extends JPanel {
		public ListPanelTo() {
			setLayout(new BorderLayout());
			//model.addElement("Nytt navn");
			listTo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPaneTo = new JScrollPane(listTo);
			add(scrollPaneTo);
			listTo.addListSelectionListener(new ListListener());
		}
		
		public Dimension getPreferredSize(){
			return new Dimension(150,150);
		}
	}
	
	private class ListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			int fromVal = listFrom.getSelectedIndex();
			int toVal = listTo.getSelectedIndex();
			if (fromVal >= 0 && toVal >= 0) {
				listFrom.clearSelection();
				listTo.clearSelection();
				double amt = 0;
				try {
					amt = Double.parseDouble(showInputDialog("Oppgi beløp:"));
				} catch (Exception e) {
					System.out.println("Innskrevet verdi ikke double.");
				}
				// En metode som bruker amt og fromVal og toVal til å regne ut resultat
				try {
					double result = Currency.convert(fromVal, toVal, amt);
					showMessageDialog(null, String.format("%.2f", amt) + " " + currencyList.get(fromVal).getName() + " = " + String.format("%.2f", result) + " " + currencyList.get(toVal).getName());
				} catch (IllegalArgumentException iae) {
					System.out.println(iae);
				} catch (Exception e) {
					System.out.println("Noe gikk galt! \n" + e);
				}
				
			}
		}
	}
	
	private class Button extends JButton {
		public Button(String text) {
			setText(text);
			addActionListener(new ButtonListener());
		}
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//System.out.println(event.getSource()); // debug
			String newCurr = showInputDialog("ISO-kode for valuta:");
			if (newCurr != null) {
				String newVal = showInputDialog("1 " + newCurr + " = # NOK?");
				double val = 0;
				try {
					val = Double.parseDouble(newVal);
					Currency currency = new Currency(newCurr, val);
					currencyList.add(currency);
					model.addElement(newCurr);
				} catch (Exception e) {
					System.out.println("Innskrevet verdi ikke double.");
				}
			}
		}
	}
	
}

class Currency {
	private String name;
	private double value;
	
	public Currency(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	// Brukes ikke for øyeblikket
	public void setValue(double value) {
		this.value = value;
	}
	
	public static double convert(int fromIndex, int toIndex, double amt) {
		Currency from = Window.currencyList.get(fromIndex);
		Currency to = Window.currencyList.get(toIndex);
		if (from == null || to == null) {
			throw new IllegalArgumentException("Må skrive inn en verdi.");
		}
		return amt * from.getValue() / to.getValue();
	}
}

class Main {
	public static void main(String[] args) {
		Window window = new Window("Valutaberegner");
		window.setVisible(true);
	}
}