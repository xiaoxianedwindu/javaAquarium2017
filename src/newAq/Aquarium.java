/*
 * HW6_104403016 資管3A 杜孝顯
 */
package newAq;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Aquarium extends JFrame{
	
	private JPanel water = new JPanel();
	private Color waterColor = new Color(0, 255, 255);	//color cyan
	private JButton newFish = new JButton("new fish");
	private JButton newTurtle = new JButton("new turtle");
	private JButton removeSelected = new JButton("remove selected");
	private JButton removeAll = new JButton ("remove all");
	private JLabel functionLabel = new JLabel("Ready to Party");
	private JLabel statusLabel= new JLabel("FISH Count and Turtle COUNT HERE");
	private ArrayList<Fish> fishList = new ArrayList<Fish>();
	private ArrayList<Thread> threadList = new ArrayList<Thread>();
	private ArrayList<Turtle> turtleList = new ArrayList<Turtle>();
	private int x, y, function;
	private String fishCount = "Fish Count: ",turtleCount = "Turtle Count: ";
	/*
	 * function code
	 * 1 is new fish
	 * 2 is new turtle
	 * 3 is remove selected
	 */
	
	public Aquarium(){
		
		super("Fish and Turtle Party");
		water.setLayout(null);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		
		GridBagConstraints btn1 = new GridBagConstraints();
		btn1.gridx = 0;
		btn1.gridy = 0;
		btn1.gridwidth = 1;
		btn1.gridheight = 1;
		btn1.weightx = 0.5;
		btn1.fill = GridBagConstraints.HORIZONTAL;					//button height will not be changed
		btn1.anchor = GridBagConstraints.NORTH;
		add(newFish,btn1);
		
		GridBagConstraints btn2 = new GridBagConstraints();
		btn2.gridx = 1;
		btn2.gridy = 0;
		btn2.gridwidth = 1;
		btn2.gridheight = 1;
		btn2.weightx = 0.4;
		btn2.fill = GridBagConstraints.HORIZONTAL;
		btn2.anchor = GridBagConstraints.NORTH;
		add(removeSelected,btn2);
		
		GridBagConstraints btn3 = new GridBagConstraints();
		btn3.gridx = 0;
		btn3.gridy = 1;
		btn3.gridwidth = 1;
		btn3.gridheight = 1;
		btn3.weightx = 0.5;
		btn3.fill = GridBagConstraints.HORIZONTAL;
		add(newTurtle,btn3);
		
		GridBagConstraints btn4 = new GridBagConstraints();
		btn4.gridx = 1;
		btn4.gridy = 1;
		btn4.gridwidth = 1;
		btn4.gridheight = 1;
		btn4.weightx = 0.4;
		btn4.fill = GridBagConstraints.HORIZONTAL;
		add(removeAll,btn4);
		
		GridBagConstraints lab1 = new GridBagConstraints();
		lab1.gridx = 0;
		lab1.gridy = 2;
		lab1.gridwidth = 1;
		lab1.gridheight = 1;
		lab1.weightx = 0.5;
		lab1.ipady = 20;
		lab1.fill = GridBagConstraints.HORIZONTAL;
		add(functionLabel,lab1);
		functionLabel.setBackground(Color.DARK_GRAY);
		functionLabel.setForeground(Color.CYAN);
		functionLabel.setOpaque(true);
		
		GridBagConstraints lab2 = new GridBagConstraints();
		lab2.gridx = 1;
		lab2.gridy = 2;
		lab2.gridwidth = 2;
		lab2.gridheight = 1;
		lab2.weightx = 0.4;
		lab2.ipady = 20;
		lab2.fill = GridBagConstraints.HORIZONTAL;
		add(statusLabel,lab2);
		statusLabel.setBackground(Color.DARK_GRAY);
		statusLabel.setForeground(Color.CYAN);
		statusLabel.setOpaque(true);
		
		GridBagConstraints pan = new GridBagConstraints();
		pan.gridx = 0;
		pan.gridy = 3;
		pan.gridwidth = 3;
		pan.weighty = 1;
		pan.fill = GridBagConstraints.BOTH;
		add(water,pan);
		water.setBackground(waterColor);
		
		water.addMouseListener(
				new MouseAdapter(){
					@Override
					public void mousePressed(MouseEvent e){
						x = e.getX();
						y = e.getY();
						if(function == 1){
							fishList.add(new Fish(x, y));
							water.add(fishList.get(fishList.size()-1)).addMouseListener(new SelectHandler());;
							threadList.add(new Thread(fishList.get(fishList.size() - 1)));
							threadList.get(threadList.size() - 1).start();
							functionLabel.setText("Added a fish");
							printInfo();						
							
						}
						
						if(function == 2){
							turtleList.add(new Turtle(x, y));
							water.add(turtleList.get(turtleList.size() - 1)).addMouseListener(new SelectHandler());;
							threadList.add(new Thread(turtleList.get(turtleList.size() - 1)));
							threadList.get(threadList.size() - 1).start();
							functionLabel.setText("Added a turtle");
							printInfo();							
							
						}
					}
				}
				
			);
		
		newFish.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				function = 1;
				functionLabel.setText("Function: New Fish");
				statusLabel.setText("Making New Fish");			
			}			
		});
		
		newTurtle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				function = 2;
				functionLabel.setText("Function: New Turtle");
				statusLabel.setText("Making New Turtle");			
			}			
		});

		removeSelected.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				function =3;
				functionLabel.setText("Function: Remove Selected");
				statusLabel.setText("Kill a creature.");
			}
		});
		
		removeAll.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fishList.clear();
				turtleList.clear();
				functionLabel.setText("All the creatures are dead. QQ");
				printInfo();
				water.removeAll();
				water.repaint();
			}
		});
	}

	private void printInfo() {
		
		  if(turtleList.size() <= 0 && fishList.size() != 0) {
			statusLabel.setText(fishCount + fishList.size());
		} else if (fishList.size() <= 0 && turtleList.size() != 0) {
			statusLabel.setText(turtleCount + turtleList.size());
		} else {		
			statusLabel.setText(fishCount + fishList.size() + " " + turtleCount + turtleList.size());
		}

	}
	
	private class SelectHandler extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(function == 3){	//remove selected
				JLabel pressed = (JLabel)e.getSource();
				if(e.getSource() instanceof Fish){
					functionLabel.setText("GOT A FISH");
					fishList.remove(e.getSource());
					printInfo();
				}				
				if(e.getSource() instanceof Turtle) {
					functionLabel.setText("GOT A TURTLE");
					turtleList.remove(e.getSource());
					printInfo();
				}
				water.remove(pressed);
				water.repaint();
				
			}
		}
	}
	
	
}
