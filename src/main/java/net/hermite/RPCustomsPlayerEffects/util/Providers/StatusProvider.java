package net.hermite.RPCustomsPlayerEffects.util.Providers;

import net.hermite.RPCustomsPlayerEffects.util.Class.IStatus;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class StatusProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IStatus.class)
    public static final Capability<IStatus> Status = null;

    private IStatus instance = Status.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == Status;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == Status ? Status.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return Status.getStorage().writeNBT(Status, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
    	Status.getStorage().readNBT(Status, this.instance, null, nbt);
    }
}
