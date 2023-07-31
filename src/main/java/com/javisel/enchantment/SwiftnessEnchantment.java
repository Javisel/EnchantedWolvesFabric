package com.javisel.enchantment;

import com.javisel.registration.EffectRegistration;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class SwiftnessEnchantment extends CollarEnchantmentBase {
    public SwiftnessEnchantment() {
        super(Rarity.UNCOMMON, 3);
    }



    @Override
    public void tickWearer(int level, ItemStack stack, LivingEntity user) {
        super.tickWearer(level, stack, user);


        if (!user.hasStatusEffect(EffectRegistration.SWIFTNESS)) {

            user.addStatusEffect(new StatusEffectInstance(EffectRegistration.SWIFTNESS, 60, level - 1, false, false, false));
        }
    }
}
