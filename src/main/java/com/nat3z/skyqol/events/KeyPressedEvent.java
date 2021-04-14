package com.nat3z.skyqol.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class KeyPressedEvent extends Event {
	public final int key;
	public final boolean status;
	public KeyPressedEvent(int key, boolean status) {
		this.key = key;
		this.status = status;
		
	}
	
}
