package com.javisel.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class WeakeningMawEnchantment extends CollarEnchantmentBase {


    public WeakeningMawEnchantment() {
        super(Rarity.RARE, 1);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);

        if (target instanceof LivingEntity livingEntity) {
            int power = EnchantmentHelper.getLevel(this, collar);

            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, power));


        }
    }


}
