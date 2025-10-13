package de.gummit.forge;

import de.gummit.RiftThingsMod;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RiftThingsMod.MOD_ID)
public final class RiftThingsForge {

    public RiftThingsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(RiftThingsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        RiftThingsMod.init();
    }

}