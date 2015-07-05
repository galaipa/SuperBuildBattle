
package io.github.galaipa.bb;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class GameListener implements Listener {
        public SuperBuildBattle plugin;
        public GameListener(SuperBuildBattle instance) {
            plugin = instance;
        }
          @EventHandler(priority = EventPriority.LOW)
          public void onInventoryClick(PlayerInteractEvent event){
              if(plugin.voting == true){
                  if(plugin.Jokalariak.contains(event.getPlayer())){
                 if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ){
                        Player p = event.getPlayer();
                        event.setCancelled(true);
                        for(Player b : plugin.botoa){
                            if(b == p){
                                p.sendMessage(ChatColor.RED + plugin.getTr("25"));
                                return;
                            }
                        }
                        if(plugin.teams2[plugin.etxea].checkPlayer(p)== true){
                            p.sendMessage(ChatColor.RED + plugin.getTr("26"));
                        }
                        else if(p.getItemInHand().getType() == Material.STAINED_CLAY){
                                  String izena = p.getItemInHand().getItemMeta().getDisplayName();
                                    if (izena.equalsIgnoreCase(ChatColor.RED + plugin.getTr("35"))){
                                    plugin.teams2[plugin.etxea].addPoint(0);
                                    p.sendMessage(ChatColor.GREEN + "Your selection: " + izena );
                                    plugin.botoa.add(p);
                                        }
                                    else if (izena.equalsIgnoreCase(ChatColor.RED + plugin.getTr("33"))){
                                    plugin.teams2[plugin.etxea].addPoint(1);
                                    p.sendMessage(ChatColor.GREEN + "Your selection: " + izena );
                                    plugin.botoa.add(p);
                                        }
                                    else if (izena.equalsIgnoreCase(ChatColor.RED + plugin.getTr("32"))){
                                    plugin.teams2[plugin.etxea].addPoint(2);
                                    p.sendMessage(ChatColor.GREEN + "Your selection: " + izena );
                                    plugin.botoa.add(p);
                                        }
                                    else if (izena.equalsIgnoreCase(ChatColor.GREEN + plugin.getTr("31"))){
                                    plugin.teams2[plugin.etxea].addPoint(3);
                                    p.sendMessage(ChatColor.GREEN + "Your selection:: " + izena );
                                    plugin.botoa.add(p);
                                        }
                                    else if (izena.equalsIgnoreCase(ChatColor.GREEN + plugin.getTr("30"))){
                                    plugin.teams2[plugin.etxea].addPoint(4);
                                    p.sendMessage(ChatColor.GREEN + "Your selection: " + izena );
                                    plugin.botoa.add(p);
                                        }
                                    else if (izena.equalsIgnoreCase(ChatColor.GREEN + plugin.getTr("36"))){
                                    plugin.teams2[plugin.etxea].addPoint(5);
                                    p.sendMessage(ChatColor.GREEN + "Your selection: " + izena );
                                    plugin.botoa.add(p);
                                        }
                        }
              }
          }} 
              else{
                  
              }
         
          }   
              @EventHandler
              public void PlayerCommand(PlayerCommandPreprocessEvent event) {
                  if(plugin.inGame == true){
                      Player p = event.getPlayer();
                      if(plugin.Jokalariak.contains(p)){
                              if(event.getMessage().toLowerCase().startsWith("/buildbattle")){
                                }
                              else if(event.getMessage().toLowerCase().startsWith("/bb")){
                                }
                              else{
                               event.setCancelled(true);
                               p.sendMessage(ChatColor.GREEN +"[BuildBattle]" + ChatColor.RED + "You can't use command during the game");
                              }
                          
                      }
                  }
              }
              @EventHandler
              public void CuboidProtection(BlockBreakEvent event) {
                  if (plugin.inGame){
                      if(plugin.Jokalariak.contains(event.getPlayer())){
                          if(!getTeam(event.getPlayer()).getCuboid().contains(event.getBlock()) || plugin.voting){
                              event.setCancelled(true);
                          }
                      }
                  }
              }
              @EventHandler
              public void CuboidProtection2(BlockPlaceEvent event) {
                  if (plugin.inGame){
                      if(plugin.Jokalariak.contains(event.getPlayer())){
                          if(!getTeam(event.getPlayer()).getCuboid().contains(event.getBlock())){
                              event.setCancelled(true);
                          }
                      }
                  }
              }
            public Team getTeam(Player p){
                                for(Team t : plugin.teams){
                                    if(t.getPlayer() == p){
                                        return t;

                                    }
                                }
                                return null;
            }
}

