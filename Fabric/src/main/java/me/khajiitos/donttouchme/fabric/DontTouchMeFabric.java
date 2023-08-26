package me.khajiitos.donttouchme.fabric;

import me.khajiitos.donttouchme.common.DontTouchMe;
import net.fabricmc.api.ClientModInitializer;

public class DontTouchMeFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DontTouchMe.init();
    }
}
