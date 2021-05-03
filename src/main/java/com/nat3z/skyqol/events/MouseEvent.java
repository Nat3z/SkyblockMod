package com.nat3z.skyqol.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class MouseEvent extends Event {
	
	public final int buttonClicked;
	
	public MouseEvent(int MouseButtonClick) {
		this.buttonClicked = MouseButtonClick;
	}
	
}
