package com.maeverose.game.Renderer;

import com.maeverose.game.map.Map;
import com.maeverose.game.map.MapSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.maeverose.game.map.MapSettings.MAP_SIZE;

public class testout {
    public static Map mappy;
    public static void RUN() throws IOException {
        mappy = new Map();
        BufferedImage buff = new BufferedImage(MAP_SIZE * 8, MAP_SIZE * 8, BufferedImage.TYPE_INT_RGB);
        Graphics g = buff.createGraphics();
        for (int i = 0; i < MAP_SIZE; i++)
        {
            for(int j = 0; j < MAP_SIZE ; j++)
            {
                mappy.getTile(i+(j*MAP_SIZE)).getIcon().paintIcon(null, g, i*8, j*8);
                if(mappy.getObject(i+(j*MAP_SIZE)) != null)
                {
                    mappy.getObject(i+(j*MAP_SIZE)).getIcon().paintIcon(null,g,i*8,j*8);
                }
            }
        }
        System.out.println("MAP GENERATION COMPLETE");
        File output = new File("testRender.png");
        ImageIO.write(buff,"png",output);
        System.out.println("MAP FILE WRITTEN");
        g.dispose();
        buff.flush();
    }
}
