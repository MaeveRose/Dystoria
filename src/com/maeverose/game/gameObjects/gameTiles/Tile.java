package com.maeverose.game.gameObjects.gameTiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Tile implements Serializable {

    private static final ArrayList<Tile> TILE_LIST = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    private final Material material;
    private final ImageIcon icon;
    private float radioactivity;
    private boolean lightSource;
    private Tile(Material material, ImageIcon img)
    {
        this.icon = img;
        this.material = material;
    }


    public static class Material implements Serializable
    {
        private static final long serialVersionUID = 1L;
        public static final Material ROCK = new Material(new Color(0.5f,0.5f,0.5f));
        public static final Material WOOD = new Material(new Color(0,0,0));
        public static final Material STONE = new Material(new Color(0,0,0, 0));
        public static final Material VERY_COMMON_ORE = new Material(new Color(0.9f,0.9f,0.9f));
        public static final Material COMMON_ORE = new Material(new Color(0.5f,0.9f,0.5f));
        public static final Material UNCOMMON_ORE = new Material(new Color(0.5f,0.5f,0.9f));
        public static final Material RARE_ORE = new Material(new Color(0.9f,0.9f,0.9f));
        public static final Material MYTHIC_ORE = new Material(new Color(0.9f,0.5f,0.9f));
        public static final Material LEGENDARY_ORE = new Material(new Color(0.9f,0.75f,0.5f));
        public static final Material GRASS = new Material(new Color(0.5f,1.0f,0.5f));
        public static final Material DIRT = new Material(new Color(0.12f,0.07f,0.00f));
        public static final Material FOREST = new Material(new Color(0.1f,0.5f,0.1f));
        public static final Material WATER = new Material(new Color(0.0f,0.0f,0.75f));
        public static final Material SAND = new Material(new Color(1f,1f,0.8f));
        public static final Material ALTARSTONE = new Material(new Color(0.125f,0.125f,0.25f));
        private final Color color;
        private Material(Color color)
        {
            this.color = color;
        }
    }
    public static final Tile ROCK = registerTile(Material.ROCK,"ROCK");
    public static final Tile AETHERIUM = registerTile(Material.ALTARSTONE,"AETHERIUM");
    public static final Tile AETHERIUM_BRICK = registerTile(Material.ALTARSTONE,"AETHERIUM_BRICK");
    public static final Tile ALTAR = registerTile(Material.ALTARSTONE,"ALTARSTONE");
    public static final Tile FARMLAND = registerTile(Material.DIRT,"FARMLAND");
    //public static final Tile FOREST = registerTile(Material.FOREST,"FOREST");
    public static final Tile OCEAN = registerTile(Material.WATER,"OCEAN");
    public static final Tile LAKE = registerTile(Material.WATER,"LAKE");
    public static final Tile PLAINS = registerTile(Material.GRASS,"PLAINS");
    public static final Tile SAND = registerTile(Material.SAND,"SAND");
    public static final Tile GRAVEL = registerTile(Material.SAND,"GRAVEL");
    public static final Tile CLAY_ORE = registerTile(Material.VERY_COMMON_ORE,"CLAY_ORE");          //very common
    public static final Tile COPPER_ORE = registerTile(Material.COMMON_ORE,"COPPER_ORE");           //common
    public static final Tile TIN_ORE = registerTile(Material.COMMON_ORE,"TIN_ORE");                 //common
    public static final Tile IRON_ORE = registerTile(Material.COMMON_ORE,"IRON_ORE");               //common
    public static final Tile COAL_ORE = registerTile(Material.UNCOMMON_ORE,"COAL_ORE");             //uncommon
    public static final Tile TITANIUM_ORE = registerTile(Material.RARE_ORE,"TITANIUM_ORE");         //rare
    public static final Tile TUNGSTUN_ORE = registerTile(Material.RARE_ORE,"TUNGSTUN_ORE");         //rare
    public static final Tile MYCATHITE_ORE = registerTile(Material.MYTHIC_ORE,"MYCATHITE_ORE");     //mythic
    public static final Tile TARALANNIUM_ORE = registerTile(Material.MYTHIC_ORE,"TARALANNIUM_ORE"); //mythic
    public static final Tile NUVITE_ORE = registerTile(Material.MYTHIC_ORE,"NUVITE_ORE").setRadioactivity(15.0f);           //mythic
    public static final Tile SAM_ORE = registerTile(Material.LEGENDARY_ORE,"SAM_ORE").setRadioactivity(-15.0f);              //legendary
    public static final Tile WOOD_FLOOR = registerTile(Material.WOOD,"WOOD_FLOORING");
    public static final Tile WOOD_WALL = registerTile(Material.WOOD,"WOOD_WALL");
    public static final Tile STONE_FLOOR = registerTile(Material.STONE, "STONE_FLOORING");
    public static final Tile STONE_WALL = registerTile(Material.STONE,"STONE_WALL");
    public static Tile registerTile(Material m, String s)
    {
        ImageIcon i = new ImageIcon("Image/tiles/TILE_"+s+".png");
        TILE_LIST.add(new Tile(m,i));
        return TILE_LIST.get(TILE_LIST.size()-1);
    }
    public ImageIcon getIcon()
    {
        return icon;
    }
    public Tile setRadioactivity(float f)
    {
        this.radioactivity = f;
        return this;
    }
    public Tile setLit(boolean b)
    {
        this.lightSource = b;
        return this;
    }
    public static void FinalizeList() throws IOException {
        int width = (int)Math.ceil(Math.sqrt(TILE_LIST.size()+1));
        BufferedImage buff = new BufferedImage(width * 8, width * 8, BufferedImage.TYPE_INT_RGB);
        Graphics g = buff.createGraphics();
        int i = 0;
        for (Tile e: TILE_LIST)
        {
            e.getIcon().paintIcon(null, g, (i % width)*8, (i / width)*8);
            i++;
        }
        File output = new File("iconmap.png");
        ImageIO.write(buff,"png",output);

    }


}
