package com.maeverose.game.map;

import com.maeverose.game.gameObjects.gameItems.MapObject;
import com.maeverose.game.gameObjects.gameTiles.Tile;


import java.io.Serializable;

import static com.maeverose.game.map.MapSettings.MAP_SIZE;


public class Map implements Serializable {
    private static final long serialVersionUID = 1L;
    private Tile[] tiles = new Tile[MAP_SIZE * MAP_SIZE];
    private MapObject[] MO = new MapObject[MAP_SIZE * MAP_SIZE];
    public Tile getTile(int i)
    {
        return tiles[i];
    }
    public MapObject getObject(int i)
    {
        return MO[i];
    }
    public Map() {

        //generate chunks based on perlin noise generation
        Generate();
    }
    private static int farm = 0;
    private static int sam = 0;
    private void Generate() {
        Noise.setSeed(System.currentTimeMillis());
        double[] oreData = Noise.normalize(Noise.turbulence(MAP_SIZE, MAP_SIZE, 1));
        double[] structGen = Noise.normalize(Noise.turbulence(MAP_SIZE, MAP_SIZE, 1));
        double[] biomeGen = Noise.normalize(Noise.turbulence(MAP_SIZE, MAP_SIZE,20));
        double[] data = Noise.normalize(Noise.turbulence(MAP_SIZE, MAP_SIZE, 200));
        double[] oddData = Noise.turbulence(MAP_SIZE,MAP_SIZE, 503);
        double[] oddData2 = Noise.turbulence(MAP_SIZE,MAP_SIZE,17);
        for (int i = 0; i < oddData.length; i++)
        {
            if(oddData[i] < oddData2[i]) oddData[i] = oddData2[i] - oddData[i];
            else if(oddData[i] > oddData2[i]) oddData[i] = oddData[i] - oddData2[i];
            else oddData[i] = 0;
        }
        oddData = Noise.normalize(oddData);
        for (int i = 0; i < oreData.length; i++) {
            oddData[i] = 255 * oddData[i];
            data[i] = 255 * data[i];
            oreData[i] = 255 * oreData[i];
            structGen[i] = 255 * structGen[i];
            biomeGen[i] = 255* biomeGen[i];
        }
        PROCESS(data, structGen, oreData, biomeGen, tiles, MO);
        oddData2 = null;
        data = null;
        structGen = null;
        oreData = null;
        biomeGen = null;
        System.gc();
        System.out.println("Sam ore generated: " + sam);
        System.out.println("Farms Generated: " + farm);
    }
    private static final void PROCESS(double[] worldGen, double[] StructureGen, double[] oreGen, double[] biomeGen, Tile[] tiles, MapObject[] MO) {
        for (int i = 0; i < (MAP_SIZE * MAP_SIZE); i++) {
            if (worldGen[i] <= 96) {
                tiles[i] = Tile.OCEAN;
            } else if (worldGen[i] > 96 && worldGen[i] <= 100) {
                tiles[i] = Tile.SAND;
            } else if ((worldGen[i] > 100 && worldGen[i] <= 140) || (worldGen[i] > 140 && worldGen[i] <= 160) ) {
                if (biomeGen[i] <= 127) {
                    addObjectToMap(MapObject.MAP_OBJ_TREE,MO, i);
                    tiles[i] = Tile.PLAINS;
                }
                else tiles[i] = Tile.PLAINS;
            //}else if (worldGen[i] > 120 && worldGen[i] <= 140) {
                //tiles[i] = Tile.PLAINS;
            } else if (worldGen[i] > 160 && worldGen[i] <= 250) {
                tiles[i] = Tile.ROCK;
            } else if (worldGen[i] > 250) {
                tiles[i] = Tile.AETHERIUM;
            }
        }
        for (int i = 0; i < (MAP_SIZE * MAP_SIZE); i++)
        {
            if (worldGen[i] > (160+MapSettings.stone_barrier) && worldGen[i] <= 250) {
                if (oreGen[i] > 245.5 && oreGen[i] <= 247) {
                    generateOres(i, Tile.CLAY_ORE, tiles);
                }
                if (oreGen[i] > 247 && oreGen[i] <= 248.25) {
                    generateOres(i, Tile.COPPER_ORE, tiles);
                }
                if (oreGen[i] > 248.25 && oreGen[i] <= 249.5) {
                    generateOres(i, Tile.TIN_ORE, tiles);
                }
                if (oreGen[i] > 249.5 && oreGen[i] <= 250.75) {
                    generateOres(i, Tile.IRON_ORE, tiles);
                }
                if (oreGen[i] > 250.75 && oreGen[i] <= 251.75) {
                    generateOres(i, Tile.COAL_ORE, tiles);
                }
                if (oreGen[i] > 251.75 && oreGen[i] <= 252.5) {
                    generateOres(i, Tile.TITANIUM_ORE, tiles);;
                }
                if (oreGen[i] > 252.5 && oreGen[i] <= 253.25) {
                    generateOres(i, Tile.TUNGSTUN_ORE, tiles);
                }
                if (oreGen[i] > 253.25 && oreGen[i] <= 253.75) {
                    generateOres(i, Tile.MYCATHITE_ORE, tiles);
                }
                if (oreGen[i] > 253.75 && oreGen[i] <= 254.25) {
                    generateOres(i, Tile.TARALANNIUM_ORE, tiles);
                }
                if (oreGen[i] > 254.25 && oreGen[i] <= 254.75) {
                    generateOres(i, Tile.NUVITE_ORE, tiles);
                }
                if (oreGen[i] >= 254.99) {
                    sam++;
                    generateOres(i, Tile.SAM_ORE, tiles);
                }
            }
        }
        for (int i = 0; i < (MAP_SIZE * MAP_SIZE); i++)
        {
            if(worldGen[i] > 100 && worldGen[i] <= 160)
            {
                if(StructureGen[i] < 255.0 && StructureGen[i] > 254.99)
                {
                    StructureGenerators.generateFarm(i, tiles, MO);
                }
                if(StructureGen[i] < 254.99 && StructureGen[i] > 254.98)
                {
                    StructureGenerators.generateHenge(i, tiles, MO);
                }
            }
        }
        StructureGenerators.generateStarterBuilding( (MAP_SIZE * MAP_SIZE)/2 - (MAP_SIZE/2),tiles,MO);
    }

    private static void generateOres(int in, Tile ore, Tile[] tiles)
    {
        for(int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (in - MAP_SIZE -1 < 0 || (in + MAP_SIZE + 1) > (MAP_SIZE*MAP_SIZE)) continue;
                if(Math.random() >= 0.5 || (in == in + MAP_SIZE * i + j)) {
                    tiles[in+i+(j*MAP_SIZE)] = ore;

                }
            }
        }
    }
    public static void addObjectToMap(MapObject m, MapObject[] ma, int i)
    {
        ma[i] = m;
    }
}