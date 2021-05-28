package fr.realcraft.plugin.ecoapi;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EcoTransfert {

    public static void PlayerToPlayer(Player player1, Player player2, double amount, Player operator) {
        double ecoplayer1 = EcoSQL.getEco(player1);

        if(ecoplayer1 >= amount) {
            EcoSQL.removeEco(player1, amount);
            EcoSQL.addEco(player2, amount);
        } else {
            operator.sendMessage(ChatColor.RED + "Operation Impossible");
        }
    }
}
