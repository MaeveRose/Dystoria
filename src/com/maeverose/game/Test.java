package com.maeverose.game;

import com.maeverose.game.Renderer.Application;
import com.maeverose.game.gameObjects.gameTiles.Tile;
import com.maeverose.game.map.Map;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {
    public static final Map mappy = new Map();
    public static void main(String[] args) throws IOException {
        Properties defaultProperties = new Properties();
            File f = new File("cfg/default.properties");
            if(f.exists()) {
                FileInputStream in = new FileInputStream(f);
                in.close();
            } else {
                f.createNewFile();
            }


        Tile.FinalizeList();
        //testout.RUN();

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
        FileOutputStream out = new FileOutputStream(f);
        //defaultProperties.store(out,"---MAP SETTINGS---");
        defaultProperties.setProperty("gen.stonebarrier","0");
        defaultProperties.setProperty("gen.starterbuilding","true");
        defaultProperties.setProperty("gen.genfarm","true");
        defaultProperties.setProperty("gen.genaltar","true");
        defaultProperties.storeToXML(out,"##MapSettings##");
        //defaultProperties.store(out, "---Map Settings---");

        out.close();
    }

}
