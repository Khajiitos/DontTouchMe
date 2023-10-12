package me.khajiitos.donttouchme.forge;

import me.khajiitos.donttouchme.common.DontTouchMe;
import me.khajiitos.donttouchme.common.config.ClothConfigCheck;
import me.khajiitos.donttouchme.common.config.ClothConfigScreenMaker;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(DontTouchMe.MOD_ID)
public class DontTouchMeForge {
    public DontTouchMeForge() {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            DontTouchMe.init();

            if (ClothConfigCheck.isInstalled()) {
                ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory(ClothConfigScreenMaker::create));
            }
        });
    }
}