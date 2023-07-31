package com.javisel;

 import com.javisel.item.LeatherWolfCollar;
 import com.javisel.item.WolfCollar;
 import com.javisel.registration.ItemRegistration;
 import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
 import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
 import net.minecraft.client.render.entity.model.BipedEntityModel;
 import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
 import net.minecraft.item.ArmorItem;
 import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
 import org.jetbrains.annotations.Nullable;

 import java.util.HashMap;
import java.util.Map;

import static com.javisel.EnchantedWolves.MODID;

public class EnchantedWolvesCollarLayer<T extends LivingEntity,M extends EntityModel<T>> extends FeatureRenderer<T,M> implements ArmorRenderer {


    public static final Map<String,Identifier> COLLAR_MAP = new HashMap<String, Identifier>();

    public static final Map<com.javisel.item.WolfCollar, Identifier> STATIC_MAP = new HashMap<WolfCollar,Identifier>();


    public static final Identifier LEATHER_BUTTON = new Identifier(MODID,"textures/entity/minecraft/wolf/leather_collar_button.png");

    static {
        STATIC_MAP.put((WolfCollar) ItemRegistration.IRON_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/iron_collar.png"));

        STATIC_MAP.put((WolfCollar) ItemRegistration.DIAMOND_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/diamond_collar.png"));
        STATIC_MAP.put((WolfCollar) ItemRegistration.GOLD_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/golden_collar.png"));
        STATIC_MAP.put((WolfCollar) ItemRegistration.LEATHER_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/leather_collar.png"));

        STATIC_MAP.put((WolfCollar) ItemRegistration.NETHERITE_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/netherite_collar.png"));
        STATIC_MAP.put((WolfCollar) ItemRegistration.CHAINMAIL_COLLAR,new Identifier(MODID,"textures/entity/minecraft/wolf/chainmail_collar.png"));

    }






    public EnchantedWolvesCollarLayer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {


         if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() instanceof   WolfCollar collar) {

            ItemStack collarStack = entity.getEquippedStack(EquipmentSlot.HEAD);

            if (collar instanceof LeatherWolfCollar leatherWolfCollar) {

                int color = leatherWolfCollar.getColor(collarStack);


                float r = (float) (color >> 16 & 255) / 255.0F;
                float g = (float) (color >> 8 & 255) / 255.0F;
                float b = (float) (color & 255) / 255.0F;


                renderArmorParts(entity,matrices,vertexConsumers,light,collar,getContextModel(),false,r,g,b,null);
                renderArmorParts(entity,matrices,vertexConsumers,light,collar,getContextModel(),true,r,g,b,"overlay");


            } else {

                renderArmorParts(entity,matrices,vertexConsumers,light,collar,getContextModel(),false,1f,1f,1f,"null");

            }


            if (collarStack.hasGlint()) {

                renderGlint(entity,matrices,vertexConsumers,light,getContextModel());
            }



        }


    }



    public static Identifier getCollarLocation(Entity entity, WolfCollar collar) {



        return  STATIC_MAP.get(collar);


    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {




    }

    private void renderArmorParts(Entity entity,MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, WolfCollar item, M model, boolean secondTextureLayer, float red, float green, float blue, @Nullable String overlay) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(secondTextureLayer ? LEATHER_BUTTON : getCollarLocation(entity,item)));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0F);
    }
    private void renderGlint(Entity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, M model) {
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint()), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
    }





}
