package com.maeverose.game.Renderer;


import com.maeverose.game.Test;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.maeverose.game.map.MapSettings.MAP_SIZE;

public class Board extends JPanel{
    public Board(){

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawFrame(g);
    }

    public void drawFrame(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < MAP_SIZE; i++)
        {
            for(int j = 0; j < MAP_SIZE ; j++)
            {
                Test.mappy.getTile(i+(j*MAP_SIZE)).getIcon().paintIcon(null, g2d, i*8, j*8);
                if(Test.mappy.getObject(i+(j*MAP_SIZE)) != null)
                {
                    Test.mappy.getObject(i+(j*MAP_SIZE)).getIcon().paintIcon(null,g2d,i*8,j*8);
                }
            }
        }
    }

}
