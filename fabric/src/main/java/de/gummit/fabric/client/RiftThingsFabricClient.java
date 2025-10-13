package de.gummit.fabric.client;

import de.gummit.entity.ModEntities;
import de.gummit.entity.ModEntityLayers;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public final class RiftThingsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.RIFT_REMNANT, RiftRemnantRenderer::new);
        ModEntityLayers.initClient((modelLayerLocation, layerDefinitionSupplier) -> EntityModelLayerRegistry.registerModelLayer(modelLayerLocation, layerDefinitionSupplier::get));
    }
}
