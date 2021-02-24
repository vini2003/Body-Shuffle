package vini2003.xyz.bodyshuffle.registry.common;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import vini2003.xyz.bodyshuffle.BodyShuffle;
import vini2003.xyz.bodyshuffle.common.component.BodyPartComponent;
import vini2003.xyz.bodyshuffle.common.component.TimerComponent;

public class BodyShuffleComponents implements EntityComponentInitializer {
	public static final ComponentKey<BodyPartComponent> BODY_PARTS = ComponentRegistry.getOrCreate(BodyShuffle.identifier("body_parts"), BodyPartComponent.class);
	public static final ComponentKey<TimerComponent> TIMER = ComponentRegistry.getOrCreate(BodyShuffle.identifier("timer"), TimerComponent.class);
	
	public static void initialize() {
	
	}
	
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
		entityComponentFactoryRegistry.registerForPlayers(BODY_PARTS, BodyPartComponent::new);
		entityComponentFactoryRegistry.registerForPlayers(TIMER, TimerComponent::new);
	}
}
