package com.javisel.mixin;


import com.javisel.registration.EffectRegistration;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LootingEnchantLootFunction.class)
public class LootingEnchantLootFunctionMixin {


    @ModifyVariable(method = "process", at = @At(value = "STORE"))
    public int process(int i, ItemStack stack, LootContext context) {


        Entity ent = context.get(LootContextParameters.THIS_ENTITY);

        if (ent != null && ent instanceof LivingEntity livingEntity) {

            if (livingEntity.hasStatusEffect(EffectRegistration.BOUNTIFUL)) {

                i += (livingEntity.getStatusEffect(EffectRegistration.BOUNTIFUL).getAmplifier() + 1);

            }


        }


        return i;

    }


}
