package me.khajiitos.donttouchme.neoforge;

import me.khajiitos.donttouchme.common.DontTouchMe;
import me.khajiitos.donttouchme.common.config.ClothConfigCheck;
import me.khajiitos.donttouchme.common.config.ClothConfigScreenMaker;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(DontTouchMe.MOD_ID)
public class DontTouchMeNeoForge {
    public DontTouchMeNeoForge(ModContainer modContainer) {
        if (FMLLoader.getDist() == Dist.CLIENT) {
            DontTouchMe.init();

            if (ClothConfigCheck.isInstalled()) {
                modContainer.registerExtensionPoint(IConfigScreenFactory.class, ClothConfigScreenMaker::create);
            }
        }
    }
}