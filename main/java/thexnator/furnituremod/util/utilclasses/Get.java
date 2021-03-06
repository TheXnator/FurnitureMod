package thexnator.furnituremod.util.utilclasses;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.furnituremod.util.utilclasses.UtilM.U;

public final class Get{
	@SideOnly(value=Side.CLIENT)
	public static final class Render{
		
		public static final ItemRenderer IR(){
			return U.getMC().getItemRenderer();
		}
		public static final RenderItem RI(){
			return U.getMC().getRenderItem();
		}
//		public static final VertexRenderer NVB(){
//			return TessUtil.getVB();
//		}
//		public static final WorldRenderer WR(){
//			return TessUtil.getWR();
//		}
		public static EffectRenderer ER(){
			return U.getMC().effectRenderer;
		}
		public static ItemModelMesher IMM(){
			return U.getMC().getRenderItem().getItemModelMesher();
		}
		public static Tessellator T(){
			return Tessellator.getInstance();
		}
//		public static class Font{
//			public static FontRendererMBase FRB(){
//				return TessUtil.getCustomFontRednerer();
//			}
//
//			public static final FontRenderer FR(){
//				return TessUtil.getFontRenderer();
//			}
//		}
	}
	@SideOnly(value=Side.CLIENT)
	public static final class Client{
		public static final EntityPlayer EP(){
			return U.getThePlayer();
		}
		public static final World W(){
			return U.getTheWorld();
		}
		public static final boolean running(){
			return !U.isNull(EP(),W());
		}
	}
	
}
