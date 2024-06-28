package de.gummit.forge;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RiftThingsMod.MOD_ID)
public final class RiftThingsForge {

    public RiftThingsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(RiftThingsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        // Run our common setup.
        RiftThingsMod.init();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        EntityRenderDispatcher renderDispatcher = event.getMinecraftSupplier().get().getEntityRenderDispatcher();

        renderDispatcher.register(ModEntities.RIFT_REMNANT.get(), new RiftRemnantRenderer(renderDispatcher));
    }

}
