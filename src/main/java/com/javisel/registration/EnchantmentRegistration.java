package com.javisel.registration;


  import com.javisel.enchantment.*;
  import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.javisel.EnchantedWolves.MODID;

public class EnchantmentRegistration {

    public static final Enchantment CLEAVING_JAW = new CleavingJawEnchantment();

    public static final Enchantment SPACIAL_BIND = new SpacialBindEnchantment();
    public static final Enchantment FROSTBITE = new FrostbiteEnchantment();

    public static final Enchantment HEARTY = new HeartyEnchantment();
    public static final Enchantment INFERNAL = new InfernalEnchantment();
    public static final Enchantment LEECH_FANGS = new LeechFangsEnchantment();
    public static final Enchantment LUCKY_PAW = new LuckyPawEnchantment();
    public static final Enchantment PACK_LEADER = new PackLeaderEnchantment();
    public static final Enchantment PEST_PATROL = new PestPatrolEnchantment();
    public static final Enchantment RADIANT_FANGS = new RadiantFangsEnchantment();
    public static final Enchantment RESURRECTION = new ResurrectionEnchantment();
    public static final Enchantment SMITING_BITE = new SmitingBiteEnchantment();
    public static final Enchantment SWIFTNESS = new SwiftnessEnchantment();
    public static final Enchantment TAG_TEAM = new TagTeamEnchantment();
    public static final Enchantment THUNDER_MAW = new ThunderMawEnchantment();
    public static final Enchantment TRAUMA = new TraumaEnchantment();
    public static final Enchantment WEAKENING_MAW = new WeakeningMawEnchantment();
    public static final Enchantment WHOLESOME_PRESCENCE = new WholesomePrescenceEnchantment();


    public static void registerEnchantments() {


        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "cleaving_jaw"), CLEAVING_JAW);
       // Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "spacial_bind"), SPACIAL_BIND);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "frostbite"), FROSTBITE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "hearty"), HEARTY);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "infernal"), INFERNAL);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "leech_fangs"), LEECH_FANGS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "lucky_paw"), LUCKY_PAW);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "pack_leader"), PACK_LEADER);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "pest_patrol"), PEST_PATROL);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "radiant_fangs"), RADIANT_FANGS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "resurrection"), RESURRECTION);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "smiting_bite"), SMITING_BITE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "swiftness"), SWIFTNESS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "tag_team"), TAG_TEAM);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "thunder_maw"), THUNDER_MAW);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "trauma"), TRAUMA);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "weakening_maw"), WEAKENING_MAW);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "wholesome_presence"), WHOLESOME_PRESCENCE);


    }

}
