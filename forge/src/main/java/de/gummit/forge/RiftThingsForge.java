package de.gummit.forge;

import de.gummit.RiftThingsMod;
import de.gummit.entity.ModEntities;
import de.gummit.entity.renderer.RiftRemnantRenderer;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RiftThingsMod.MOD_ID)
public final class RiftThingsForge {

    public RiftThingsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(RiftThingsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> this::clientBusSetup);

        // Run our common setup.
        RiftThingsMod.init();
    }

    @OnlyIn(Dist.CLIENT)
    private void clientBusSetup() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(FMLClientSetupEvent event) {
        EntityRenderDispatcher renderDispatcher = event.getMinecraftSupplier().get().getEntityRenderDispatcher();

        renderDispatcher.register(ModEntities.RIFT_REMNANT.get(), new RiftRemnantRenderer(renderDispatcher));
    }

}
