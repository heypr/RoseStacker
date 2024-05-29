package dev.rosewood.rosestacker.listener;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosestacker.manager.ConfigurationManager.Setting;
import dev.rosewood.rosestacker.manager.StackManager;
import dev.rosewood.rosestacker.stack.StackingThread;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRemoveEvent;

public class ClearlagListener implements Listener {

    private final RosePlugin rosePlugin;

    public ClearlagListener(RosePlugin rosePlugin) {
        this.rosePlugin = rosePlugin;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onClear(EntityRemoveEvent event) {
        StackManager stackManager = this.rosePlugin.getManager(StackManager.class);
        StackingThread stackingThread = stackManager.getStackingThread(event.getEntity().getWorld());
        if (stackingThread == null)
            return;

        if (Setting.MISC_CLEARLAG_CLEAR_ENTITIES.getBoolean() && stackManager.isEntityStackingEnabled())
            stackingThread.removeAllEntityStacks();

        if (Setting.MISC_CLEARLAG_CLEAR_ITEMS.getBoolean() && stackManager.isItemStackingEnabled())
            stackingThread.removeAllItemStacks();
    }

}
