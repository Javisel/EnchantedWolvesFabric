package com.javisel.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class FrostbiteEnchantment extends CollarEnchantmentBase {


    public FrostbiteEnchantment() {
        super(Rarity.UNCOMMON, 3);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);

         ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);

        if (target instanceof LivingEntity livingEntity && !livingEntity.hasStatusEffect(StatusEffects.SLOWNESS)) {
            int power = EnchantmentHelper.getLevel(this, collar);

            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, -1 + power));

            if (user.getWorld() instanceof ServerWorld serverWorld) {
                double d0 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d1 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
                double d2 = (double)(user.getRandom().nextFloat() * 2.0F - 1.0F);
             serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE, target.getX(), target.getBodyY(0.5), target.getZ() , 5, 0, 0.0, 0, 0.25);

                serverWorld.playSound(target.getX(),target.getY(),target.getZ(), SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.AMBIENT,5,1,true);

            }


        }


    }




}
