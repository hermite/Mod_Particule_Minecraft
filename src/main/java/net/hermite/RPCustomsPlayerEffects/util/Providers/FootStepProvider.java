package net.hermite.RPCustomsPlayerEffects.util.Providers;

import net.hermite.RPCustomsPlayerEffects.util.Class.IFootStep;
import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FootStepProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IFootStep.class)
    public static final Capability<IFootStep> FootStep = null;

    private IFootStep instance = FootStep.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == FootStep;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == FootStep ? FootStep.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return FootStep.getStorage().writeNBT(FootStep, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
    	FootStep.getStorage().readNBT(FootStep, this.instance, null, nbt);
    }
}
