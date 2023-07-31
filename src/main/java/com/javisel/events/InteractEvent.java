package com.javisel.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public interface InteractEvent {


    Event<InteractEvent> INTERACT = EventFactory.createArrayBacked(InteractEvent.class, (listeners) -> ((player, hand, target) -> {


         for (InteractEvent event : listeners) {
            ActionResult result = event.interact(player, hand, target);

            if (result != ActionResult.PASS) {

                return result;
            }

        }

        return ActionResult.PASS;
    }));

    ActionResult interact(PlayerEntity player, Hand hand, LivingEntity target);


}
