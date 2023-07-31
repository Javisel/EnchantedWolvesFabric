package com.javisel.mixin;


import com.javisel.item.WolfCollar;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {





    @Inject(at = @At(value = "HEAD"), method = "tryAttack")

    public boolean tryAttack(Entity target, CallbackInfoReturnable callbackInfo) {

        WolfEntity mobEntity = (WolfEntity) (Object) this;


        float f = 0;
        if (target instanceof LivingEntity livingEntity) {


            if (mobEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof WolfCollar wolfCollar) {

                ItemStack collarStack = mobEntity.getEquippedStack(EquipmentSlot.HEAD);


                f+= wolfCollar.getAttackDamage(mobEntity,target);

            }

         }






        boolean bl = target.damage(mobEntity.getDamageSources().mobAttack(mobEntity), (float) (f + mobEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));



         if (bl) {

             mobEntity.applyDamageEffects(mobEntity,target);
             if (f>0) {
                 if (mobEntity.getWorld() instanceof ServerWorld) {
                     double d0 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                     double d1 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                     double d2 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                     ((ServerWorld)mobEntity.getWorld()).spawnParticles(ParticleTypes.ENCHANTED_HIT, target.getX(), target.getBodyY(0.5), target.getZ() , (int)f, d0, d1, d2, 0.25);
                 }


             }
        }
         return bl;

    }





}
