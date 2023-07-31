package com.javisel;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;

public class Utilities {




    public static boolean isEntityOwner(TameableEntity tameableEntity, LivingEntity livingEntity) {




        return  tameableEntity.getOwner() == livingEntity;


    }
}
