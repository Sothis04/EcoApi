package fr.realcraft.plugin.ecoapi;

import fr.realcraft.plugin.data.commons.Profile;
import fr.realcraft.plugin.data.core.ProfileProvider;
import fr.realcraft.plugin.data.management.exceptions.ProfileNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EcoSQL {

    public static double getEco(Player player) {
        double eco_player = 0;

        try {
            final ProfileProvider profileProvider = new ProfileProvider(player);
            final Profile profile = profileProvider.getProfile();

            eco_player = profile.getCoins();

            if(eco_player < 0) {
                Bukkit.broadcast(ChatColor.RED + "Le joueur" + ChatColor.GOLD + player.getDisplayName() + ChatColor.RED + "est passé en négatif", "intercraft.admin");
                resetEco(player);
            }
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }

        return eco_player;
    }

    public static void setEco(Player player, double coins) {
        try {
            final ProfileProvider profileProvider = new ProfileProvider(player);
            final Profile profile = profileProvider.getProfile();

            profile.setCoins(coins);

            profileProvider.sendProfileToRedis(profile);
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addEco(Player player, double coins) {
        try {
            final ProfileProvider profileProvider = new ProfileProvider(player);
            final Profile profile = profileProvider.getProfile();
            final double ecodb = getEco(player);
            final double addeco = ecodb + coins;

            profile.setCoins(addeco);

            profileProvider.sendProfileToRedis(profile);
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void removeEco(Player player, double coins) {
        try {
            final ProfileProvider profileProvider = new ProfileProvider(player);
            final Profile profile = profileProvider.getProfile();
            final double ecodb = getEco(player);
            final double removeeco = ecodb - coins;

            profile.setCoins(removeeco);

            profileProvider.sendProfileToRedis(profile);
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void resetEco(Player player) {
        try {
            final ProfileProvider profileProvider = new ProfileProvider(player);
            final Profile profile = profileProvider.getProfile();

            double reset = 0.0;

            profile.setCoins(reset);

            profileProvider.sendProfileToRedis(profile);
        } catch (ProfileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
