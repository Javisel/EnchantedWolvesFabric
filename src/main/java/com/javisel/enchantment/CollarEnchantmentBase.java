package com.javisel.enchantment;

import com.javisel.item.ModTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class CollarEnchantmentBase extends Enchantment {


    public static EquipmentSlot[] EQUIPMENT_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD};

    private final ArrayList<Enchantment> incompatibles = new ArrayList();
    private final int maxLevel;

    private boolean isTreasure = false;

    public CollarEnchantmentBase(Rarity rarityIn, int maxLevelin) {
        super(rarityIn, EnchantmentTarget.valueOf("COLLAR"), EQUIPMENT_SLOTS);
        maxLevel = maxLevelin;

    }

    public CollarEnchantmentBase setTreasure() {

        isTreasure = true;

        return this;
    }


    public void tickWearer(int level, ItemStack stack,LivingEntity user) {



    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {



        return stack.isIn(ModTags.COLLARS) || stack.getItem() == Items.BOOK;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }


    @Override
    protected boolean canAccept(Enchantment enchantment) {

        return super.canAccept(enchantment) && !incompatibles.contains(enchantment);

    }

    public float getAttackDamageFromCollar(int level, LivingEntity target) {



        return  0.0f;
    }

    public void onTargetDamagedWithCollar(int level, LivingEntity user, Entity target) {




    }

    public void onUserDamagedWithCollar(int level, LivingEntity user, LivingEntity attacker) {




    }



    @Override
    public boolean isTreasure() {
        return super.isTreasure() || isTreasure;
    }


    public ArrayList<Enchantment> getIncompatibles() {
        return incompatibles;
    }


}
