package com.javisel.loot;

import com.javisel.registration.EnchantmentRegistration;
import com.javisel.registration.ItemRegistration;
import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntryTypes;
import net.minecraft.loot.function.*;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.ArrayList;
import java.util.Random;

public class LootTableModifier {



   static  ArrayList<Identifier> LEATHER_IDENTIFIERS = new ArrayList<>();


    static  {

        LEATHER_IDENTIFIERS.add(LootTables.VILLAGE_TAIGA_HOUSE_CHEST);
        LEATHER_IDENTIFIERS.add(LootTables.JUNGLE_TEMPLE_CHEST);
        LEATHER_IDENTIFIERS.add(LootTables.VILLAGE_TANNERY_CHEST);


    }



        public static void initialize() {


            LootTableEvents.MODIFY.register(new LootTableEvents.Modify() {
                @Override
                public void modifyLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id, LootTable.Builder tableBuilder, LootTableSource source) {



                     //Dyed leather colours
                    for (Identifier ide : LEATHER_IDENTIFIERS) {
                        if (ide.equals(id)) {


                            LootPool.Builder poolBuilder = LootPool.builder();


                              poolBuilder = poolBuilder.with(
                                        ItemEntry.builder(ItemRegistration.LEATHER_COLLAR).conditionally(RandomChanceLootCondition.builder(0.15f)).apply(RandomDyeFunction.builder()));

                            tableBuilder.pool(poolBuilder);

                       }

                    }
                    if (id.equals( LootTables.SIMPLE_DUNGEON_CHEST)) {

                        LootPool.Builder poolBuilder = LootPool.builder();

                        poolBuilder = poolBuilder.with(
                                ItemEntry.builder(ItemRegistration.LEATHER_COLLAR).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(25,125))));



                        poolBuilder = poolBuilder.with(
                                ItemEntry.builder(ItemRegistration.CHAINMAIL_COLLAR).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(25,250))));
                        tableBuilder.pool(poolBuilder);


                    }
                    if (id .equals( LootTables.END_CITY_TREASURE_CHEST)) {

                        LootPool.Builder poolBuilder = LootPool.builder();

                        poolBuilder = poolBuilder.with(
                                ItemEntry.builder(ItemRegistration.DIAMOND_COLLAR).weight(5).apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20,39))));

                        poolBuilder = poolBuilder.with(
                                ItemEntry.builder(ItemRegistration.IRON_COLLAR).weight(3).apply(EnchantWithLevelsLootFunction.builder(UniformLootNumberProvider.create(20,39))));



                         SetEnchantmentsLootFunction.Builder builder = new SetEnchantmentsLootFunction.Builder().enchantment(EnchantmentRegistration.RESURRECTION, UniformLootNumberProvider.create(1,3));


                        poolBuilder = poolBuilder.with(ItemEntry.builder(Items.ENCHANTED_BOOK).conditionally(RandomChanceLootCondition.builder(0.10f)).apply(builder));



                        tableBuilder.pool(poolBuilder);


                    }
                    if (id .equals( LootTables.STRONGHOLD_LIBRARY_CHEST)) {

                        LootPool.Builder poolBuilder = LootPool.builder();


                        SetEnchantmentsLootFunction.Builder builder = new SetEnchantmentsLootFunction.Builder().enchantment(EnchantmentRegistration.THUNDER_MAW, UniformLootNumberProvider.create(1,1));


                        poolBuilder = poolBuilder.with(ItemEntry.builder(Items.ENCHANTED_BOOK).conditionally(RandomChanceLootCondition.builder(0.3f)).apply(builder));



                        tableBuilder.pool(poolBuilder);


                    }
                    if (id .equals( LootTables.BASTION_TREASURE_CHEST)) {

                        LootPool.Builder poolBuilder = LootPool.builder();


                        SetEnchantmentsLootFunction.Builder builder = new SetEnchantmentsLootFunction.Builder().enchantment(EnchantmentRegistration.RESURRECTION, UniformLootNumberProvider.create(1,3));


                        poolBuilder = poolBuilder.with(ItemEntry.builder(Items.ENCHANTED_BOOK).weight(1).apply(builder));



                        tableBuilder.pool(poolBuilder);


                    }
                }




            });

        }



    public static NbtCompound getRandomlyDyedCollar(Random random) {

        float red = random.nextInt((101));
        float green = random.nextInt((101));
        float blue = random.nextInt((101));


        red /= 100;
        blue /= 100;
        green /= 100;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;

        int i2 = (int)(red * 255.0F);
        int l = (int)(green* 255.0F);
        int i1 = (int)(blue * 255.0F);
        i += Math.max(i2, Math.max(l, i1));
        aint[0] += i2;
        aint[1] += l;
        aint[2] += i1;
        ++j;




        int j1 = aint[0] / j;
        int k1 = aint[1] / j;
        int l1 = aint[2] / j;
        float f3 = (float)i / (float)j;
        float f4 = (float)Math.max(j1, Math.max(k1, l1));
        j1 = (int)((float)j1 * f3 / f4);
        k1 = (int)((float)k1 * f3 / f4);
        l1 = (int)((float)l1 * f3 / f4);
        int j2 = (j1 << 8) + k1;
        j2 = (j2 << 8) + l1;


        NbtCompound displaytag = new NbtCompound();
        displaytag.putInt("color",j2);
        NbtCompound returnPound = new NbtCompound();
        returnPound.put("display",displaytag);
        return returnPound;

    }



}
