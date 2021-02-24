package vini2003.xyz.bodyshuffle.client.screen.widget;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import vini2003.xyz.blade.common.utilities.Positions;
import vini2003.xyz.blade.common.widget.base.AbstractWidget;

public class PlayerWidget extends AbstractWidget {
	private int modelSize = 1;
	
	@Override
	public void drawWidget(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider provider) {
		if (getHidden()) return;
		
		InventoryScreen.drawEntity((int) getX(), (int) getY(), getModelSize(), getX() - Positions.getMouseX(), getY() - Positions.getMouseY() - modelSize * 1.66F, getHandler().getPlayer());
	}
	
	public int getModelSize() {
		return modelSize;
	}
	
	public void setModelSize(int modelSize) {
		this.modelSize = modelSize;
	}
}
