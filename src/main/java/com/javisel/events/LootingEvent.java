package com.javisel.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface LootingEvent {

    Event<com.javisel.events.LootingEvent> LOOTING_EVENT = EventFactory.createArrayBacked(com.javisel.events.LootingEvent.class, callbacks -> (LivingEntity, DamageSource) -> {


        int total = 0;
        for (com.javisel.events.LootingEvent event : callbacks) {

            total += event.LootingEvent(LivingEntity, DamageSource);


        }

        return total;

    });

    int LootingEvent(LivingEntity dropper, DamageSource source);
}

