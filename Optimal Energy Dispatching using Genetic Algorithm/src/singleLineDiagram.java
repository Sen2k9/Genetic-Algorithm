

import java.awt.*;
import javax.swing.*;
public class singleLineDiagram extends JFrame {
	public String s=" ";
	public String s2=" ";
	public int k;
	public singleLineDiagram( String s, String s2, int k) {
	this.k=k;
	this.s=s;
	this.s2=s2;
	if(k==0)
		setTitle("Single Line diagram of power network for hour "+24+" to "+(k+1));
	else
		setTitle("Single Line diagram of power network for hour "+k+" to "+(k+1));
		setSize(1200,700);
	setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
  public void paint(Graphics g) {
	
	 
		int a[] = new int[14];
		int b[] = new int[14];
	
		int[] p = new int[3];
		int[] q = new int[3];
		p[0]=s2.indexOf(",", 0);
		q[0]=Integer.parseInt(s2.substring(1, p[0]).trim());
	
		for(int i=1;i<3;i++) {
			if(i==2) {
				
					q[i]=Integer.parseInt(s2.substring(p[i-1]+1, s2.length()-1).trim());
				
				}
			else {
			p[i]=s2.indexOf(",",p[i-1]+1);
	
			q[i]=Integer.parseInt(s2.substring(p[i-1]+1, p[i]).trim());
		
			}
		}
	
		a[0]=s.indexOf(",", 0);
		b[0]=Integer.parseInt(s.substring(1,a[0]).trim());

		for(int i=1;i<14;i++) {
			if(i==13) {
				
					b[i]=Integer.parseInt(s.substring(a[i-1]+1, s.length()-1).trim());
				
				}
			else {
			a[i]=s.indexOf(",", a[i-1]+1);
		
			b[i]=Integer.parseInt(s.substring(a[i-1]+1, a[i]).trim());
		
			}
		}
		for(int i=0;i<14;i++) {
			  switch(b[i]){
			  
			   
			  case 1:
			  {
				  g.setColor(Color.GREEN);
		  g.drawLine(150, 100, 150, 200);// line 1
		  
		  g.setColor(Color.blue);
		  g.drawRect(145, 200, 10, 10);
		 
		  break;}
			  case 9:
			  { g.setColor(Color.blue);
			  g.drawRect(145, 200, 10, 10);
			  g.setColor(Color.GREEN);
				  g.drawLine(150, 210, 50, 300);// line 2
				  g.setColor(Color.blue);
				  g.drawRect(45, 300, 10, 10); 
				  break;}
			  case 13:
			  {
				  g.setColor(Color.blue);
				  g.drawRect(45, 300, 10, 10); 
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(50, 300, 50, 400);// line 3
				  g.setColor(Color.blue);
				  g.drawRect(45, 400, 10, 10);
				  break;}
			  case 4:
			  {
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(50, 400, 50, 500);// line 4
				  g.setColor(Color.blue);
				  g.drawRect(45, 500, 10, 10);
				  g.setColor(Color.blue);
				  g.drawRect(45, 400, 10, 10);
				  break;}
			  case 5:
			  {g.setColor(Color.blue);
			  g.drawRect(145, 200, 10, 10);
				  g.setColor(Color.GREEN);
				  g.drawLine(150, 210, 200, 300);// line 5
				  g.setColor(Color.blue);
				  g.drawRect(195, 300, 10, 10);
				  break;}
			  case 6:
			  {
				  g.setColor(Color.blue);
				  g.drawRect(195, 300, 10, 10);
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(205, 310, 300, 400);// line 6
				  g.setColor(Color.blue);
				  g.drawRect(295, 400, 10, 10);
				  break;}
			  case 7:
			  { g.setColor(Color.blue);
			  g.drawRect(445, 300, 10, 10);
				  
				  g.setColor(Color.GREEN);
				  
				  g.drawLine(450, 300, 305, 400);//line 7
				  g.setColor(Color.blue);
				 g.drawRect(295, 400, 10, 10);
				  break;}
			  case 8:
			  {
				  g.setColor(Color.blue);
				  g.drawRect(445, 300, 10, 10);
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(455, 300, 500, 400);//line 8
				  g.setColor(Color.blue);
				  g.drawRect(495, 400, 10, 10);
				  break;}
			  case 2:
			  { g.setColor(Color.GREEN);
				 
				  g.drawLine(400, 110, 500, 200);//line 9
				  g.setColor(Color.blue);
				  g.drawRect(495, 200, 10, 10);
				  break;}
			  case 10:
			  {
				  g.setColor(Color.blue);
				  g.drawRect(495, 200, 10, 10);
				  g.setColor(Color.GREEN);
				
				  g.drawLine(500, 200, 450, 300);//line 10
				  g.setColor(Color.blue);
				  g.drawRect(445, 300, 10, 10);
				  break;}
			  case 11:
			  { 
				  g.setColor(Color.blue);
				  g.drawRect(495, 200, 10, 10);
				  g.setColor(Color.GREEN);
				  
				  g.drawLine(505, 210, 600, 300);//line 11
				  g.setColor(Color.blue);
				  g.drawRect(595, 300, 10, 10);
				  break;}
			  case 12:
			  {
				  g.setColor(Color.blue);
				  g.drawRect(595, 300, 10, 10);
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(600, 300, 750, 400);//line 12
				  g.setColor(Color.blue);
				  g.drawRect(745, 400, 10, 10);
				  break;}
			  case 3:
			  {  g.setColor(Color.GREEN);
				 
				  g.drawLine(905, 110, 1000, 200);//line 13
				  g.setColor(Color.blue);
				  g.drawRect(995, 200, 10, 10);
				  break;}
			  case 14:
			  {  g.setColor(Color.blue);
			
			  g.drawRect(995, 200, 10, 10);
			  g.setColor(Color.GREEN);
				  g.drawLine(995, 200, 755, 400);//line 14
				  g.setColor(Color.blue);
				  g.drawRect(745, 400, 10, 10);
				  break;}
			  case 15:
			  { g.setColor(Color.blue);
				  
			  g.drawRect(995, 200, 10, 10);
			  g.setColor(Color.GREEN);
				  g.drawLine(1000, 200, 1100, 550);//line 15
				  g.setColor(Color.blue);
				  
				  g.drawRect(1095, 550, 10, 10);
				  break;}
			  case 16:
			  { g.setColor(Color.GREEN);
				  
				  g.drawLine(1095, 560, 50, 510);//line 16
	 g.setColor(Color.blue);
				  
				  g.drawRect(1095, 550, 10, 10);
				  g.setColor(Color.blue);
				  g.drawRect(45, 500, 10, 10);
				  break;}
			  default:
			  { g.setColor(Color.GREEN);
				 
				  g.drawLine(150, 0, 150, 100);//source 1
				  g.setColor(Color.blue);
				  g.drawRect(145, 100, 10, 10);

				//  g.drawRect(295, 400, 10, 10);
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(400, 0, 400, 100);//source 2
				  g.setColor(Color.blue);
				  g.drawRect(395, 100, 10, 10);
				 
				
				  
				  
				  
				  g.setColor(Color.GREEN);
				  
				  g.drawLine(900, 0, 900, 100);//source 3
				  g.setColor(Color.blue);
				  g.drawRect(895, 100, 10, 10);
					  
					//  g.drawString("hello", 150, 150);
				  g.setColor(Color.GREEN);
				 
				  g.drawLine(50, 105, 1050, 105);//bus bar
				  g.setColor(Color.blue);
				  g.drawRect(145, 100, 10, 10);
				  break;
			  }
		  }
		
	
		
	
	 
		}
	 
		
			  if( q[0]==1) {
				  g.setColor(Color.GREEN);
			
			  g.drawLine(150, 0, 150, 100);//source 1
			  g.setColor(Color.blue);
			  g.drawRect(145, 100, 10, 10);
			  g.setColor(Color.BLACK);
			  g.drawString("Hydro-Electric Power "+"ON", 155, 50);
			  }
			  else {
				  g.setColor(Color.RED);
				  g.drawLine(150, 0, 150, 100);//source 1
				  g.setColor(Color.blue);
				  g.drawRect(145, 100, 10, 10);
				  g.setColor(Color.BLACK);
				  g.drawString("Hydro-Electric Power "+"OFF", 155, 50);
			  }
			  if(q[1]==1) { g.setColor(Color.GREEN);
		
			  
			  g.drawLine(400, 0, 400, 100);//source 2
			  g.setColor(Color.blue);
			  g.drawRect(395, 100, 10, 10);
			  g.setColor(Color.BLACK);
			  g.drawString("Wind Power "+"ON", 405, 50);
			  }
			  else {
				  g.setColor(Color.RED);
				  g.drawLine(400, 0, 400, 100);//source 2
				  g.setColor(Color.blue);
				  g.drawRect(395, 100, 10, 10);
				  g.setColor(Color.BLACK);
				  g.drawString("Wind Power "+"OFF", 405, 50);
			  }
			
			
			  if( q[2]==1) {
			  
				  g.setColor(Color.GREEN);
			 
			
			  g.drawLine(900, 0, 900, 100);//source 3
			  g.setColor(Color.blue);
			  g.drawRect(895, 100, 10, 10);
			  g.setColor(Color.BLACK);
			  g.drawString("Solar Power "+"ON", 905, 50);
			  }
			  else {
				  g.setColor(Color.RED);
				  g.drawLine(900, 0, 900, 100);//source 3
				  g.setColor(Color.blue);
				  g.drawRect(895, 100, 10, 10);
				  g.setColor(Color.BLACK);
				  g.drawString("Solar Power "+"OFF", 905, 50);
			  }
			  g.setColor(Color.GREEN);  
				
			
			  g.drawLine(50, 105, 1050, 105);//bus bar
			  g.setColor(Color.blue);
			  g.drawRect(145, 100, 10, 10);
			
			  
				
			  }
}
		  
	
	 
	 
	 
	  
	  
	
	  
		
