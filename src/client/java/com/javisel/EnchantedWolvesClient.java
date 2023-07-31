package com.javisel;

import com.javisel.mixin.client.EnchantedWolvesClientMixin;
import com.javisel.registration.ItemRegistration;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.EndermanEyesFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.registry.Registries;

public class EnchantedWolvesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {


		ColorProviderRegistry.ITEM.register((stack, color) -> color > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), ItemRegistration.LEATHER_COLLAR);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		LivingEntityFeatureRendererRegistrationCallback.EVENT.register(new LivingEntityFeatureRendererRegistrationCallback() {
			@Override
			public void registerRenderers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?, ?> entityRenderer, RegistrationHelper registrationHelper, EntityRendererFactory.Context context) {


				if (entityType == EntityType.WOLF) {


 					registrationHelper.register(new EnchantedWolvesCollarLayer<>(entityRenderer));

				}
			}
		});






	}
}