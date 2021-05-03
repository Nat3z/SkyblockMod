package com.nat3z.skyqol.events;

import javax.vecmath.Vector3f;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

// Code Provided By Skyils
@Cancelable
public class DamageBlockEvent extends Event {

    public BlockPos pos;
    public Vector3f vectorPos;

    public DamageBlockEvent(BlockPos pos) {
        this.pos = pos;
        this.vectorPos = new Vector3f(pos.getX(), pos.getY(), pos.getZ());
    }
}