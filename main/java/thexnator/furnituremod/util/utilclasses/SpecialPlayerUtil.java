package thexnator.furnituremod.util.utilclasses;

import net.minecraft.entity.player.EntityPlayer;

public final class SpecialPlayerUtil{
	
	private static final String   owner="TheXnator";
	private static final String[] yt={"TheXnator","ChuggyV2"},
								  fr={"ChuggyV2"};
	
	public static int getPlayerRank(EntityPlayer player){
		String plName=player.getName();
		
		if(plName.equals(owner))return 1;
		
		for(int i=0;i<yt.length;i++)if(plName.equals(yt[i]))return 2;
		
		for(int i=0;i<fr.length;i++)if(plName.equals(fr[i]))return 3;
		
		return -1;
	}
	
	
	
}
