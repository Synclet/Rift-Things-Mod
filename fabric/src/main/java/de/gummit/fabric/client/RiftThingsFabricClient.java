package de.gummit.fabric.client;

import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public final class RiftThingsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.RIFT_REMNANT, RiftRemnantRenderer::new);
    }
}
