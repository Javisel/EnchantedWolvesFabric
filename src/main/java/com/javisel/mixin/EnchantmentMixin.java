package com.javisel.mixin;


import com.javisel.item.WolfCollar;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(at = @At(value = "HEAD"), method = "isAcceptableItem", cancellable = true)
    void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        Enchantment enchantment = (Enchantment) (Object) this;

        if (enchantment == Enchantments.FIRE_PROTECTION && stack.getItem() instanceof WolfCollar) {

           callbackInfo.setReturnValue(false);

        }



     }




}
