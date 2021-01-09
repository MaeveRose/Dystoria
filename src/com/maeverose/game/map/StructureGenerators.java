package com.maeverose.game.map;

import com.maeverose.game.gameObjects.gameItems.MapObject;
import com.maeverose.game.gameObjects.gameTiles.Tile;

import static com.maeverose.game.map.MapSettings.MAP_SIZE;
import static com.maeverose.game.map.MapSettings.farm;

public class StructureGenerators {
    public static void generateFarm(int in, Tile[] tiles, MapObject[] MO)
    {
        if(!MapSettings.generateFarms) return;
        if( in < (MAP_SIZE * 4 - 1) || in > ((MAP_SIZE * MAP_SIZE) - (MAP_SIZE * 4))) return;
        if( in % MAP_SIZE < 4 || in % MAP_SIZE > MAP_SIZE - 4) return;
        for (int i = -4; i <= 4;i++)
        {
            for(int j = -2; j<= 2; j++)
            {
                tiles[in + MAP_SIZE * i + j] = Tile.FARMLAND;
                MO[in + MAP_SIZE * i + j] = null;
            }
        }
        farm++;
    }
    public static void generateLake(int in, Tile[] tiles, MapObject[] MO)
    {

    }
    public static void generateHenge(int in, Tile[] tiles, MapObject[] MO)
    {
        if(!MapSettings.generateAltars) return;
        if( in < (MAP_SIZE * 4 - 1) || in > ((MAP_SIZE * MAP_SIZE) - (MAP_SIZE * 4))) return;
        if( in % MAP_SIZE < 4 || in % MAP_SIZE > MAP_SIZE - 4) return;

        tiles[in] = Tile.ALTAR;
        tiles[in+1] = Tile.ALTAR;
        tiles[in-1] = Tile.ALTAR;
        tiles[in-MAP_SIZE] = Tile.ALTAR;
        tiles[in+MAP_SIZE] = Tile.ALTAR;

        tiles[in-MAP_SIZE-3] = Tile.AETHERIUM_BRICK;
        tiles[in-MAP_SIZE+3] = Tile.AETHERIUM_BRICK;
        tiles[in-MAP_SIZE*3 -1] = Tile.AETHERIUM_BRICK;
        tiles[in-MAP_SIZE*3 +1] = Tile.AETHERIUM_BRICK;
        tiles[in+MAP_SIZE-3] = Tile.AETHERIUM_BRICK;
        tiles[in+MAP_SIZE+3] = Tile.AETHERIUM_BRICK;
        tiles[in+MAP_SIZE*3 -1] = Tile.AETHERIUM_BRICK;
        tiles[in+MAP_SIZE*3 +1] = Tile.AETHERIUM_BRICK;

        MO[in] = null;
        MO[in+1] = null;
        MO[in-1] = null;
        MO[in+MAP_SIZE] = null;
        MO[in-MAP_SIZE] = null;

        MO[in-MAP_SIZE-3] = null;
        MO[in-MAP_SIZE+3] = null;
        MO[in-MAP_SIZE*3-1] = null;
        MO[in-MAP_SIZE*3+1] = null;
        MO[in+MAP_SIZE-3] = null;
        MO[in+MAP_SIZE+3] = null;
        MO[in+MAP_SIZE*3-1] = null;
        MO[in+MAP_SIZE*3+1] = null;

        System.out.println("henge generated!");
    }
    public static void generateStarterBuilding(int in, Tile[] tiles, MapObject[] MO)
    {
        if(!MapSettings.generatestarterbuilding) return;
        if(MAP_SIZE < 9) return;
        System.out.println(in);
        for(int i = -3; i <= 3; i++)
        {
            for(int j = -4; j <= 4; j++)
            {
                if(i == -3 || i == 3 || j == -4 || j == 4) {
                    tiles[in + i + (j * MAP_SIZE)] = Tile.WOOD_WALL;
                    MO[in + i + (j * MAP_SIZE)] = null;
                }else {
                    tiles[in + i + (j * MAP_SIZE)] = Tile.WOOD_FLOOR;
                    MO[in + i + (j * MAP_SIZE)] = null;
                }
            }
        }

    }
}
