
package io.github.galaipa.bb;

import io.github.galaipa.bb.Cuboid.CuboidDirection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Team {
    private int id;
    private int point;
    private Cuboid cuboid;
    private Cuboid parent;
    private Location spawnpoint;
    public Player Jokalaria;
    public Player Jokalaria2;
    private World world;
    public Team(int n) {
	id = n;
        point = 0;
	}
    public void addRegion(Location l1, Location l2){
     cuboid = new Cuboid(l1,l2);
     parent = new Cuboid(cuboid);
     parent = parent.expand(CuboidDirection.North,1);
     parent = parent.expand(CuboidDirection.West,1);
     parent = parent.expand(CuboidDirection.Up,1);
     parent = parent.expand(CuboidDirection.Down,1);
     parent = parent.expand(CuboidDirection.South,1);
     parent = parent.expand(CuboidDirection.East,1);
     world = l1.getWorld();
    }
    public void resetArenas(){
            for (Block block : cuboid){
                    block.setType(Material.AIR);
            }
    }

    public int getID(){
        return id;
    }
    public World getWorld(){
        return world;
    }
    public Cuboid getCuboid(){
        return cuboid;
    }
    public Cuboid getCuboidParent(){
        return parent;
    }
    public int getPoint(){
        return point;
    }
    public void addPoint(int p){
        point = point + p;
    }
    public Location getSpawnPoint(){
        Location v = cuboid.getCenter();
        spawnpoint = v;
        return spawnpoint;
    }
    public void addPlayer(Player p){
        Jokalaria = p;
        }
    public void removePlayer(Player p){
        Jokalaria = null;
        }
    public void addPlayers(Player p,Player p2){
        Jokalaria = p;
        Jokalaria2 = p2;
    }
    public Player getPlayer(){
        return Jokalaria;
        }
    public Player getPlayer2(){
        return Jokalaria2;
        }
    public String getPlayerString(){
        String p = Jokalaria.getName();
        String jokalariak = p;
        if(Jokalaria2 != null){
            jokalariak = p + " eta " + Jokalaria2.getName();
        }
        return jokalariak;
        }
    public Boolean checkPlayer(Player p){
        Player pa = Jokalaria;
        Player pa2 = Jokalaria2;
        if(p == pa || p == pa2){
            return true;
        }
        else{
            return false;
        }
        }
    }
    

