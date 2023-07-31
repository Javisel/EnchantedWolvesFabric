package com.javisel.mixin;


import com.javisel.registration.EffectRegistration;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {


    @ModifyVariable(method = "dropEquipment(Lnet/minecraft/entity/damage/DamageSource;IZ)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)

    private int injected(int y, DamageSource damageSource, int t, boolean b) {

        LivingEntity livingEntity = (LivingEntity) (Object) this;


        if (livingEntity.hasStatusEffect(EffectRegistration.BOUNTIFUL)) {

            y += 1 + livingEntity.getStatusEffect(EffectRegistration.BOUNTIFUL).getAmplifier();


        }

        return y;

    }



}