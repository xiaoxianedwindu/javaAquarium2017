/*
 * HW6_104403016 資管3A 杜孝顯
 */
package newAq;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Turtle extends JLabel implements Runnable{
	private int x, y, lr, lrRand, lrSpeed, dropSpeed, dropFlag;
	private Random r = new Random();
	private boolean lrFlag = true/*  dropFlag = false	*/;
	private int size = r.nextInt(50) + 60;
	private ImageIcon[] oldImgIcon = {new ImageIcon(getClass().getResource("w2.png")), new ImageIcon(getClass().getResource("w.png"))};
	//original ImageIcon -> image
	private Image img = oldImgIcon[0].getImage();
	private Image img1 = oldImgIcon[1].getImage();
	//image scaling
	private Image image = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image image1 = img1.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	//back into 2d array
	private ImageIcon[] imgIcon = {new ImageIcon(image), new ImageIcon(image1)};
	private Timer t1;
	
	public Turtle(int x, int y){
		this.x = x;
		this.y = y;
		size = r.nextInt(100) + 50;
		lr = r.nextInt(2);	//0 for left, 1 for right
		lrRand = r.nextInt(690);
		lrSpeed = r.nextInt(10) + 1;
		dropSpeed = r.nextInt(10) + 1;
		this.dropFlag = 0;
		if(lr == 1){
			this.lrFlag = false;
		}
		this.setIcon(imgIcon[lr]);
		this.setBounds(x, y, this.getIcon().getIconWidth(),this.getIcon().getIconHeight());
		//make sure turtle doesn't spawn outside
		if(x + Turtle.this.getIcon().getIconWidth() > 690){
			this.x = x - Turtle.this.getIcon().getIconWidth();
		}
	}

	@Override
	public void run() {
		t1 = new Timer(r.nextInt(25) + 50, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){			
				
				if (dropFlag == 0){
					if((y + Turtle.this.getIcon().getIconHeight()) < 580){
						y = y + dropSpeed;
						Turtle.this.setLocation(x, y);
					} else {
						y = 590 - Turtle.this.getIcon().getIconHeight();
						dropFlag = 1;
					}
				} else {
					if(Turtle.this.lrFlag){
						if (x - 10 < 0 || x == lrRand){
							Turtle.this.lrFlag = !Turtle.this.lrFlag;
							lr = 1;
							Turtle.this.setIcon(imgIcon[lr]);
							x = x + lrSpeed;
							lrRand = r.nextInt(690) + 1;
							lrSpeed = r.nextInt(10) + 1;
						}
						x = x - lrSpeed;
						Turtle.this.setLocation(x, y);
					} else {
						if ((x + Turtle.this.getIcon().getIconWidth() + 10)> 690 || x == lrRand){
							Turtle.this.lrFlag = !Turtle.this.lrFlag;
							lr = 0;
							Turtle.this.setIcon(imgIcon[lr]);
							x = x - lrSpeed;
							lrRand = r.nextInt(690) + 1;
							lrSpeed = r.nextInt(10) + 1;
						}
						x = x + lrSpeed;
						Turtle.this.setLocation(x, y);
					}
				}
			}
		});
		t1.start();
	}
}
