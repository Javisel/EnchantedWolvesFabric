package com.javisel.enchantment;

import com.javisel.registration.EffectRegistration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class PackLeaderEnchantment extends CollarEnchantmentBase {


    public PackLeaderEnchantment() {
        super(Rarity.VERY_RARE, 1);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        if (user.hasStatusEffect(EffectRegistration.PACK_POWER_COOLDOWN)) {
            return;
        }

         List<LivingEntity> allies = user.getWorld().getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox().expand(8));

        for (LivingEntity ally : allies) {


            boolean buff = ally.isTeammate(user);

            if (user instanceof TameableEntity tamedUser) {

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


            }


            if (buff) {



                ally.addStatusEffect(new StatusEffectInstance(EffectRegistration.PACK_POWER, 100, -1 + level));
                user.addStatusEffect(new StatusEffectInstance(EffectRegistration.PACK_POWER, 100, -1 + level));
                user.addStatusEffect(new StatusEffectInstance(EffectRegistration.PACK_POWER_COOLDOWN, 200));

            }


        }

    }


}
