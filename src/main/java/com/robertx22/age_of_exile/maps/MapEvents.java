package com.robertx22.age_of_exile.maps;

import com.robertx22.age_of_exile.mmorpg.ForgeEvents;
import com.robertx22.age_of_exile.uncommon.utilityclasses.WorldUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;

public class MapEvents {

    public static void init() {

        ForgeEvents.registerForgeEvent(BlockEvent.BreakEvent.class, event -> {
            Level level = event.getPlayer().level();

            if (!level.isClientSide) {

                Player player = event.getPlayer();
                if (player.isCreative()) {
                    return;
                }
                if (WorldUtils.isMapWorldClass(level)) {
                    event.setCanceled(true);
                }
            }
        });

        
        ForgeEvents.registerForgeEvent(BlockEvent.EntityPlaceEvent.class, event -> {
            Level level = event.getEntity().level();
            if (!level.isClientSide) {
                if (event.getEntity() instanceof Player p && p.isCreative()) {
                    return;
                }
                if (WorldUtils.isMapWorldClass(level)) {
                    event.setCanceled(true);
                }
            }
        });


        ForgeEvents.registerForgeEvent(LivingEvent.LivingTickEvent.class, x -> {
            if (!x.getEntity().level().isClientSide) {
                if (x.getEntity() instanceof Player p && x.getEntity().level() instanceof ServerLevel sw) {
                    if (p.tickCount == 0 || p.tickCount == 1 || p.tickCount % 20 == 0) {
                        ProcessChunkBlocks.process(sw, x.getEntity().blockPosition());
                    }
                }
            }
        });
    }
}