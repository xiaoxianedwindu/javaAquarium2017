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

public class Fish extends JLabel implements Runnable{
	private int x, y, lr, ud, fishType, lrRand, udRand, lrSpeed, udSpeed;
	private Random r = new Random();
	private int size = r.nextInt(50) + 80;
	private boolean lrFlag = true, udFlag = true;
	
	private ImageIcon[][] oldimgIcon = {
			{new ImageIcon(getClass().getResource("2.png")),new ImageIcon(getClass().getResource("4.png")),new ImageIcon(getClass().getResource("6.png"))},
			{new ImageIcon(getClass().getResource("1.png")),new ImageIcon(getClass().getResource("3.png")),new ImageIcon(getClass().getResource("5.png"))}
		};
	//ImageIcon -> Image
	private Image img00 = oldimgIcon[0][0].getImage();
	private Image img01 = oldimgIcon[0][1].getImage();
	private Image img02 = oldimgIcon[0][2].getImage();
	private Image img10 = oldimgIcon[1][0].getImage();
	private Image img11 = oldimgIcon[1][1].getImage();
	private Image img12 = oldimgIcon[1][2].getImage();
	//scaling for resizing
	private Image newImage00 = img00.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image newImage01 = img01.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image newImage02 = img02.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image newImage10 = img10.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image newImage11 = img11.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	private Image newImage12 = img12.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	//new image icon 2d array
	private ImageIcon[][] imgIcon = {
			{new ImageIcon(newImage00),new ImageIcon(newImage01),new ImageIcon(newImage02)}, //����
			{new ImageIcon(newImage10),new ImageIcon(newImage11),new ImageIcon(newImage12)} //���k
		};
	private Timer t1;
	
	public Fish(int x, int y){
		this.x = x;
		this.y = y;
		size = r.nextInt(100) + 50;
		lr = r.nextInt(2);	// 0 creature goes left, 1 it goes right
		ud = r.nextInt(2);	// 0 creature goes up, 1 it goes down
		lrRand = r.nextInt(690);
		udRand = r.nextInt(570);
		lrSpeed = r.nextInt(10) + 1;
		udSpeed = r.nextInt(10) + 1;		
		if(lr == 1){
			this.lrFlag = false;
		}
		if(ud == 1){
			this.udFlag = false;
		}

		this.setIcon(imgIcon[lr][fishType = r.nextInt(3)]);	//lr chooses orientation, randomFish selects the fish type
		this.setBounds(x,y,this.getIcon().getIconWidth(),this.getIcon().getIconHeight());
		
		//make sure the fish doesn't go out of bounds
		if(x + this.getIcon().getIconWidth() > 690){
			this.x = x - Fish.this.getIcon().getIconWidth();
		}
		if(y + this.getIcon().getIconWidth() > 570){
			this.y = y - Fish.this.getIcon().getIconHeight();
		}
		
	}
	@Override
	public void run() {
		t1 = new Timer(r.nextInt(25)+50, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				//when fish goes left and up
				if(Fish.this.lrFlag && Fish.this.udFlag){
					//when fish is going out of bounds or when random turn occurs
					if(x-10 < 0 || x == lrRand){
						Fish.this.lrFlag = !Fish.this.lrFlag;
						lr = 1;
						Fish.this.setIcon(imgIcon[lr][fishType]);
						y = y - udSpeed;
						x = x - lrSpeed;
						lrRand = r.nextInt(690) + 1;
						lrSpeed = r.nextInt(10) + 1;
						
					}
					if( y - 10 < 0|| y == udRand){
						Fish.this.udFlag = !Fish.this.udFlag;
						
						y = y + udSpeed;
						x = x + lrSpeed;
						udRand = r.nextInt(570);
						udSpeed = r.nextInt(10) + 1;
					}
					x = x - lrSpeed;
					y = y - udSpeed;
					Fish.this.setLocation(x,y);
				}else if(!Fish.this.lrFlag && Fish.this.udFlag){
					//fish is going right and up
					if((x + Fish.this.getIcon().getIconWidth()) > 690 || x == lrRand){
						Fish.this.lrFlag = !Fish.this.lrFlag;						
						lr = 0; 
						Fish.this.setIcon(imgIcon[lr][fishType]);
						x = x - lrSpeed;
						y = y - udSpeed;
						lrRand = r.nextInt(690)+1;
						lrSpeed = r.nextInt(10)+1;

					}
					if( y-10 < 0 || y == udRand){
						Fish.this.udFlag = !Fish.this.udFlag;
						y = y + udSpeed;
						x = x + lrSpeed;
						udRand = r.nextInt(570);
						udSpeed = r.nextInt(10) + 1;
					}
					x = x + lrSpeed;
					y = y - udSpeed;
					Fish.this.setLocation(x,y);
				}else if(!Fish.this.lrFlag && !Fish.this.udFlag){
					//fish is going right and down
					if((x + Fish.this.getIcon().getIconWidth()) > 690 || x == lrRand){
						Fish.this.lrFlag = !Fish.this.lrFlag;
						lr = 0; 
						Fish.this.setIcon(imgIcon[lr][fishType]);
						x = x - lrSpeed;
						y = y + udSpeed;
						lrRand = r.nextInt(690)+1;
						lrSpeed = r.nextInt(10)+1;
					}
					if(y + Fish.this.getIcon().getIconHeight() > 570 || y == udRand){
						Fish.this.udFlag = !Fish.this.udFlag;
						y = y - udSpeed;
						x = x + lrSpeed;
						udRand = r.nextInt(570);
						udSpeed = r.nextInt(10)+1;
					}
					x = x + lrSpeed;
					y = y + udSpeed;
					Fish.this.setLocation(x,y);
				}else{
					//fish is going left and down
					if(x - 10 < 0 || x == lrRand){
						Fish.this.lrFlag = !Fish.this.lrFlag;
						Fish.this.udFlag = !Fish.this.udFlag;
						lr = 1; 
						Fish.this.setIcon(imgIcon[lr][fishType]);
						y = y + udSpeed;
						x = x + lrSpeed;
						lrRand = r.nextInt(690)+1;
						lrSpeed = r.nextInt(10)+1;
					}
					if(y + Fish.this.getIcon().getIconHeight() > 570 || y == udRand){
						Fish.this.udFlag = !Fish.this.udFlag;
						y = y-udSpeed;
						x = x-lrSpeed;
						udRand = r.nextInt(570);
						udSpeed = r.nextInt(10)+1;
					}
					x = x - lrSpeed;
					y = y + udSpeed;
					Fish.this.setLocation(x,y);
				}
				
			}
		});
		t1.start();
	}
}
