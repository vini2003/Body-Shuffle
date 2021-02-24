package vini2003.xyz.bodyshuffle.client.screen.widget;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import vini2003.xyz.blade.client.utilities.Drawings;
import vini2003.xyz.blade.client.utilities.Layers;
import vini2003.xyz.blade.common.utilities.Networks;
import vini2003.xyz.blade.common.widget.base.AbstractWidget;
import vini2003.xyz.bodyshuffle.client.utilities.BodyShuffleClientUtilities;
import vini2003.xyz.bodyshuffle.common.miscellaneous.BodyPart;
import vini2003.xyz.bodyshuffle.registry.common.BodyShuffleComponents;

public class BodyPartWidget extends AbstractWidget {
	private Identifier enabledTexture;
	
	private Identifier disabledTexture;
	
	private BodyPart part;
	
	private boolean enabled;
	
	public BodyPartWidget() {
		getSynchronize().add(Networks.getMOUSE_RELEASE());
	}
	
	@Override
	public void onMouseReleased(float x, float y, int button) {
		super.onMouseReleased(x, y, button);
		
		if (!getHandler().getClient() || getFocused()) {
			if (getHandler().getClient()) {
				BodyShuffleClientUtilities.playButtonSound();
			}
			
			setEnabled(!isEnabled());
			
			BodyShuffleComponents.BODY_PARTS.get(getHandler().getPlayer()).toggle(part, enabled);
			BodyShuffleComponents.BODY_PARTS.sync(getHandler().getPlayer());
		}
	}
	
	
	@Override
	public void drawWidget(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider provider) {
		if (getHidden()) return;
		
		if (enabled) {
			Drawings.drawTexturedQuad(matrices, provider, Layers.get(enabledTexture), getX(), getY(), getWidth(), getHeight(), enabledTexture);
		} else {
			Drawings.drawTexturedQuad(matrices, provider, Layers.get(disabledTexture), getX(), getY(), getWidth(), getHeight(), disabledTexture);
		}
	}
	
	public Identifier getEnabledTexture() {
		return enabledTexture;
	}
	
	public void setEnabledTexture(Identifier enabledTexture) {
		this.enabledTexture = enabledTexture;
	}
	
	public Identifier getDisabledTexture() {
		return disabledTexture;
	}
	
	public void setDisabledTexture(Identifier disabledTexture) {
		this.disabledTexture = disabledTexture;
	}
	
	public BodyPart getPart() {
		return part;
	}
	
	public void setPart(BodyPart part) {
		this.part = part;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
