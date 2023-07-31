package com.javisel.enchantedwolves.common;

/*
public class ConfigHandler {



        /*
    public static class CommonConfig{




        public  final Config.ConfigValue<Double> vulnerableMultiplier;
        public  final ForgeConfigSpec.ConfigValue<Double> packPowerDamageBoost;
        public  final ForgeConfigSpec.ConfigValue<Double> packPowerSpeedBoost;


        public  final ForgeConfigSpec.ConfigValue<Double> cleavingJawPercentage;
        public  final ForgeConfigSpec.ConfigValue<Integer> spacialBindDuration;
        public  final ForgeConfigSpec.ConfigValue<Integer> frostbiteDuration ;

        public  final ForgeConfigSpec.ConfigValue<Integer> heartyRegeneration;
        public  final ForgeConfigSpec.ConfigValue<Double> infernalDamageReduction ;
        public  final ForgeConfigSpec.ConfigValue<Integer> infernalBurnDuration ;
        public  final ForgeConfigSpec.ConfigValue<Double> leechFangsHealPercentage ;
        public  final ForgeConfigSpec.ConfigValue<Integer> luckyPawDuration ;
        public  final ForgeConfigSpec.ConfigValue<Integer> packPowerStrength;
        public  final ForgeConfigSpec.ConfigValue<Integer> packPowerDuration;
        public  final ForgeConfigSpec.ConfigValue<Integer> packPowerCooldownDuration ;
        public  final ForgeConfigSpec.ConfigValue<Double> pestPatrolDamage;
        public  final ForgeConfigSpec.ConfigValue<Integer> radiantFangsDuration ;
        public     ForgeConfigSpec.ConfigValue<Boolean>  doesResurrectionConsumeEnchant;
        public  final ForgeConfigSpec.ConfigValue<Double> smitingBiteDamage ;
        public  final ForgeConfigSpec.ConfigValue<Double> swiftnessSpeedBoost;
        public  final ForgeConfigSpec.ConfigValue<Integer> tagTeamPower;
        public  final ForgeConfigSpec.ConfigValue<Integer> tagTeamDuration;
        public  final ForgeConfigSpec.ConfigValue<Double> thunderMawBaseChance;
        public  final ForgeConfigSpec.ConfigValue<Double> thunderMawChanceMultiplier;
        public  final ForgeConfigSpec.ConfigValue<Double > traumaDamage;
        public  final ForgeConfigSpec.ConfigValue<Integer> weakeningMawDuration;
        public  final ForgeConfigSpec.ConfigValue<Integer> weakeningMawPower;



        public static final String[] validWolves = new String[] {"minecraft:wolf", "quark:foxhound", "doggytalents:dog"};


        public CommonConfig(ForgeConfigSpec.Builder builder) {


            builder.push("Enchanted Wolves Config");







            this.vulnerableMultiplier=builder.comment("The percent increase in damage that Vulnerable applies").translation("config.enchantedwolves.vulnerablemod.desc").defineInRange("vulnerableMultiplier",0.10f,0f,10000);
            this.packPowerDamageBoost=builder.comment("How much Attack Damage Pack Power grants").translation("config.enchantedwolves.packpower.desc").defineInRange("packPowerDamage",2f,0f,10000);
            this.packPowerSpeedBoost=builder.comment("How much  Pack Power grants").translation("config.enchantedwolves.packpower.desc").defineInRange("packPowerSpeed",.10f,0f,10000);





            builder.comment("Durations are in ticks. 20 Ticks = 1 second. Please Note: Configuring enchantment levels is not possible due to code restrictions.");
 
            this.cleavingJawPercentage=builder.comment("The percentage of the triggering attack's damage that cleaving jaw applies to other entities").translation("config.enchantedwolves.enchantment.cleavingjaw.desc").defineInRange("cleaveJawDamage",0.25f,0f,10000);
            this.spacialBindDuration =builder.comment("How long Spacial Bind should last.").translation("config.enchantedwolves.enchantment.spacialbind.desc").defineInRange("spacialBindDuration",100,0,Integer.MAX_VALUE,Integer.class);
            this.frostbiteDuration=builder.comment("how long frostbites's slow should last").translation("config.enchantedwolves.enchantment.frostbite.desc").defineInRange("frostbiteDuration",60,0,Integer.MAX_VALUE, Integer.class);



            this.heartyRegeneration=builder.comment("The Regeneration Level Hearty grants.").translation("config.enchantedwolves.enchantment.hearty.desc").defineInRange("heartyRegenerationLevel",1,0,255, Integer.class);
            this.infernalDamageReduction=builder.comment("The percentage reduction infernal applies to fire damage.").translation("config.enchantedwolves.enchantment.infernal.desc").defineInRange("infernalDamageReduction",0.5f,0f,10000);
            this.infernalBurnDuration=builder.comment("how long Infernal should set enemeis on fire for").translation("config.enchantedwolves.infernalduration.desc").defineInRange("infernalBurnDuration",40,0,Integer.MAX_VALUE, Integer.class);
            this.leechFangsHealPercentage=builder.comment("The percentage of the triggering attack's damage that heals the wolf.").translation("config.enchantedwolves.enchantment.hearty.desc").defineInRange("leechFangHealing",0.25f,0f,10000);
            this.luckyPawDuration=builder.comment("how long Lucky should last.").translation("config.enchantedwolves.enchantment.lucky.desc").defineInRange("luckyDuration",100,0,Integer.MAX_VALUE, Integer.class);
            this.packPowerStrength=builder.comment("What Pack Power level Pack Leader should apply").translation("config.enchantedwolves.enchantment.packpowerstrength.desc").defineInRange("packPowerStrength",100,0,Integer.MAX_VALUE, Integer.class);
            this.packPowerDuration=builder.comment("How long Pack Power should last").translation("config.enchantedwolves.enchantment.packpowerduration.desc").defineInRange("packPowerDuration",100,0,Integer.MAX_VALUE, Integer.class);
            this.packPowerCooldownDuration=builder.comment("Cooldown Duration of Pack Power").translation("config.enchantedwolves.enchantment.packpowercooldown.desc").defineInRange("packPowerCooldown",2400,0,Integer.MAX_VALUE, Integer.class);
            this.pestPatrolDamage=builder.comment("How much damage Pest Patrol should do.").translation("config.enchantedwolves.enchantment.pestpatrol.desc").defineInRange("pestPatrolDmge",2.5f,0f,10000);
            this.radiantFangsDuration=builder.comment("How long the Bountiful effect should last").translation("config.enchantedwolves.enchantment.bountiful.desc").defineInRange("luckyDuration",100,0,Integer.MAX_VALUE, Integer.class);
            this.doesResurrectionConsumeEnchant=builder.comment("should resurrection consume an enchantment level on activation").translation("config.enchantedwolves.enchantment.resurrection.desc").define("ressurrectConsumesEnch",true);
            this.smitingBiteDamage=builder.comment("How much damage Smiting Bite should do").translation("config.enchantedwolves.enchantment.smitingbite.desc").defineInRange("smitingdamage",2.5f,0f,10000);
            this.swiftnessSpeedBoost=builder.comment("Swiftness' speed multiplier").translation("config.enchantedwolves.enchantment.swiftness.desc").defineInRange("swiftnessSpeed",0.1f,0f,10000);
            this.tagTeamPower=builder.comment("The Strength level granted by Tag Team").translation("config.enchantedwolves.enchantment.tagteampower.desc").defineInRange("tagTeamPower",1,0,255,Integer.class);
            this.tagTeamDuration =builder.comment("The duration of the strength buff granted by Tag Team").translation("config.enchantedwolves.enchantment.tagteam.tagteamduration.desc").defineInRange("tagTeamDuration",100,0,Integer.MAX_VALUE,Integer.class);
            this.thunderMawBaseChance=builder.comment("The base percent chance for Thundermaw to apply").translation("config.enchantedwolves.thundermawbasechance.desc").defineInRange("thunderBaseChance",0.30f,0f,10000);
            this.thunderMawChanceMultiplier=builder.comment("The per-enchant-level multiiplier to thundermaw's chance").translation("config.enchantedwovles.thundermawchacemulti.desc").defineInRange("thunderMawLvlMod",0.25f,0f,10000);
            this.traumaDamage=builder.comment("The damage boost from Trauma enchant").translation("config.enchantedwolves.enchantment.trauma.desc").defineInRange("traumaDamage",1f,0f,10000);
            this.weakeningMawDuration =builder.comment("How long weakening' maw's weakness effect should .ast").translation("config.enchantedwolves.enchantment.weakness.desc").defineInRange("weakMawDuration",60,0,Integer.MAX_VALUE,Integer.class);
            this.weakeningMawPower =builder.comment("The strength of Weakening Maw's weakness effect").translation("config.enchantedwolves.enchantment.weakeningmaw.desc").defineInRange("weakMawPower",3,0,Integer.MAX_VALUE,Integer.class);



            builder.pop();








        }



        public boolean isEntityValidWolfByLocation(ResourceLocation input) {



            for ( String entry : validWolves) {





                if  (input.toString().equalsIgnoreCase(entry)){

                    return true;
                }



            }
            return  false;

        }



        public  boolean isEntityValidWolf(Entity entity) {



            return isEntityValidWolfByLocation(entity.getType().getRegistryName());

        }


    }




    public static final CommonConfig CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static
    {
        Pair<CommonConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        CONFIG = commonSpecPair.getLeft();
        CONFIG_SPEC = commonSpecPair.getRight();
    }








}



*/

