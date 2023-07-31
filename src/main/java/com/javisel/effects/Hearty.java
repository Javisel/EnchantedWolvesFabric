package com.javisel.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

public class Hearty extends MobEffectBase {
    public Hearty() {
        super(StatusEffectCategory.BENEFICIAL, 51200);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);



        if (entity.getHealth() < entity.getMaxHealth()) {

            entity.heal(entity.getMaxHealth() * 0.15f);
            double d0 = entity.getRandom().nextGaussian() * 0.02D;
            double d1 = entity.getRandom().nextGaussian() * 0.02D;
            double d2 = entity.getRandom().nextGaussian() * 0.02D;


            if (entity.getWorld() instanceof ServerWorld) {
                ((ServerWorld) entity.getWorld()).spawnParticles(ParticleTypes.HEART, entity.getX() + d0, entity.getBodyY(1), entity.getZ() + d0, 3, d1, 0.0, d2, 0.0);
            }

        }

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

            return  duration % 60 == 0;
     }
}
