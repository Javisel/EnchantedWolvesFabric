package com.javisel.enchantment;

import com.javisel.registration.EnchantmentRegistration;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class WholesomePrescenceEnchantment extends CollarEnchantmentBase {
    public WholesomePrescenceEnchantment() {
        super(Rarity.RARE, 1);


        getIncompatibles().add(EnchantmentRegistration.HEARTY);
    }

    @Override
    public void tickWearer(int level, ItemStack stack, LivingEntity user) {
        super.tickWearer(level, stack, user);

        List<LivingEntity> allies = user.getWorld().getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(8));
        if (user instanceof TameableEntity tamedUser && tamedUser.isSitting()) {

        for (LivingEntity ally : allies) {


            boolean buff = ally.isTeammate(user);


            if (tamedUser.getOwner() != null) {

                if (ally == tamedUser.getOwner()) {
                    buff = true;
                }

                if (ally instanceof TameableEntity tameableEntity) {

                    if (tameableEntity.getOwnerUuid().toString().equalsIgnoreCase(tamedUser.getOwnerUuid().toString())) {

                        buff = true;

                    }

                }


            }


            if (buff) {


                ally.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, -1 + level,false,false,true));

            }


        }
        }

    }






}
