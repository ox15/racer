import greenfoot.*;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.text.DecimalFormat;


public class RaY extends World
{
    int[] obbX = {900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900,900};
    double[] obbY = {0,10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,250,260,270,280,290};
    int x,y,color=0,score=0,health=3,offY,alpha=255,accel;
    double slant,speed = .7,offX=0,count=-1;
    boolean scored=false;
    String me;
    GreenfootImage screen = new GreenfootImage(500,300);
    greenfoot.Color flash = greenfoot.Color.WHITE;
    DecimalFormat decimalFormat = new DecimalFormat("#.###");
    
    
    public RaY() { 
        super(500,300, 1);
        for(int f=8;f<20;f++){
            obbX[f] = Greenfoot.getRandomNumber(80)-40;
        }
        screen = getBackground();
    }
    
    public void act() 
    {
        screen.setColor(new greenfoot.Color(color,0,0,alpha));
        screen.fillRect(0,0,500,300);
        count = count+speed;
        if(count>15){
            flash = new greenfoot.Color(Greenfoot.getRandomNumber(200)+50,Greenfoot.getRandomNumber(200)+50,Greenfoot.getRandomNumber(200)+50);
            if(scored){
                flash = new greenfoot.Color(Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(100));
            }
            count=0;
        }
        
        if(health<=0){
            scored=true;
            screen.setColor(flash);
            screen.drawString("Final score:  "+score,5,15);
            screen.drawString("Final speed:  "+decimalFormat.format(speed),5,45);
            return;
        }
        
        
        
        
        screen.setColor(new greenfoot.Color(70,70,70));
        x = 250+(int)(270*Math.atan2(40-offX,0));
        y = (int)(5000/Math.sqrt((40-offX)*(40-offX)));
        screen.drawLine(250,150,x,150-(int)(y/2)+(int)(.002*(250-x)*slant));
        x = 250+(int)(270*Math.atan2(-40-offX,0));
        y = (int)(5000/Math.sqrt((-40-offX)*(-40-offX)));
        screen.drawLine(250,150,x,150-(int)(y/2)+(int)(.002*(250-x)*slant));
        
        for(int f=-40;f<=40;f=f+5){
            x = 250+(int)(270*Math.atan2(f-offX,0));
            y = (int)(5000/Math.sqrt((f-offX)*(f-offX)));
            screen.drawLine(250,150,x,150+(int)(y/2)+(int)(.002*(250-x)*slant));
        }
        
        
        // Display info. Each line is 12 pixels high.
        screen.setColor(flash);
        screen.drawString("Score: "+score,0,12);
        screen.drawString("Health: "+health,0,24);
        screen.drawString("Speed: "+decimalFormat.format(speed),0,36);
        
        
        
        for(int f=0;f<30;f++){
            x = 250+(int)(270*Math.atan2(obbX[f]-offX,obbY[f]));
            y = (int)(400/Math.sqrt((obbX[f]-offX)*(obbX[f]-offX)  + obbY[f]*obbY[f]));
            offY = 150+(int)(270*Math.atan2(4,obbY[f]));
            
            screen.drawOval(x-(int)(y/2),offY-(int)(y/2)+(int)(.002*(250-(x-(int)(y/2)))*slant),y,y);
            obbY[f] = obbY[f] - speed;
            if(obbY[f]<9 && obbY[f]>6 && Math.abs(obbX[f] - offX)<1){
                color = 254;
                obbY[f] = 290;
                obbX[f] = Greenfoot.getRandomNumber(80)-40;  
                health--;
            }
            if(obbY[f]<5){
                obbY[f] = 290;
                obbX[f] = Greenfoot.getRandomNumber(80)-40;      
            }
        }
        
        
        if(Greenfoot.isKeyDown("left")){
            accel = accel - 5;
        }
        if(Greenfoot.isKeyDown("right")){
            accel = accel+5;
        }
        slant += (accel-slant)/5.0;
        if(offX+accel/100.0 > 39 || offX+accel/100.0<-39){
            accel = -accel;
        }
        offX = offX + accel/100.0;
        if(accel>0){
            accel = accel - 1;
        }
        if(accel<0){
            accel = accel + 1;
        }
        speed = speed+.0005;
        if(color>0){
            color = color - 2;
        }
        score++;
        
        screen.drawRect(240,290,20,10);
        
        
    } 
    
 

}
