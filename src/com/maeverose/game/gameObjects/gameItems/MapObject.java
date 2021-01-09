package com.maeverose.game.gameObjects.gameItems;

import com.maeverose.game.gameObjects.gameTiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MapObject extends GameObject implements Serializable {
    private String unlocalizedName;
    private ImageIcon icon;
    public static final MapObject MAP_OBJ_TREE = makeMapObject("MAPTREE");
    public static final MapObject MAP_OBJ_CHEST = makeMapObject("MAPCHEST");

    private MapObject(String s)
    {
        this.unlocalizedName = s;
        icon = new ImageIcon("Image/tile_objects/"+s+".png");
    }
    public static MapObject makeMapObject(String s)
    {
        return new MapObject(s);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }
}
