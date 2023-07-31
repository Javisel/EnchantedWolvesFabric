package com.javisel.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class Bountiful extends StatusEffect {
    public Bountiful() {
        super(StatusEffectCategory.HARMFUL, 16776960);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        if (entity.getWorld() instanceof ServerWorld) {
            double d0 = (double)(entity.getRandom().nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(entity.getRandom().nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(entity.getRandom().nextFloat() * 2.0F - 1.0F);
            ((ServerWorld)entity.getWorld()).spawnParticles(ParticleTypes.GLOW, entity.getX(), entity.getBodyY(0.5), entity.getZ() , 5, 0, 0.0, 0, 0.25);

        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        
        return (duration %10 == 0);
        
        
    }
}
