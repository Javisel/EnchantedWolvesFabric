package com.javisel.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;

public class CleavingJawEnchantment extends CollarEnchantmentBase {


    public CleavingJawEnchantment() {
        super(Rarity.COMMON, 3);
    }

    @Override
    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {
        super.onTargetDamagedWithCollar(level, user, target);
        ItemStack collar = user.getEquippedStack(EquipmentSlot.HEAD);

         if (target != null) {


            int power = EnchantmentHelper.getLevel(this, collar);

            float f3 = (float) (0.25 * power);

            int i = 0;
            for (LivingEntity livingentity : target.getWorld().getEntitiesByClass(LivingEntity.class, target.getBoundingBox().expand(1.0D, 0.5D, 1.0D), LivingEntity::canTakeDamage)) {

                i++;
                boolean strikeable = true;
                if (user instanceof TameableEntity tamed) {


                    if (tamed.getOwner() != null) {

                        if (tamed.getOwner() == livingentity) {
                            strikeable = false;
                        }
                    }

                }


                if (strikeable && livingentity != target && livingentity != target && !user.isTeammate(livingentity) && (!(livingentity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingentity).isMarker()) && user.squaredDistanceTo(livingentity) < 9.0D) {
                    livingentity.takeKnockback(0.4F, MathHelper.sin(user.getHeadYaw() * ((float) Math.PI / 180F)), -MathHelper.cos(user.getHeadYaw() * ((float) Math.PI / 180F)));
                    livingentity.damage(user.getEntityWorld().getDamageSources().mobAttack(user), (float) (f3 * user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));

                }

                double d = (double)(-MathHelper.sin(user.getYaw() * 0.017453292F));
                double e = (double)MathHelper.cos(user.getYaw() * 0.017453292F);

                if (user.getWorld() instanceof ServerWorld) {
                    ((ServerWorld)user.getWorld()).spawnParticles(ParticleTypes.SWEEP_ATTACK, user.getX() + d, user.getBodyY(0.5), user.getZ() + e, 0, d, 0.0, e, 0.0);
                }

            }


        }

    }



}


