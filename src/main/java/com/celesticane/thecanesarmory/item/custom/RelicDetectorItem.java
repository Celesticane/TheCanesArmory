package com.celesticane.thecanesarmory.item.custom;

import net.minecraft.world.item.*;

public class RelicDetectorItem extends Item {


    public int scanRange;
    public int readoutMode;

    public RelicDetectorItem(Properties pProperties, int scanRange, int readoutMode) {
        super(pProperties);
        this.scanRange = scanRange;
        this.readoutMode = readoutMode;
    }
}