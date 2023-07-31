package com.javisel.registration;


import com.javisel.item.LeatherWolfCollar;
import com.javisel.item.NetheriteCollar;
import com.javisel.item.WolfCollar;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.javisel.EnchantedWolves.MODID;


public class ItemRegistration {

    public static final Item LEATHER_COLLAR = new LeatherWolfCollar();

    public static final Item IRON_COLLAR = new WolfCollar(ArmorMaterials.IRON);
    public static final Item GOLD_COLLAR = new WolfCollar(ArmorMaterials.GOLD);
    public static final Item CHAINMAIL_COLLAR = new WolfCollar(ArmorMaterials.CHAIN);
    public static final Item DIAMOND_COLLAR = new WolfCollar(ArmorMaterials.DIAMOND);
    public static final Item NETHERITE_COLLAR = new NetheriteCollar();


    public static void registerItems() {

        Registry.register(Registries.ITEM, new Identifier(MODID, "leather_collar"), LEATHER_COLLAR);

        Registry.register(Registries.ITEM, new Identifier(MODID, "iron_collar"), IRON_COLLAR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "golden_collar"), GOLD_COLLAR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "chainmail_collar"), CHAINMAIL_COLLAR);

        Registry.register(Registries.ITEM, new Identifier(MODID, "diamond_collar"), DIAMOND_COLLAR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_collar"), NETHERITE_COLLAR);


    }


}