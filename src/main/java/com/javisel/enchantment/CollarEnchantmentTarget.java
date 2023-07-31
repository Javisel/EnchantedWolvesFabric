package com.javisel.enchantment;

import com.javisel.item.WolfCollar;
 import com.javisel.registration.ItemRegistration;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class CollarEnchantmentTarget extends EnchantmentTargetMixin {


    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof WolfCollar;
    }
}
@Mixin(EnchantmentTarget.class)
 abstract class EnchantmentTargetMixin {
    @Shadow
    public abstract boolean isAcceptableItem(Item item);
}