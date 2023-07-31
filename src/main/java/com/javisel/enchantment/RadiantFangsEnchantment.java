package com.javisel.enchantment;

import com.javisel.registration.EffectRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class RadiantFangsEnchantment extends CollarEnchantmentBase {


    public RadiantFangsEnchantment() {
        super(Rarity.UNCOMMON, 2);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);

        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);
        if (target instanceof LivingEntity livingEntity && (!livingEntity.hasStatusEffect(StatusEffects.GLOWING) && livingEntity.hasStatusEffect(EffectRegistration.VULNERABLE))) {
            int power = EnchantmentHelper.getLevel(this, collar);

            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100));
            livingEntity.addStatusEffect(new StatusEffectInstance(EffectRegistration.VULNERABLE, 100, -1 + power));
            if (user.getWorld() instanceof ServerWorld) {
                double d0 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d1 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d2 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                ((ServerWorld)user.getWorld()).spawnParticles(ParticleTypes.GLOW, target.getX(), target.getBodyY(0.5), target.getZ() , 5, 0, 0.0, 0, 0.25);
            }

        }

    }

}
