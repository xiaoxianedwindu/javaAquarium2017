/*
 * HW6_104403016 資管3A 杜孝顯
 */
package newAq;

import javax.swing.JFrame;

public class AquariumTest {

	public static void main(String[] args) {
		
		Aquarium aquarium = new Aquarium();
		aquarium.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aquarium.setSize(700, 700); 
		aquarium.setVisible(true);
		
	}

}
