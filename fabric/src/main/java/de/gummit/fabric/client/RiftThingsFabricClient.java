package de.gummit.fabric.client;

import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public final class RiftThingsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityRendererRegistry.INSTANCE.register(ModEntities.RIFT_REMNANT.get(), (manager, context) -> new RiftRemnantRenderer(manager));
    }
}
