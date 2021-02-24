package vini2003.xyz.bodyshuffle.client.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import vini2003.xyz.blade.client.handler.BaseHandledScreen;
import vini2003.xyz.blade.client.utilities.Drawings;
import vini2003.xyz.blade.client.utilities.Layers;
import vini2003.xyz.blade.common.handler.BaseScreenHandler;
import vini2003.xyz.blade.common.miscellaneous.Color;
import vini2003.xyz.blade.common.widget.base.AbstractWidget;
import vini2003.xyz.bodyshuffle.client.screen.widget.BodyPartWidget;
import vini2003.xyz.bodyshuffle.common.screenhandler.BodyPartSelectorScreenHandler;

public class BodyPartSelectorScreen extends BaseHandledScreen<BodyPartSelectorScreenHandler> {
	public BodyPartSelectorScreen(@NotNull BaseScreenHandler handler, @NotNull PlayerInventory inventory, @NotNull Text title) {
		super(handler, inventory, title);
		
		this.playerInventoryTitleX = Integer.MIN_VALUE;
	}
	
	@Override
	public void render(@NotNull MatrixStack matrices, int mouseX, int mouseY, float delta) {
		super.render(matrices, mouseX, mouseY, delta);
		
		VertexConsumerProvider provider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
		
		for (AbstractWidget widget : getScreenHandler().getAllWidgets()) {
			if (widget instanceof BodyPartWidget) {
				if (widget.getFocused()) {
					Drawings.drawQuad(matrices, provider, Layers.flat(), widget.getX() - 1, widget.getY() - 1, 1, widget.getHeight() + 2, Color.of(0xFFFFFFFF));
					Drawings.drawQuad(matrices, provider, Layers.flat(), widget.getX() + widget.getWidth(), widget.getY() - 1, 1, widget.getHeight() + 2, Color.of(0xFFFFFFFF));
					Drawings.drawQuad(matrices, provider, Layers.flat(), widget.getX(), widget.getY() - 1, widget.getWidth(), 1, Color.of(0xFFFFFFFF));
					Drawings.drawQuad(matrices, provider, Layers.flat(), widget.getX(), widget.getY() + widget.getHeight(), widget.getWidth(), 1, Color.of(0xFFFFFFFF));
				}
			}
		}
		
		((VertexConsumerProvider.Immediate) provider).draw();
	}
}
