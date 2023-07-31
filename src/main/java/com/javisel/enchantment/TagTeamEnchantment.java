package com.javisel.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;

public class TagTeamEnchantment extends CollarEnchantmentBase {


    public TagTeamEnchantment() {
        super(Rarity.VERY_RARE, 1);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        if (user instanceof TameableEntity tameableEntity) {

            if (tameableEntity.getOwner() != null) {

                LivingEntity owner = tameableEntity.getOwner();

                owner.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, level - 1), user);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, level - 1), user);


            }


        }
    }


}
