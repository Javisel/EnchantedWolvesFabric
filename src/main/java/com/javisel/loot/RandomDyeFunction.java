package com.javisel.loot;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.random.Random;


public class RandomDyeFunction extends ConditionalLootFunction {


    public RandomDyeFunction(LootCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {

        NbtCompound nbtCompound = stack.getOrCreateNbt();


        nbtCompound.put("display",getRandomlyDyedCollar(context.getRandom()));

        stack.setNbt(nbtCompound);
        return stack;
    }

    @Override
    public LootFunctionType getType() {
        return null;
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

        return displaytag;

    }

    public static RandomDyeFunction.Builder<?> builder() {
        return RandomDyeFunction.builder((LootCondition[] conditions) -> new RandomDyeFunction((LootCondition[])conditions));
    }
}
