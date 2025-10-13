package de.gummit.fabric;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public final class RiftThingsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        RiftThingsMod.init();

        //clientInit();
    }

    //@Environment(EnvType.CLIENT)
    //private void clientInit() {
    //    EntityRendererRegistry.register(ModEntities.RIFT_REMNANT, RiftRemnantRenderer::new);
    //}

}