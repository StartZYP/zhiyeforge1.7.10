package me.professionalskills.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CustomEntity extends Entity {

    EntityPlayer player=null;

    public CustomEntity(World p_i1582_1_) {
        super(p_i1582_1_);
    }

    @Override
    protected void entityInit() {

    }

    public void setOwner(EntityPlayer player){
        this.player=player;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {

    }
}
