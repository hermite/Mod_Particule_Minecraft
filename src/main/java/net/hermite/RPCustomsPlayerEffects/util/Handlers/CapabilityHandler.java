package net.hermite.RPCustomsPlayerEffects.util.Handlers;

import net.hermite.RPCustomsPlayerEffects.util.Reference;
import net.hermite.RPCustomsPlayerEffects.util.Providers.FootStepProvider;
import net.hermite.RPCustomsPlayerEffects.util.Providers.StatusProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//Ajoute la capacity sur les players
@Mod.EventBusSubscriber
public class CapabilityHandler
{
    public static final ResourceLocation status = new ResourceLocation(Reference.MOD_ID, "status");
    public static final ResourceLocation footStep = new ResourceLocation(Reference.MOD_ID, "footStep");

    @SubscribeEvent
    public void attachCapabilityEntity(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        event.addCapability(status, new StatusProvider());
    }
    
    @SubscribeEvent
    public void attachCapabilityWorld(AttachCapabilitiesEvent<World> event)
    {
        event.addCapability(footStep, new FootStepProvider());
    }
}
