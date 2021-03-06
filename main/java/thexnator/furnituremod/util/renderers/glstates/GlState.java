package thexnator.furnituremod.util.renderers.glstates;

import static org.lwjgl.opengl.GL11.glDepthMask;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import thexnator.furnituremod.util.renderers.GL11U;
import thexnator.furnituremod.util.renderers.OpenGLM;

public class GlState{
	
	public static final GlState 
		NORMAL=new GlState(new int[]{GL11.GL_DEPTH, GL11.GL_TEXTURE_2D, GL11.GL_CULL_FACE, GL11.GL_LIGHTING}, new int[]{GL11.GL_BLEND}, ()->{
			OpenGLM.lineWidth(1);
			GlStateManager.color(1, 1, 1, 1);
			GL11U.allOpacityIs(false);
			GlStateManager.depthMask(false);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}),
		STANDARD_OPAQUE=new GlState(new int[]{GL11.GL_BLEND}, new int[]{GL11.GL_ALPHA_TEST}, ()->{
			glDepthMask(false);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11U.allOpacityIs(true);
		});
	
	
	private int[] enable={},disable={};
	private Runnable custom;
	
	public GlState(Runnable custom){
		this.custom=custom;
	}
	public GlState(int[] enable, int[] disable,Runnable custom){
		this(enable,disable);
		this.custom=custom;
	}
	public GlState(int[] enable, int[] disable){
		this.enable=enable;
		this.disable=disable;
	}
	public void configureOpenGl(){
		for(int i:enable)GL11.glEnable(i);
		for(int i:disable)GL11.glDisable(i);
		if(custom!=null)custom.run();
	}
}
