package io.github.galaipa.bb;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminGui implements Listener{
        private int time = 1;
        private int timeVote = 1;
        private int players1 = 1;
        private int players2 = 1;
        private Location l;
        private boolean region = false;
        private boolean setup = false;
        public SuperBuildBattle plugin;
        public final Map<Integer, Location> location1 = new HashMap<Integer, Location>();
        public final Map<Integer, Location> location2 = new HashMap<Integer, Location>();
        public AdminGui(SuperBuildBattle instance) {
            plugin = instance;
        }
    public static ItemStack item(Material material, int id, int amount,String name){
            ItemStack b = new ItemStack(material, amount, (short) id);
            ItemMeta metaB = b.getItemMeta();                          
            metaB.setDisplayName(name);
            b.setItemMeta(metaB);
            return b;
    }
        public static void adminGui(Player p){
                           Inventory inv = p.getInventory();
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Setup arena"));
                           inv.addItem(item(Material.STAINED_CLAY,5,1,ChatColor.GREEN + "Force START game"));
                           inv.addItem(item(Material.STAINED_CLAY,14,1,ChatColor.GREEN + "Force STOP game"));
                           inv.addItem(item(Material.STAINED_CLAY,1,1,ChatColor.GREEN + "Close"));
                           p.updateInventory();
        }
        public static void SetupInventory(Player p){
                           Inventory inv = p.getInventory();
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Set building time"));
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Set voting time"));
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Set minimum players"));
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Set maximum players"));
                           inv.addItem(item(Material.STAINED_CLAY,3,1,ChatColor.GREEN + "Set lobby spawnpoint"));
                           inv.addItem(item(Material.STAINED_CLAY,4,1,ChatColor.GREEN + "Clear"));
                           inv.addItem(item(Material.STAINED_CLAY,13,1,ChatColor.GREEN + "Next step"));
                           p.updateInventory();
                      }
          public void SetupInventory2(Player p, int id){
                           region = true;
                           Inventory inv = p.getInventory();
                           inv.clear();
                           inv.addItem(item(Material.STAINED_CLAY,10,id,ChatColor.GREEN + "Point A"));
                           inv.addItem(item(Material.STAINED_CLAY,11,id,ChatColor.GREEN + "Point B"));
                           inv.addItem(item(Material.STAINED_CLAY,5,id,ChatColor.GREEN + "Next arena"));
                           p.updateInventory();
          }

          @EventHandler
          public void onInventoryClick2(PlayerInteractEvent event){
              if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ){
                  Player p = event.getPlayer();
                  if(p.getItemInHand().getType() == Material.STAINED_CLAY){
                        String izena = p.getItemInHand().getItemMeta().getDisplayName();
                        ItemStack i = p.getItemInHand();
                        Inventory inve = p.getInventory();
                      if(setup == true){
                           if (izena.equalsIgnoreCase(ChatColor.GREEN + "Set building time")){
                               event.setCancelled(true);
                               time = i.getAmount()+1;
                               inve.remove(i);
                               i.setAmount(i.getAmount()+1);
                               inve.addItem(i);
                               p.updateInventory();
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Set voting time")){
                               event.setCancelled(true);
                               timeVote = i.getAmount()+1;
                               inve.remove(i);
                               i.setAmount(i.getAmount()+1);
                               inve.addItem(i);
                               p.updateInventory();
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Set minimum players")){
                               event.setCancelled(true);
                               players1= i.getAmount()+1;
                               inve.remove(i);
                               i.setAmount(i.getAmount()+1);
                               inve.addItem(i);
                               p.updateInventory();
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Set maximum players")){
                               event.setCancelled(true);
                               players2 = i.getAmount()+1;
                               inve.remove(i);
                               i.setAmount(i.getAmount()+1);
                               inve.addItem(i);
                               p.updateInventory();
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Clear")){
                               event.setCancelled(true);
                               time = 1;
                               players1 = 1;
                               players2 = 1;
                               timeVote = 1;
                               p.getInventory().clear();
                               SetupInventory(p);
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Set lobby spawnpoint")){
                               event.setCancelled(true);
                               l = p.getLocation();
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "Lobby set to: " + p.getLocation());
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Next step")){
                               event.setCancelled(true);
                               if(l == null){
                                   p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.RED + "SpawnPoint missing");
                               }
                               plugin.SaveSpawn(p.getLocation(l),time,players2,players1,timeVote);
                               SetupInventory2(p,1);
                                    }
                           else if (event.getAction() == Action.RIGHT_CLICK_AIR && izena.equalsIgnoreCase(ChatColor.GREEN + "Point A") ||event.getAction() == Action.RIGHT_CLICK_AIR && izena.equalsIgnoreCase(ChatColor.GREEN + "Point B")){
                               event.setCancelled(true);
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "Select the two points placing the blocks" );
                                    }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Next arena")){
                               int id = p.getItemInHand().getAmount();
                               if(location1.get(id) == null || location2.get(id) == null ){
                                   p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.RED + "Points missing" );
                               }
                               else{
                                   plugin.saveSelection(id,location1.get(id),location2.get(id));
                               }
                               if(id != players2){
                                   SetupInventory2(p,id+1);
                               }
                               else{
                                   p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "You finished setting up the game. Now you can start having fun." );
                                   setup = false;
                                   region = false;
                                   plugin.returnInventory(p);
                               }
                           }
                      }
                      else if(plugin.admin == true){
                           if (izena.equalsIgnoreCase(ChatColor.GREEN + "Setup arena")){
                               p.getInventory().clear();
                               SetupInventory(p);
                               setup = true;
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "You have entered the BuildBattle setup:" );
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "Use the setup inventory to set all the game parametres. Use it with out opening the inventory" );
                           }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Force START game")){
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "You forced the game to start" );
                               plugin.hasiera();
                               setup = false;
                               plugin.admin = false;
                               plugin.returnInventory(p);
                           }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Force STOP game")){
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "You forced the game to stop" );
                               plugin.reset();
                               setup = false;
                               plugin.admin = false;
                               plugin.returnInventory(p);
                           }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Close")){
                               setup = false;
                               plugin.admin = false;
                               plugin.returnInventory(p);
                           }
              }else{
                          
                      }
                          
                  }
                  
              }}

          
    @EventHandler 
          public void onBlockPlace(BlockPlaceEvent event){
                   if(setup == true){
                       if(region == true){
                       Player p = event.getPlayer();
                       if(p.getItemInHand().getType() == Material.STAINED_CLAY){
                           String izena = p.getItemInHand().getItemMeta().getDisplayName();
                           int id = p.getItemInHand().getAmount();
                           if (izena.equalsIgnoreCase(ChatColor.GREEN + "Point A")){  
                               event.setCancelled(true);
                               Location l1 = event.getBlock().getLocation();
                               location1.put(id,l1);
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "Point A set to " + l1 );
                           }
                           else if (izena.equalsIgnoreCase(ChatColor.GREEN + "Point B")){
                               event.setCancelled(true);
                               Location l2 = event.getBlock().getLocation();
                               location2.put(id,l2);
                               p.sendMessage(ChatColor.YELLOW + "[BuildBattle]" +ChatColor.GREEN + "Point B set to " + l2 );
                           }
                       }
                   }  
                   }
                    }
}


