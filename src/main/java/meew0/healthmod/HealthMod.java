package meew0.healthmod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import meew0.healthmod.client.GuiSymptomOverlay;
import meew0.healthmod.items.ItemThermometer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = HealthMod.MODID, version = HealthMod.VERSION)
public class HealthMod
{
    public static final String MODID = "healthmod";
    public static final String VERSION = "1.0";

    public static Logger modLog;
    public static boolean debugMode = true;

    public static Item thermometer;

    public static final HealthModEventHandler eventHandler = new HealthModEventHandler();

    public static void debug(String msg) {
        if(debugMode) modLog.info("[DEBUG] " + msg);
    }

    public static void warn(String msg) {
        modLog.warn(msg);
    }

    public static void error(String msg) {
        modLog.error(msg);
    }

    public static void info(String msg) {
        modLog.info(msg);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        modLog = event.getModLog();

        MinecraftForge.EVENT_BUS.register(eventHandler);
        FMLCommonHandler.instance().bus().register(eventHandler);

        MinecraftForge.EVENT_BUS.register(new GuiSymptomOverlay());

        thermometer = new ItemThermometer().setUnlocalizedName("thermometer").setCreativeTab(CreativeTabs.tabAllSearch);
        GameRegistry.registerItem(thermometer, "thermometer");
    }
}
