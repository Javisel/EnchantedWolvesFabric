package com.javisel.mixin;


import com.javisel.events.InteractEvent;
import com.javisel.item.WolfCollar;
import com.javisel.registration.EffectRegistration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {



    @Inject(at = @At(value = "HEAD", target = "Lnet/minecraft/entity/mob/MobEntity;interactMob(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;"), method = "interactMob", cancellable = true)
    public ActionResult interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable callbackInfo) {


        ActionResult result = InteractEvent.INTERACT.invoker().interact(player, hand, (MobEntity) (Object) this);


        return result;
    }

    @Inject(at=@At("HEAD"), method = "tick")

    public void tickArmour(CallbackInfo callbackInfo) {

        MobEntity entity = (MobEntity) (Object) this;


         if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof WolfCollar wolfCollar) {


            wolfCollar.wornTick(entity.getEquippedStack(EquipmentSlot.HEAD),entity);


        }


    }




    //Applies Attack Damage bonuses from Wolf Collar
    @ModifyVariable(at = @At(value = "STORE"), method = "tryAttack")

    public float attackDamage(float f, Entity target) {

         MobEntity mobEntity = (MobEntity) (Object) this;


         if (target instanceof LivingEntity livingEntity) {


             if (mobEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof WolfCollar wolfCollar) {





                          f+= wolfCollar.getAttackDamage(mobEntity,livingEntity);


                 if (f  > 0) {


                     if (mobEntity.getWorld() instanceof ServerWorld) {
                         double d0 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                         double d1 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                         double d2 = (double)(mobEntity.getRandom().nextFloat() * 2.0F - 1.0F);
                         ((ServerWorld)mobEntity.getWorld()).spawnParticles(ParticleTypes.ENCHANTED_HIT, target.getX(), target.getBodyY(0.5), target.getZ() , (int)f, d0, d1, d2, 0.25);
                     }



                 }



            }

        }


        return f;

    }



}
