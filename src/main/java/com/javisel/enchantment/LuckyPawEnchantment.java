package com.javisel.enchantment;

import com.javisel.registration.EffectRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class LuckyPawEnchantment extends CollarEnchantmentBase {


    public LuckyPawEnchantment() {
        super(Rarity.RARE, 3);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);

        if (target instanceof LivingEntity livingEntity && !livingEntity.hasStatusEffect(EffectRegistration.BOUNTIFUL)) {
            int power = EnchantmentHelper.getLevel(this, collar);

            livingEntity.addStatusEffect(new StatusEffectInstance(EffectRegistration.BOUNTIFUL, 100, -1 + power));
            if (user.getWorld() instanceof ServerWorld) {
                double d0 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d1 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d2 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                ((ServerWorld)user.getWorld()).spawnParticles(ParticleTypes.GLOW, target.getX(), target.getBodyY(0.5), target.getZ() , 5, 0, 0.0, 0, 0.25);

            }

        }

    }


}
