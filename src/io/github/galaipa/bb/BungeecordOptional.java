
package io.github.galaipa.bb;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;


public class BungeecordOptional {
        public static SuperBuildBattle plugin;
        public BungeecordOptional(SuperBuildBattle instance) {
            plugin = instance;
        }
    public static void sendPlayer(Player p){
        ProxiedPlayer player = (ProxiedPlayer) p;
        ServerInfo target = ProxyServer.getInstance().getServerInfo(plugin.getConfig().getString("BungeeCord.Hub"));
        player.connect(target);
    }
}
