package thexnator.furnituremod.util.renderers;

import thexnator.furnituremod.util.utilclasses.math.PartialTicksUtil;

public class PartialTicks1F{
	
	public float value,prevValue;
	
	public PartialTicks1F(){}
	public PartialTicks1F(float value){
		this.value=prevValue=value;
	}
	
	public void update(float newValue){
		prevValue=value;
		value=newValue;
	}
	
	public float get(){
		return PartialTicksUtil.calculatePos(prevValue, value);
	}
}
