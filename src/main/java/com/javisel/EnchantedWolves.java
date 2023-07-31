package com.javisel;

import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;
import com.javisel.events.InteractEvent;
import com.javisel.item.ModTags;
import com.javisel.item.WolfCollar;
import com.javisel.loot.LootTableModifier;
import com.javisel.registration.EffectRegistration;
import com.javisel.registration.EnchantmentRegistration;
import com.javisel.registration.ItemRegistration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.*;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class EnchantedWolves implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("data/enchantedwolves");
    public static final String MODID = "enchantedwolves";

    private static final ItemGroup ENCHANTED_WOLVES_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemRegistration.DIAMOND_COLLAR))
            .displayName(Text.translatable("itemGroup.enchantedwolves"))
            .build();
    public static boolean canWearCollar(LivingEntity target) {


        return target.getType().isIn(ModTags.COLLAR_WEARERS);
    }


    @Override
    public void onInitialize() {
        LOGGER.info("Enchanting Wolves!");
         ItemRegistration.registerItems();
        EnchantmentRegistration.registerEnchantments();

        EffectRegistration.registerEffects();


        Registry.register(Registries.ITEM_GROUP,new Identifier(MODID,"enchanted_wolves"), ENCHANTED_WOLVES_GROUP);


        EnchantmentTarget etarget = ClassTinkerers.getEnum(EnchantmentTarget.class, "COLLAR");







        ItemGroupEvents.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(ENCHANTED_WOLVES_GROUP).get()).register(content -> {

            ArrayList<ItemStack> books = new ArrayList<>();


            for (RegistryEntry<Item> item : Registries.ITEM.getEntryList(ModTags.COLLARS).get()) {

                ItemStack stack = new ItemStack(item);
                books.add(stack);
            }


            for (RegistryKey key : Registries.ENCHANTMENT.getKeys()) {

                if (key.getValue().getNamespace().equalsIgnoreCase( MODID)){

                    Enchantment enchantment = Registries.ENCHANTMENT.get(key);


                    int x = 0;
                    while (x< enchantment.getMaxLevel()) {

                        x++;
                        ItemStack stack  = new ItemStack(Items.ENCHANTED_BOOK);

                        EnchantedBookItem.addEnchantment(stack,new EnchantmentLevelEntry(enchantment,x));


                        books.add(stack);

                    }

                }

            }




            content.addAll(books);
         });




        ServerEntityEvents.EQUIPMENT_CHANGE.register((livingEntity, equipmentSlot, previousStack, currentStack) -> {


            if (!equipmentSlot.isArmorSlot()) {
                return;
            } else {
                if (currentStack.getItem() instanceof WolfCollar) {
                    if (!canWearCollar(livingEntity)) {

                        ItemStack drop = currentStack.copyAndEmpty();

                        livingEntity.dropStack(drop);

                        if (livingEntity instanceof ServerPlayerEntity serverPlayerEntity) {

                            serverPlayerEntity.sendMessage(Text.translatable(MODID + ".message.cantequip"));

                        }

                    }

                }
            }

        });



            ServerLivingEntityEvents.ALLOW_DEATH.register(new ServerLivingEntityEvents.AllowDeath() {
                @Override
                public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
                    return false;
                }
            });

        LootTableModifier.initialize();

        //Damage taken
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ServerLivingEntityEvents.AllowDamage() {








            @Override
            public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
                ItemStack defenderHeadStack = entity.getEquippedStack(EquipmentSlot.HEAD);


                if (defenderHeadStack.getItem() instanceof WolfCollar) {



                    defenderHeadStack.damage(1,entity.getRandom(),null);
                    //Thundermaw provides Lightning Immunity
                    if (source.isIn(DamageTypeTags.IS_LIGHTNING) && EnchantmentHelper.getLevel(EnchantmentRegistration.THUNDER_MAW,defenderHeadStack) > 0) {

                        return false;
                    }

                    //Collared entities cannot be harmed by their owner
                    if (source.getAttacker() !=null && source.getAttacker() instanceof LivingEntity) {


                        if (entity instanceof TameableEntity tameableEntity) {


                            if (tameableEntity.getOwner() == source.getAttacker()) {


                            return false;
                            }

                        }





                    }


                }


                //Damage intaken effects


                //Vulnerable damage increase
                if (entity.hasStatusEffect(EffectRegistration.VULNERABLE)) {

                    int power = 1 + entity.getStatusEffect(EffectRegistration.VULNERABLE).getAmplifier();

                    amount *= (float) (1 + (0.10 * power));

                }


                //Infernal fire immunity
                if (EnchantmentHelper.getLevel(EnchantmentRegistration.INFERNAL, defenderHeadStack) > 0 && source.isIn(DamageTypeTags.IS_FIRE)) {


                    return false;
                }

                //Attacker Effects

                if (source.getAttacker() != null) {

                    if (source.getAttacker() instanceof LivingEntity livingAttacker) {

                        ItemStack attackerhelmet = livingAttacker.getEquippedStack(EquipmentSlot.HEAD);


                        //Leech Fangs Bonus
                        if (EnchantmentHelper.getLevel(EnchantmentRegistration.LEECH_FANGS, attackerhelmet) > 0) {

                            int power = EnchantmentHelper.getLevel(EnchantmentRegistration.LEECH_FANGS, attackerhelmet);


                            float heal = amount * (0.15f * power);
                            livingAttacker.heal(heal);
                            double d0 = livingAttacker.getRandom().nextGaussian() * 0.02D;
                            double d1 = livingAttacker.getRandom().nextGaussian() * 0.02D;
                            double d2 = livingAttacker.getRandom().nextGaussian() * 0.02D;


                            if (livingAttacker.getWorld() instanceof ServerWorld) {
                                ((ServerWorld) livingAttacker.getWorld()).spawnParticles(ParticleTypes.HEART, livingAttacker.getX() + d0, livingAttacker.getBodyY(1), livingAttacker.getZ() + d0, (int) heal, d1, 0.0, d2, 0.0);
                            }


                        }


                    }


                }


                //Resurrection Enchantment
                if (amount >= entity.getHealth()) {
                    if (EnchantmentHelper.getLevel(EnchantmentRegistration.RESURRECTION, defenderHeadStack) > 0) {


                        entity.heal(entity.getMaxHealth());


                        entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_WOLF_HOWL, SoundCategory.AMBIENT, 5, 1);
                        entity.getWorld().playSound(null, entity.getBlockPos(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.AMBIENT, 5, 1);


                        if (entity instanceof TameableEntity tameableEntity) {

                            if (tameableEntity.getOwner() != null && tameableEntity.getOwner().getWorld() == entity.getWorld() && !tameableEntity.getOwner().getWorld().isClient) {


                                LivingEntity owner = tameableEntity.getOwner();
                                tameableEntity.teleport(owner.getX(), owner.getY(), owner.getZ(), true);


                            }


                        }
                        EnchantedWolves.reduceItemEnchantmentLevel(defenderHeadStack, EnchantmentRegistration.RESURRECTION, 1);


                        amount = 0;
                    }


                }
                return amount > 0;


            }
        });


        //Handles equipping collar
        InteractEvent.INTERACT.register((player, hand, target) -> {


            if (canWearCollar(target) && player.isSneaking()) {

                if (target instanceof TameableEntity tameableEntity) {

                    if (tameableEntity.getOwner() != null && tameableEntity.getOwner() == player) {

                        ItemStack headStack = tameableEntity.getEquippedStack(EquipmentSlot.HEAD);
                        ItemStack collarStack = player.getStackInHand(hand);

                        if (headStack.isEmpty()) {


                            if (collarStack.getItem() instanceof WolfCollar) {


                                ItemStack copied = collarStack.copyAndEmpty();


                                if (copied.hasCustomName()) {
tameableEntity.setCustomName(copied.getName());
                                }


                                tameableEntity.equipStack(EquipmentSlot.HEAD, copied);


                                return ActionResult.FAIL;
                            }


                        } else {

                            if (collarStack.isEmpty()) {

                                if (!headStack.isEmpty()) {

                                    ItemStack copied = headStack.copyAndEmpty();

                                    player.setStackInHand(hand, copied);
                                    return ActionResult.FAIL;

                                }


                            }

                        }


                    }


                }


            } else {


            }


            return ActionResult.PASS;
        });

        //Handles Resurrection Enchantment



        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add(ItemRegistration.LEATHER_COLLAR);
            content.add(ItemRegistration.IRON_COLLAR);
            content.add(ItemRegistration.CHAINMAIL_COLLAR);
            content.add(ItemRegistration.GOLD_COLLAR);
            content.add(ItemRegistration.DIAMOND_COLLAR);
            content.add(ItemRegistration.NETHERITE_COLLAR);

        });
    }

    public static void reduceItemEnchantmentLevel(ItemStack stack, Enchantment enchantment, int amount) {


        if (EnchantmentHelper.getLevel(enchantment, stack) >= 1) {
            Identifier resourcelocation = Registries.ENCHANTMENT.getId(enchantment);
            NbtList listnbt = stack.getEnchantments();
            int newlevel = -1;
            boolean remove = false;
            for (int i = 0; i < listnbt.size(); ++i) {
                NbtCompound compoundnbt = listnbt.getCompound(i);
                Identifier resourcelocation1 = Identifier.tryParse(compoundnbt.getString("id"));
                if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                    newlevel = compoundnbt.getInt("lvl") - amount;
                    if (newlevel >= 1) {
                        compoundnbt.putInt("lvl", newlevel);
                    } else {

                        remove = true;
                    }
                }
            }

            if (remove) {
                removeEnchantment(enchantment, stack);
            }


        }


    }

    public static void removeEnchantment(Enchantment enchantment, ItemStack stack) {

        if (EnchantmentHelper.getLevel(enchantment, stack) >= 1) {
            Identifier resourcelocation = Registries.ENCHANTMENT.getId(enchantment);
            NbtCompound nbt = stack.getNbt().copy();
            NbtList listnbt = nbt.getList("Enchantments", 10);
            NbtList newenchantments = new NbtList();
            for (int i = 0; i < listnbt.size(); ++i) {
                NbtCompound compoundnbt = listnbt.getCompound(i);
                Identifier resourcelocation1 = Identifier.tryParse(compoundnbt.getString("id"));
                if (resourcelocation1 != null && !resourcelocation1.equals(resourcelocation)) {

                    newenchantments.add(compoundnbt);

                }
            }
            nbt.put("Enchantments", newenchantments);
            stack.setNbt(nbt);

        }


    }


}