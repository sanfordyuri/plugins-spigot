package impalinha.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class AtackEvent implements Listener {

    @EventHandler
    public void onAttack(EntityTargetEvent e) {
        if(e.getEntity() != null) {
            if(!(e.getEntity() instanceof Player)) {
                if(e.getTarget() != null) {
                    if(!(e.getEntity() instanceof Player)) {
                        e.setCancelled(true);
                    } else {
                        Entity entity = e.getEntity();
                        String customName = entity.getCustomName();
                        if(customName != null) {
                            if(customName.contains("x")) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBurn(EntityCombustEvent e) {
        if(!(e.getEntity() instanceof Player)) {
            if(e.getEntity().getCustomName() != null) {
                String customName = e.getEntity().getCustomName();
                if(customName.contains("x")) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
