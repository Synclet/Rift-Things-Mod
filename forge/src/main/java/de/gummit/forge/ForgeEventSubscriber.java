package de.gummit.forge;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RiftThingsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeEventSubscriber {


    @SubscribeEvent(receiveCanceled = true, priority = EventPriority.HIGHEST)
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.RIFT_REMNANT.get(), RiftRemnantRenderer::new);
    }

}
