package io.github.galaipa.bb;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class WorldGuardOptional {
private static World world;
        public SuperBuildBattle plugin;
        public WorldGuardOptional(SuperBuildBattle instance) {
            plugin = instance;
        }
    public static void WGregion(World w,Double x,Double y,Double z,Double x2,Double y2,Double z2,int id){
        BlockVector b1 = new BlockVector(x,y,z);
        BlockVector b2 = new BlockVector(x2,y2,z2);
        ProtectedCuboidRegion region = new ProtectedCuboidRegion("bb_" + id,b1,b2);
        RegionManager manager = getWorldGuard().getRegionManager(w);
        region.setFlag(DefaultFlag.PASSTHROUGH, StateFlag.State.ALLOW );
        manager.addRegion(region);
        world = w;
    }
    public static void WGregionRM(int id){
        getWorldGuard().getRegionManager(world).removeRegion("bb_" + id);
    }

        private static WorldGuardPlugin getWorldGuard() {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
            return (WorldGuardPlugin) plugin;
        }
}