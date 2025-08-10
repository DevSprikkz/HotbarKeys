package com.sprikkz.hotbarkeys;

/*
 * Project: HotbarKeys
 * File: HotbarKeys.java
 * Created on: Aug 10, 2025 at 13:14
 * Author: DevSprikkz (pedrojoao31196@gmail.com)
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@Mod(modid = HotbarKeys.MODID, version = HotbarKeys.VERSION, clientSideOnly = true)
public class HotbarKeys
{
    public static final String MODID = "hotbarkeys";
    public static final String VERSION = "1.0";
    
    private final Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderHotbar(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.HOTBAR) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int centerX = sr.getScaledWidth() / 2;
        int yPos = sr.getScaledHeight() - 20;

        KeyBinding[] hotbarKeys = {
                mc.gameSettings.keyBindsHotbar[0],
                mc.gameSettings.keyBindsHotbar[1],
                mc.gameSettings.keyBindsHotbar[2],
                mc.gameSettings.keyBindsHotbar[3],
                mc.gameSettings.keyBindsHotbar[4],
                mc.gameSettings.keyBindsHotbar[5],
                mc.gameSettings.keyBindsHotbar[6],
                mc.gameSettings.keyBindsHotbar[7],
                mc.gameSettings.keyBindsHotbar[8]
        };

        for (int i = 0; i < 9; i++) {
            String keyName = Keyboard.getKeyName(hotbarKeys[i].getKeyCode());
            int slotX = centerX - 90 + (i * 20) + 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.8f, 0.8f, 1.0f);
            int scaledX = (int)(slotX / 0.8f);
            int scaledY = (int)(yPos / 0.8f);
            mc.fontRendererObj.drawStringWithShadow(keyName, scaledX, scaledY, 0xFFFFFF);
            GL11.glPopMatrix();
        }
    }
}

