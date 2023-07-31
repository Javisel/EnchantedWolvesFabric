package com.javisel.enchantment;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;


public class InfernalEnchantment extends CollarEnchantmentBase {


    public InfernalEnchantment() {
        super(Rarity.RARE, 1);
    }

    @Override
    public int getProtectionAmount(int level, DamageSource source) {

        if (source.isIn(DamageTypeTags.IS_FIRE)) {
            return 10000;
        }
        return super.getProtectionAmount(level, source);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);

        if (target instanceof LivingEntity livingEntity) {

            int power = EnchantmentHelper.getLevel(this, collar);
            livingEntity.setFireTicks(60 + (20 * power));


        }

    }

    @Override
    public void tickWearer(int level, ItemStack stack, LivingEntity user) {
        super.tickWearer(level, stack, user);

        if (user.isOnFire()) {

            user.extinguishWithSound();

        }
    }
}
