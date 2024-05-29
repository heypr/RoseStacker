package dev.rosewood.rosestacker.hook;

import com.nisovin.shopkeepers.api.ShopkeepersAPI;
import dev.rosewood.rosestacker.manager.ConfigurationManager.Setting;
import io.hotmail.com.jacob_vejvoda.infernal_mobs.infernal_mobs;
import io.lumine.mythic.bukkit.MythicBukkit;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import simplepets.brainsynder.api.plugin.SimplePets;

public class NPCsHook {

    private static Boolean mythicMobsEnabled;

    private static Boolean citizensEnabled;
    private static Boolean shopkeepersEnabled;
    private static Boolean epicBossesEnabled;
    private static Boolean eliteMobsEnabled;
    //private static Boolean bossEnabled;
    private static Boolean proCosmeticsEnabled;
    private static Boolean infernalMobsEnabled;
    private static Boolean simplePetsEnabled;

    /**
     * @return true if Citizens is enabled, false otherwise
     */
    public static boolean citizensEnabled() {
        if (citizensEnabled != null)
            return citizensEnabled;

        return citizensEnabled = Bukkit.getPluginManager().isPluginEnabled("Citizens");
    }

    /**
     * @return true if ShopKeepers is enabled, false otherwise
     */
    public static boolean shopkeepersEnabled() {
        if (shopkeepersEnabled != null)
            return shopkeepersEnabled;

        return shopkeepersEnabled = Bukkit.getPluginManager().isPluginEnabled("Shopkeepers");
    }

    /**
     * @return true if MythicMobs is enabled, false otherwise
     */
    public static boolean mythicMobsEnabled() {
        if (mythicMobsEnabled != null)
            return mythicMobsEnabled;

        return mythicMobsEnabled = Bukkit.getPluginManager().isPluginEnabled("MythicMobs");
    }

    /**
     * @return true if EpicBosses is enabled, false otherwise
     */
    public static boolean epicBossesEnabled() {
        if (epicBossesEnabled != null)
            return epicBossesEnabled;

        return epicBossesEnabled = Bukkit.getPluginManager().isPluginEnabled("EpicBosses");
    }

    /**
     * @return true if EliteMobs is enabled, false otherwise
     */
    public static boolean eliteMobsEnabled() {
        if (eliteMobsEnabled != null)
            return eliteMobsEnabled;

        return eliteMobsEnabled = Bukkit.getPluginManager().isPluginEnabled("EliteMobs");
    }

//    /**
//     * @return true if Boss is enabled, false otherwise
//     */
//    public static boolean bossEnabled() {
//        if (bossEnabled != null)
//            return bossEnabled;
//
//        return bossEnabled = Bukkit.getPluginManager().isPluginEnabled("Boss");
//    }

    /**
     * @return true if ProCosmetics is enabled, false otherwise
     */
    public static boolean proCosmeticsEnabled() {
        if (proCosmeticsEnabled != null)
            return proCosmeticsEnabled;

        return proCosmeticsEnabled = Bukkit.getPluginManager().isPluginEnabled("ProCosmetics");
    }

    /**
     * @return true if InfernalMobs is enabled, false otherwise
     */
    public static boolean infernalMobsEnabled() {
        if (infernalMobsEnabled != null)
            return infernalMobsEnabled;

        return infernalMobsEnabled = Bukkit.getPluginManager().isPluginEnabled("InfernalMobs");
    }

    public static boolean simplePetsEnabled() {
        if (simplePetsEnabled != null)
            return simplePetsEnabled;

        boolean enabled = Bukkit.getPluginManager().isPluginEnabled("SimplePets");
        if (!enabled)
            return simplePetsEnabled = false;

        try {
            Class.forName("simplepets.brainsynder.api.plugin.SimplePets");
        } catch (ClassNotFoundException e) {
            return simplePetsEnabled = false;
        }

        return simplePetsEnabled = true;
    }

    /**
     * @return true if any NPC plugin is enabled, false otherwise
     */
    public static boolean anyEnabled() {
        return citizensEnabled()
                || shopkeepersEnabled()
                || mythicMobsEnabled()
                || epicBossesEnabled()
                || eliteMobsEnabled()
//                || bossEnabled()
                || proCosmeticsEnabled()
                || infernalMobsEnabled()
                || simplePetsEnabled();
    }

    /**
     * Checks if a LivingEntity is considered an NPC
     *
     * @param entity The LivingEntity to check
     * @return true if the given LivingEntity is considered an NPC, false otherwise
     */
    public static boolean isNPC(LivingEntity entity) {
        boolean npc = false;

        if (citizensEnabled() && CitizensAPI.hasImplementation())
            npc = CitizensAPI.getNPCRegistry().isNPC(entity);

        if (!npc && shopkeepersEnabled() && ShopkeepersAPI.isEnabled())
            npc = ShopkeepersAPI.getShopkeeperRegistry().isShopkeeper(entity);

        if (!npc && mythicMobsEnabled() && !Setting.MISC_MYTHICMOBS_ALLOW_STACKING.getBoolean())
            npc = MythicBukkit.inst().getAPIHelper().isMythicMob(entity);

//        if (!npc && bossEnabled())
//            npc = BossAPI.isBoss(entity);

        if (!npc && proCosmeticsEnabled())
            npc = entity.hasMetadata("PROCOSMETICS_ENTITY");

        if (!npc && infernalMobsEnabled()) {
            infernal_mobs plugin = ((infernal_mobs) Bukkit.getPluginManager().getPlugin("InfernalMobs"));
            npc = plugin != null && plugin.idSearch(entity.getUniqueId()) >= 0;
        }

        if (!npc && simplePetsEnabled())
            npc = SimplePets.isPetEntity(entity);

        return npc;
    }

}
