package com.javisel.item;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorMaterials;

public class NetheriteCollar extends WolfCollar {

    public NetheriteCollar() {
        super(ArmorMaterials.NETHERITE, new FabricItemSettings().maxDamage(ArmorMaterials.NETHERITE.getDurability(Type.CHESTPLATE)).fireproof());
    }


}
