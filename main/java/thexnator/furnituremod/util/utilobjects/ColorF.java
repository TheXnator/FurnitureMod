package thexnator.furnituremod.util.utilobjects;

import java.awt.Color;

import thexnator.furnituremod.util.renderers.OpenGLM;
import thexnator.furnituremod.util.utilclasses.math.MathUtil;

public class ColorF{
	public static final ColorF 
		BLACK	 =ColorF.convert(Color.BLACK),
		BLUE	  =ColorF.convert(Color.BLUE),
		CYAN	  =ColorF.convert(Color.CYAN),
		DARK_GRAY =ColorF.convert(Color.DARK_GRAY),
		GRAY	  =ColorF.convert(Color.GRAY),
		GREEN	  =ColorF.convert(Color.GREEN),
		LIGHT_GRAY=ColorF.convert(Color.LIGHT_GRAY),
		MAGENTA	  =ColorF.convert(Color.MAGENTA),
		ORANGE	  =ColorF.convert(Color.ORANGE),
		PINK	  =ColorF.convert(Color.PINK),
		RED	  	  =ColorF.convert(Color.RED),
		WHITE	  =ColorF.convert(Color.WHITE),
		YELLOW	  =ColorF.convert(Color.YELLOW);
	public float r,g,b,a;
	
	public ColorF(double r, double g, double b, double a){
		this.r=(float)MathUtil.snap(r, 0, 1);
		this.g=(float)MathUtil.snap(g, 0, 1);
		this.b=(float)MathUtil.snap(b, 0, 1);
		this.a=(float)MathUtil.snap(a, 0, 1);
	}
	public ColorF(){
		this(1,1,1,1);
	}
	public ColorF blackNWhite(){
		float sum=(r+g+b)/3F;
		return new ColorF(sum,sum,sum,a);
	}
	public ColorF negative(){
		return new ColorF(1-r, 1-g, 1-b, 1-a);
	}
	public ColorF disablBlend(){
		return new ColorF(r,g,b,1);
	}
	public void bind(){
		OpenGLM.color(r,g,b,a);
	}
	public int toCode(){
		return new Color(r,g,b,a).hashCode();
	}
	public ColorF mix(Color color){
		return mix(convert(color));
	}
	public ColorF mix(ColorF color){
		return new ColorF((r+color.r)/2F,(g+color.g)/2F,(b+color.b)/2F,(a+color.a)/2F);
	}
	public ColorF mix(Color color, float scale1,float scale2){
		return mix(convert(color), scale1, scale2);
	}
	public ColorF mix(ColorF color, float scale1,float scale2){
		return new ColorF(
				(r*scale1+color.r*scale2)/(scale1+scale2),
				(g*scale1+color.g*scale2)/(scale1+scale2),
				(b*scale1+color.b*scale2)/(scale1+scale2),
				(a*scale1+color.a*scale2)/(scale1+scale2)
				);
	}
	public ColorF set(float modifier, char c){
		modifier=MathUtil.snap(modifier, 0, 1);
		return new ColorF(c=='r'?modifier:r, c=='g'?modifier:g, c=='b'?modifier:b, c=='a'?modifier:a);
	}
	public ColorF copy(){
		return new ColorF(r, g, b, a);
	}
	@Override
	public String toString(){
		return "("+(r+"").substring(0, Math.min((r+"").length(),4))+", "+(g+"").substring(0, Math.min((g+"").length(),4))+", "+(b+"").substring(0, Math.min((b+"").length(),4))+", "+(a+"").substring(0, Math.min((a+"").length(),4))+")";
	}
	public static ColorF convert(Color color){
		return new ColorF(color.getRed()/256F, color.getGreen()/256F, color.getBlue()/256F, color.getAlpha()/256F);
	}
	public ColorF mul(double r, double g, double b, double a){
		return new ColorF(this.r*r, this.g*g, this.b*b, this.a*a);
	}
	public ColorF mul(double var){
		return new ColorF(r*var, g*var, b*var, a*var);
	}

	public ColorF add(ColorF color){
		return new ColorF(color.r+r,color.g+g,color.b+b,color.a+a);
	}
}
