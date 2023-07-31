package com.javisel.mixin;

import com.javisel.item.WolfCollar;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;

@Mixin(Entity.class)
public class EntityMixin {







    @Inject(method = "applyDamageEffects" , at = @At("HEAD"))
    public void applyDamageEffects(LivingEntity attacker, Entity target, CallbackInfo callbackInfo) {




        if (attacker.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof WolfCollar wolfCollar && target instanceof  LivingEntity livingttarget) {


             wolfCollar.onTargetDamaged(attacker,livingttarget);





        }

        if (target instanceof LivingEntity livingEntity) {


             if (livingEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof WolfCollar wolfCollar) {

                 System.out.println("Target has a collar!");
                wolfCollar.onUserDamaged(livingEntity,attacker);





            }
         }


     }


}
