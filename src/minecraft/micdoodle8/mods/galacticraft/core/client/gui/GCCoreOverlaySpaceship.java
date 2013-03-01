package micdoodle8.mods.galacticraft.core.client.gui;

import static micdoodle8.mods.galacticraft.core.client.gui.GCCoreOverlay.drawTexturedModalRect;
import static micdoodle8.mods.galacticraft.core.client.gui.GCCoreOverlay.getPlayerPositionY;
import static micdoodle8.mods.galacticraft.core.client.gui.GCCoreOverlay.loadDownloadableImageTexture;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GCCoreOverlaySpaceship extends GCCoreOverlay
{
	private static Minecraft minecraft = FMLClientHandler.instance().getClient();
	
	/**
	 * Render the GUI when player is in inventory
	 */
	public static void renderSpaceshipOverlay()
	{
		final ScaledResolution scaledresolution = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
        final int width = scaledresolution.getScaledWidth();
        final int height = scaledresolution.getScaledHeight();
        minecraft.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, minecraft.renderEngine.getTexture("/micdoodle8/mods/galacticraft/core/client/gui/spaceshipgui.png"));
		final Tessellator tessellator = Tessellator.instance;
        drawTexturedModalRect(10, 10, 0, 0, 10, 121);

        final int col = GCCoreUtil.convertTo32BitColor(255, 198, 198, 198);
        Gui.drawRect(0, 					0, 					width, 		20, 			col);
        Gui.drawRect(0,	 					height - 24, 		width, 		height,    		col);
        Gui.drawRect(0, 					0, 					10, 		height,    		col);
        Gui.drawRect(width - 10, 			0, 					width, 		height, 		col);

		loadDownloadableImageTexture("http://skins.minecraft.net/MinecraftSkins/" + StringUtils.stripControlCodes(minecraft.thePlayer.username) + ".png", FMLClientHandler.instance().getClient().thePlayer.getTexture());

        drawTexturedModalRect(10, 10 + (120 - (int) Math.floor(getPlayerPositionY(minecraft.thePlayer) / 10)), 16, 16, 8, 8);
        
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
