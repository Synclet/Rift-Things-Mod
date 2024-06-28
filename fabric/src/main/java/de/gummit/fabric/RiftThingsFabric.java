package de.gummit.fabric;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;

public final class RiftThingsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        RiftThingsMod.init();

        EntityRendererRegistry.INSTANCE.register(ModEntities.RIFT_REMNANT.get(), new EntityRendererRegistry.Factory() {
            @Override
            public EntityRenderer<? extends Entity> create(EntityRenderDispatcher manager, EntityRendererRegistry.Context context) {
                return new RiftRemnantRenderer(manager);
            }
        });
    }
}
