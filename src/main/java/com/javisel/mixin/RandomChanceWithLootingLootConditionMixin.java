package com.javisel.mixin;

import com.javisel.registration.EffectRegistration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(RandomChanceWithLootingLootCondition.class)
public class RandomChanceWithLootingLootConditionMixin {


    @ModifyVariable(method = "test", at = @At(value = "STORE"))
    public int process(int i, LootContext context) {


        Entity ent = context.get(LootContextParameters.THIS_ENTITY);

        if (ent != null) {
            if (ent instanceof LivingEntity livingEntity) {

                if (livingEntity.hasStatusEffect(EffectRegistration.BOUNTIFUL)) {

                    i += 1 + livingEntity.getStatusEffect(EffectRegistration.BOUNTIFUL).getAmplifier();


                }


            }
        }


        return i;

    }

}
