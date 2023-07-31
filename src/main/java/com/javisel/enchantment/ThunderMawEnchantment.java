package com.javisel.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class ThunderMawEnchantment extends CollarEnchantmentBase {


    public ThunderMawEnchantment() {
        super(Rarity.VERY_RARE, 1);
        setTreasure();
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);
         World world = user.getEntityWorld();
        if (target instanceof LivingEntity livingEntity) {

            if (world.getRandom().nextInt(101) <= 25*level) {

                LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);

                lightningEntity.setPos(target.getX(), target.getY(), target.getZ());

                if (user instanceof TameableEntity tameableEntity) {


                    if (tameableEntity.getOwner() != null && tameableEntity.getOwner() instanceof ServerPlayerEntity serverPlayerEntity) {

                        lightningEntity.setChanneler(serverPlayerEntity);

                    }

                }

                user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, true, false, false));
                world.spawnEntity(lightningEntity);
            }


        }


    }

    @Override
    public void onUserDamagedWithCollar(int level, LivingEntity user, LivingEntity attacker) {
        super.onUserDamagedWithCollar(level, user, attacker);



    }
}
