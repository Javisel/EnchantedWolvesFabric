package com.javisel.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.EnchantedWolves;
import com.javisel.enchantment.CollarEnchantmentBase;
import com.javisel.registration.EnchantmentRegistration;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.DispenserBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WolfCollar extends ArmorItem {

    private static final UUID[] ARMOR_MODIFIERS = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    // private final Multimap<Attribute, AttributeModifier> collarModifiers;
    private static final UUID collarids = UUID.fromString("48d08e37-770d-4f7e-826b-9563b2fcf7e0");
    private final ArmorMaterial material;
    private final float damageReduction;
    private final float toughness;
    private final float antiknockback;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributes;


    public WolfCollar(ArmorMaterial materialIn) {
        super(materialIn, Type.HELMET, new FabricItemSettings().maxDamage(materialIn.getDurability(Type.HELMET)));
        this.material = materialIn;
        this.damageReduction = (float) (materialIn.getProtection(Type.HELMET) + materialIn.getProtection(Type.CHESTPLATE) + materialIn.getProtection(Type.LEGGINGS) + materialIn.getProtection(Type.BOOTS)) / 2;

        this.toughness = materialIn.getToughness() / 2;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(collarids, "Armor modifier", this.damageReduction, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(collarids, "Armor toughness", this.toughness, EntityAttributeModifier.Operation.ADDITION));

        antiknockback = material.getKnockbackResistance();
        if (this.knockbackResistance > 0) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(collarids, "Armor knockback resistance", this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }


        this.attributes = builder.build();
    }

    public WolfCollar(ArmorMaterial materialIn, FabricItemSettings properties) {
        super(materialIn, Type.HELMET, properties);
        this.material = materialIn;
        this.damageReduction = (float) (materialIn.getProtection(Type.HELMET) + materialIn.getProtection(Type.CHESTPLATE) + materialIn.getProtection(Type.LEGGINGS) + materialIn.getProtection(Type.BOOTS)) / 2;
        this.toughness = materialIn.getToughness() / 2;
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(collarids, "Armor modifier", this.damageReduction, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(collarids, "Armor toughness", this.toughness, EntityAttributeModifier.Operation.ADDITION));

        antiknockback = material.getKnockbackResistance();
        if (this.knockbackResistance > 0) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(collarids, "Armor knockback resistance", this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION));
        }


        this.attributes = builder.build();
    }

    public static boolean dispenseArmor(BlockPointer pointer, ItemStack armor) {
        BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        List<LivingEntity> list = pointer.getWorld().getEntitiesByClass(LivingEntity.class, new Box(blockPos), EntityPredicates.EXCEPT_SPECTATOR.and(new EntityPredicates.Equipable(armor)));
        if (list.isEmpty()) {
            return false;
        } else {
            LivingEntity livingEntity = list.get(0);
            EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(armor);
            ItemStack itemStack = armor.split(1);
            livingEntity.equipStack(equipmentSlot, itemStack);
            if (EnchantedWolves.canWearCollar(livingEntity)) {
                ((MobEntity) livingEntity).setEquipmentDropChance(equipmentSlot, 2.0F);
                ((MobEntity) livingEntity).setPersistent();
            }

            return true;
        }
    }

    @Nullable
    static Equipment fromStack(ItemStack stack) {


        return null;
    }


     public void wornTick(ItemStack stack, Entity entity) {
         if (entity instanceof LivingEntity livingEntity) {




                 Map<Enchantment,Integer> enchantmentIntegerHashMap = EnchantmentHelper.get(livingEntity.getEquippedStack(EquipmentSlot.HEAD));



                 for (Enchantment enchantment : enchantmentIntegerHashMap.keySet()) {



                     if (enchantment instanceof CollarEnchantmentBase collarEnchantmentBase) {


                         collarEnchantmentBase.tickWearer(enchantmentIntegerHashMap.get(enchantment),stack,livingEntity);

                     }


                 }







         }

    }






    public float getAttackDamage(LivingEntity user, Entity target) {


        Map<Enchantment,Integer> enchantmentIntegerHashMap = EnchantmentHelper.get(user.getEquippedStack(EquipmentSlot.HEAD));


        float f=0;

        for (Enchantment enchantment : enchantmentIntegerHashMap.keySet()) {


            if (enchantment instanceof CollarEnchantmentBase collarEnchantmentBase && target instanceof LivingEntity livingTarget) {


                f+=collarEnchantmentBase.getAttackDamageFromCollar(enchantmentIntegerHashMap.get(enchantment),livingTarget);

            }


        }


        return f;
    }

    public void onTargetDamaged(LivingEntity user, LivingEntity target) {

        Map<Enchantment,Integer> enchantmentIntegerHashMap = EnchantmentHelper.get(user.getEquippedStack(EquipmentSlot.HEAD));





        for (Enchantment enchantment : enchantmentIntegerHashMap.keySet()) {


             if (enchantment instanceof CollarEnchantmentBase collarEnchantmentBase) {


                collarEnchantmentBase.onTargetDamagedWithCollar(enchantmentIntegerHashMap.get(enchantment),user,target);
            }


        }
        return ;
    }

    public void onUserDamaged(LivingEntity user, LivingEntity attacker) {

        Map<Enchantment,Integer> enchantmentIntegerHashMap = EnchantmentHelper.get(user.getEquippedStack(EquipmentSlot.HEAD));



        for (Enchantment enchantment : enchantmentIntegerHashMap.keySet()) {


            if (enchantment instanceof CollarEnchantmentBase collarEnchantmentBase) {



                collarEnchantmentBase.onUserDamagedWithCollar(enchantmentIntegerHashMap.get(enchantment),user,attacker);

            }


        }

    }

    @Override
    public TypedActionResult<ItemStack> equipAndSwap(Item item, World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.fail(user.getStackInHand(hand));
    }


    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {





        if (slot ==EquipmentSlot.HEAD) return  attributes;
        return super.getAttributeModifiers(slot);
    }
}
