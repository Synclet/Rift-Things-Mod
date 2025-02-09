package de.gummit.fabric;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public final class RiftThingsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        RiftThingsMod.init();

        clientInit();
    }

    @Environment(EnvType.CLIENT)
    private void clientInit() {
        EntityRendererRegistry.INSTANCE.register(ModEntities.RIFT_REMNANT.get(), (manager, context) -> new RiftRemnantRenderer(manager));
    }

}
