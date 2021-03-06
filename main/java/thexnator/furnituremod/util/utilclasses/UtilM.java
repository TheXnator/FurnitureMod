package thexnator.furnituremod.util.utilclasses;

import static com.mojang.realmsclient.gui.ChatFormatting.GOLD;
import static com.mojang.realmsclient.gui.ChatFormatting.RESET;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableMap;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.furnituremod.Reference;
import thexnator.furnituremod.util.utilclasses.math.PartialTicksUtil;
import thexnator.furnituremod.util.utilobjects.ColorF;
import thexnator.furnituremod.util.utilobjects.vectors.Plane;
import thexnator.furnituremod.util.utilobjects.vectors.RayDeprecated;
import thexnator.furnituremod.util.utilobjects.vectors.Vec2i;
import thexnator.furnituremod.util.utilobjects.vectors.Vec3M;

public class UtilM{
	
	public class U extends UtilM{}
	
	
	public static final float p=1F/16F;
//	@SideOnly(value=Side.CLIENT)
//	public static void spawnEntityFX(EntityFX particleFX){
//		
//		if(isRemote(particleFX)){
//			Minecraft mc=U.getMC();
//			Entity ent=mc.getRenderViewEntity();
//			if(ent!=null&&mc.effectRenderer!=null){
//				int i=mc.gameSettings.particleSetting;
//				double d6=ent.posX-particleFX.posX,d7=ent.posY-particleFX.posY,d8=ent.posZ-particleFX.posZ,d9=Math.sqrt(mc.gameSettings.renderDistanceChunks)*45;
//				if(!(i>1)&&!(d6*d6+d7*d7+d8*d8>d9*d9)&&RandUtil.RB(Config.getParticleAmount()))Get.Render.ER().addEffect(particleFX);
//			}
//		}
//	}
//	@SideOnly(value=Side.CLIENT)
//	public static void spawnEntityFX(EntityFX particleFX,int distance){
//		if(particleFX.worldObj.isRemote){
//			Minecraft mc=U.getMC();
//			Entity ent=mc.getRenderViewEntity();
//			if(ent!=null&&mc.effectRenderer!=null){
//				int i=mc.gameSettings.particleSetting;
//				double d6=ent.posX-particleFX.posX,d7=ent.posY-particleFX.posY,d8=ent.posZ-particleFX.posZ;
//				if(!(i>1)&&!(d6*d6+d7*d7+d8*d8>distance*distance)&&RandUtil.RB(Config.getParticleAmount()))Get.Render.ER().addEffect(particleFX);
//			}
//		}
//	}
	public static Entity spawnEntity(Entity entity){
		if(isRemote(entity))return null;
		entity.worldObj.spawnEntityInWorld(entity);
		entity.forceSpawn=true;
		return entity;
	}
	@SideOnly(value=Side.CLIENT)public static Minecraft getMC(){return Minecraft.getMinecraft();}
	@SideOnly(value=Side.CLIENT)public static World getTheWorld(){return U.getMC().theWorld;}
	@SideOnly(value=Side.CLIENT)public static EntityPlayer getThePlayer(){return U.getMC().thePlayer;}
	public static int booleanToInt(boolean bool){if(bool)return 1;return 0;}
	public static boolean intToBoolean(int i){return i==1;}
	
	/**
	 * Returns false if all objects are not null and it returns true if any of object/s are true
	 * Note: you'll might need to add "!" on using it
	 * @param objects a
	 * @return a
	 */
	public static boolean isNull(Object...objects){
		for(Object object:objects)if(object==null)return true;
		return false;
	}
	/**
	 * Returns if stack contains a specific item
	 * Note: no danger of null pointer exception!
	 * @param item a
	 * @param stack a
	 * @return a
	 */
	public static boolean isItemInStack(Item item,ItemStack stack){
		return stack!=null&&stack.getItem()==item;
	}
	public static boolean isInteger(String str){
		if(str==null)return false;
		int length=str.length();
		if(length==0)return false;
		int i=0;
		if(str.charAt(0)=='-'){
			if(length==1)return false;
			i=1;
		}for(;i<length;i++){
			char c=str.charAt(i);
			if(c<='/'||c>=':')return false;
		}return true;
	}
	public static boolean isBoolean(String str){
		return str!=null&&(str.equals("true")||str.equals("false"));
	}
//	public static void sendMessage(AbstractPacket message){
//		if(isNull(message))return;
//		if(message instanceof AbstractToServerMessage)Furnt.NETWORK_CHANNEL.sendToServer(message);
//		else if(message instanceof AbstractToClientMessage){
//			AbstractToClientMessage msg=(AbstractToClientMessage)message;
//			if(msg.target!=null&&!msg.target.world.isRemote){
//					 if(msg.target.typeOfSending==TypeOfSending.ToPlayer)Magiology.NETWORK_CHANNEL.sendTo(message,(EntityPlayerMP)msg.target.player);
//				else if(msg.target.typeOfSending==TypeOfSending.ToAll)Magiology.NETWORK_CHANNEL.sendToAll(message);
//				else if(msg.target.typeOfSending==TypeOfSending.AroundPoint)Magiology.NETWORK_CHANNEL.sendToAllAround(msg, msg.target.point);
//				else if(msg.target.typeOfSending==TypeOfSending.ToDimension)Magiology.NETWORK_CHANNEL.sendToDimension(msg, msg.target.world.provider.getDimensionId());
//			}
//		}
//	}
	public static float handleSpeedFolower(float speed, float pos,float wantedPos,float acceleration){
	return (float)handleSpeedFolower((double)speed, (double)pos, (double)wantedPos, (double)acceleration);}
	public static double handleSpeedFolower(double speed, double pos,double wantedPos,double acceleration){
		if(pos==wantedPos)return speed;
		if(pos>wantedPos)speed-=acceleration;
		else speed+=acceleration;
		return speed;
	}
	public static void playSoundAtEntity(Object name,Entity entity,double volume,double pitch){
		if(entity.worldObj.isRemote)return;
		entity.worldObj.playSoundAtEntity(entity,(Reference.MODID+":"+name.toString()),(float)volume,(float)pitch);
	}
	@SideOnly(value=Side.CLIENT)
	public static int getFPS(){
		return Minecraft.getDebugFPS();
	}
	public static World getWorld(Object object){
		if(object instanceof Entity)return((Entity)object).worldObj;
		if(object instanceof World)return((World)object);
		if(object instanceof TileEntity)return((TileEntity)object).getWorld();
		if(object instanceof EntityEvent)return((EntityEvent)object).entity.worldObj;
		if(object instanceof BlockEvent)return((BlockEvent)object).world;
		PrintUtil.println("Given object has no data reference to world!");
		return null;
	}
	public static boolean isRemote(Object object){
		return getWorld(object).isRemote;
	}
	public static boolean isArray(Object object){
		return object!=null&&object.getClass().isArray();
	}
	public static<T>boolean isInArray(T object,T[] array){
		return getPosInArray(object, array)>=0;
	}
	public static<T>int getPosInArray(T object,T[] array){
		if(isNull(object,array))return -1;
		if(array.length==0||isArray(object))return -1;
		int pos=-2;
		for(int a=0;a<array.length;a++)if(array[a]==object){
			pos=a;a=array.length;
		}
		return pos;
	}
	public static void exit(int Int){
		FMLCommonHandler.instance().exitJava(Int, false);
	}
	@SideOnly(value=Side.CLIENT)
	public static void exitSoft(){
		getMC().shutdown();
	}
	public static boolean isAny(Object tester,Object... objects){
		for(Object object:objects)if(tester==object)return true;
		return false;
	}
	@SideOnly(Side.CLIENT)
	public static float fluctuateSmooth(double speed, double offset){
		float
			fluctuate=fluctuate(speed, offset),
			prevFluctuate=fluctuate(speed, offset-1);
		return PartialTicksUtil.calculatePos(prevFluctuate, fluctuate);
	}
	@SideOnly(Side.CLIENT)
	public static float fluctuate(double speed, double offset){
		long wtt=(long)(getTheWorld().getTotalWorldTime()+offset);
		double helper=(wtt%speed)/(speed/2F);
		return (float) (helper>1?2-helper:helper);
	}
	public static int colorToCode(Color color){
		return color.hashCode();
	}
	public static int rgbByteToCode(int r,int g,int b,int alpha){
		return colorToCode(new Color(r, g, b, alpha));
	}
	public static int rgbPercentageToCode(double r, double g, double b, double alpha){
		int r1=(int)(255*r), g1=(int)(255*g), b1=(int)(255*b), alpha1=(int)(255*alpha);
		return rgbByteToCode(r1, g1, b1, alpha1);
	}
	public static int[] colorToRGBABByte(Color color){
		return new int[]{color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha()};
	}
	public static int[] codeToRGBABByte(int code){
		return colorToRGBABByte(new Color(code));
	}
	public static float[] colorToRGBABPercentage(Color color){
		int[] data=colorToRGBABByte(color);
		return new float[]{(data[0])/255F,(data[1])/255F,(data[2])/255F,(data[3])/255F};
	}
	public static float[] codeToRGBABPercentage(int code){
		return colorToRGBABPercentage(new Color(code,true));
	}
	public static ColorF codeToColorF(int code){
		float[] data=codeToRGBABPercentage(code);
		return new ColorF(data[0],data[1],data[2],data[3]);
	}
	public static boolean intersectLinePlane(RayDeprecated ray,Plane plane, Vec3M result){
		//TODO: fix this shit! Use GeometryUtil
		
		if(result==null){
			PrintUtil.println("Result is null!\nResult can't be set if it is null!\nInitialize it!\n------------");
			return false;
		}
		
		boolean printProcess=FALSE();
		
		
		boolean xz=
				plane.q.x==plane.r.x&&
				plane.r.x==plane.s.x&&
				plane.r.x==plane.s.x;
		if(!xz){
			
			if(ray.from.z>ray.to.z){
				Vec3M helper=ray.from;
				ray.from=ray.to;
				ray.to=helper;
			}
			double z=plane.q.z;
			if(ray.from.z>z){if(printProcess)PrintUtil.println("target behind");return false;}
			if(ray.to.z<z){if(printProcess)PrintUtil.println("target to far");return false;}
			AxisAlignedBB Plane=new AxisAlignedBB(plane.q.x, plane.q.y, plane.q.z, plane.s.x, plane.s.y, plane.s.z+0.01);
			MovingObjectPosition rayTrace=Plane.calculateIntercept(ray.from.add(0, 0.1, 0).conv(), ray.to.add(0, 0.1, 0).conv());
			if(rayTrace==null||rayTrace.hitVec==null){if(printProcess)PrintUtil.println("target clipped out");return false;}
			result.x=rayTrace.hitVec.xCoord;
			result.y=rayTrace.hitVec.yCoord;
			result.z=rayTrace.hitVec.zCoord;
			
			if(printProcess)PrintUtil.println("Ray trace has resolwed a valid intersection point!");
			return true;
		}
		
		return false;
	}
	
	public static ColorF calculateRenderColor(ColorF prevColor, ColorF color){
		return new ColorF(PartialTicksUtil.calculatePos(prevColor.r, color.r),
						  PartialTicksUtil.calculatePos(prevColor.g, color.g),
						  PartialTicksUtil.calculatePos(prevColor.b, color.b),
						  PartialTicksUtil.calculatePos(prevColor.a, color.a));
	}
	public static ColorF slowlyEqalizeColor(ColorF variable, ColorF goal, float speed){
		return new ColorF(slowlyEqualize(variable.r, goal.r, speed),
						  slowlyEqualize(variable.g, goal.g, speed),
						  slowlyEqualize(variable.b, goal.b, speed),
						  slowlyEqualize(variable.a, goal.a, speed));
	}
	public static Vec3M getEyePosition(Entity entity){
		return getEntityPos(entity).addY(entity.getEyeHeight());
	}
//	public static String getStringForSize(String text, float allowedWidth){
//		if(text.isEmpty())return text;
//		String Return=""+text;
//		while(TessUtil.getFontRenderer().getStringWidth(Return)>allowedWidth){
//			Return=Return.substring(0, Return.length()-1);
//		}
//		return Return;
//	}
	public static String getStackTrace(){
		StringBuilder Return=new StringBuilder();
		
		StackTraceElement[] a1=Thread.currentThread().getStackTrace();
		int length=0;
		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal=Calendar.getInstance();
		Return.append("Invoke time: ").append(dateFormat.format(cal.getTime())).append("\n");
		for(int i=2;i<a1.length;i++){
			StackTraceElement a=a1[i];
			String s=a.toString();
			Return.append(s).append("\n");
			length=Math.max(s.length(),length);
		}
		for(int b=0;b<length/4;b++)Return.append("_/\\_");
		
		return Return.toString();
	}
//	public static void spawnEntityFXAt(TileEntity tileEntity, EntityFX entityFX){
//		entityFX.posX+=x(tileEntity);
//		entityFX.posY+=y(tileEntity);
//		entityFX.posZ+=z(tileEntity);
//		spawnEntityFX(entityFX);
//	}
	public static TargetPoint TargetPoint(TileEntity tile, int range){
		return new TargetPoint(tile.getWorld().provider.getDimensionId(), x(tile), y(tile), z(tile), range);
	}
	public static boolean instanceOf(Object tester, Object instance){
		return instanceOf(tester.getClass(), instance.getClass());
	}
	@SuppressWarnings("rawtypes")
	public static boolean instanceOf(Object tester, Class instance){
		return instanceOf(tester.getClass(), instance);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean instanceOf(Class tester, Class instance){
		try{
			tester.asSubclass(instance);
			return true;
		}catch(Exception ignored){}
		return false;
	}
	public static EntityItem dropBlockAsItem(World world, double x, double y, double z, ItemStack stack){
		if(!world.isRemote&&!world.restoringBlockSnapshots){
			EntityItem entity=new EntityItem(world,x,y,z,stack);
			entity.setPickupDelay(0);
			spawnEntity(entity);
			return entity;
		}
		return null;
	}
	public static boolean axisAlignedBBEqual(AxisAlignedBB box1, AxisAlignedBB box2){
		if(box1==box2) return true;
		return !isNull(box1, box2)&&box1.minX==box2.minX&&box1.minY==box2.minY&&box1.minZ==box2.minZ&&box1.maxX==box2.maxX&&box1.maxY==box2.maxY&&box1.maxZ==box2.maxZ;
	}
	
	public static final PropertyInteger META=PropertyInteger.create("meta", 0, 15);
	public static int getBlockMetadata(World world, BlockPos pos){
		return hasMetaState(world, pos)?getBlock(world, pos).getMetaFromState(world.getBlockState(pos)):0;
	}
	public static void setMetadata(World world, BlockPos pos,int meta){
		if(hasMetaState(world, pos))world.setBlockState(pos, world.getBlockState(pos).withProperty(META, meta), 3);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean hasMetaState(World world, BlockPos pos){
		ImmutableMap i=world.getBlockState(pos).getProperties();
		return i.keySet().contains(META);
	}
	
	public static Block getBlock(IBlockAccess world, BlockPos pos){
		return world.getBlockState(pos).getBlock();
	}
	public static Block getBlock(World world, int x, int y, int z){
		return getBlock(world, new BlockPos(x, y, z));
	}
	public static void setBlock(World world, BlockPos pos,Block block){
		world.setBlockState(pos, block.getDefaultState(), 3);
	}
	public static void setBlock(World world, BlockPos pos, Block block, int meta){
		world.setBlockState(pos, block.getDefaultState().withProperty(META, meta), 3);
	}
	public static BlockPos BlockPos(int[] array3i){
		return new BlockPos(array3i[0], array3i[1], array3i[2]);
	}
	public static int x(TileEntity tile){
		return tile.getPos().getX();
	}
	public static int y(TileEntity tile){
		return tile.getPos().getZ();
	}
	public static int z(TileEntity tile){
		return tile.getPos().getY();
	}
	public static AxisAlignedBB setAABB(AxisAlignedBB box,String varName, double value){
		if(varName.length()!=4)throw new IllegalStateException("use minY, minZ, maxX, maxY, maxZ");
		if(varName.startsWith("min")){
			if(varName.endsWith("X"))return new AxisAlignedBB(value, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
			if(varName.endsWith("Y"))return new AxisAlignedBB(box.minX, value, box.minZ, box.maxX, box.maxY, box.maxZ);
			if(varName.endsWith("Z"))return new AxisAlignedBB(box.minX, box.minY, value, box.maxX, box.maxY, box.maxZ);
		}else if(varName.startsWith("max")){
			if(varName.endsWith("X"))return new AxisAlignedBB(box.minX, box.minY, box.minZ, value, box.maxY, box.maxZ);
			if(varName.endsWith("Y"))return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, value, box.maxZ);
			if(varName.endsWith("z"))return new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, value);
		}
		return null;
	}
	public static BlockPos[] BlockPosArray(int[] pos1, int[] pos2, int[] pos3){
		BlockPos[] result=new BlockPos[0];
		for(int i=0;i<pos1.length;i++)result=ArrayUtils.add(result, new BlockPos(pos1[i],pos2[i],pos3[i]));
		return result;
	}
	public static boolean TRUE(){
		return true;
	}
	public static boolean FALSE(){
		return false;
	}
	public static String signature(){
		return signature(RESET);
	}
	public static String signature(ChatFormatting... colorAfter){
		String result=GOLD+"["+ChatFormatting.DARK_GREEN+Reference.MOD_NAME+GOLD+"] ";
		for(ChatFormatting a:colorAfter)result+=a;
		return result;
	}
	@SideOnly(value=Side.CLIENT)
	public static float getGuiScale(){
		return Math.max(getGuiScaleRaw()/4F,1);
	}
	@SideOnly(value=Side.CLIENT)
	public static int getGuiScaleRaw(){
		return new ScaledResolution(getMC()).getScaleFactor();
	}
	public static void sleep(int time){
		try{
			Thread.sleep(time);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Vec3M getEntityPos(Entity entity){
		return new Vec3M(entity.posX, entity.posY, entity.posZ);
	}
	public static double getDistance(Entity entity,int x,int y, int z){
		Vec3M entityPos=new Vec3M(entity.posX, entity.posY, entity.posZ);
		Vec3M blockPos=new Vec3M(x+0.5, y+0.5, z+0.5);
		return entityPos.distanceTo(blockPos);
	}
	public static double getDistance(TileEntity tile,int x,int y, int z){
		Vec3M entityPos=new Vec3M(tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getY());
		Vec3M blockPos=new Vec3M(x+0.5, y+0.5, z+0.5);
		return entityPos.distanceTo(blockPos);
	}
	public static float round(float d, int decimalPlace){
		return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
	}
	@SideOnly(Side.CLIENT)
	public static long getWorldTime(){
		return getTheWorld().getTotalWorldTime();
	}
	public static long getWorldTime(Object worldContainer){
		return getWorld(worldContainer).getTotalWorldTime();
	}
	public static String[] stringNewlineSplit(String toSplit){
		return toSplit.split("\\r\\n|\\n\\r|\\r|\\n");
	}
	//
	public static boolean isRemote(){
		return FMLCommonHandler.instance().getEffectiveSide()==Side.CLIENT;
	}
	public static String join(Object[] args){
		StringBuilder result=new StringBuilder();
		for(Object o:args)result.append(o);
		return result.toString();
	}
	public static String join(CharSequence splitter,Object[] args){
		StringBuilder result=new StringBuilder();
		for(Object o:args)result.append(o).append(splitter);
		return result.substring(0, result.length()-splitter.length());
	}
	
	private static long startTime;
	public static void startTime(){
		startTime=System.currentTimeMillis();
	}
	public static long endTime(){
		return System.currentTimeMillis()-startTime;
	}
	public static void printTime(){
		PrintUtil.println(endTime());
	}
	public static <T, E> T getMapKey(Map<T, E> map, E value){
		for(Entry<T, E> entry : map.entrySet()){
			if(Objects.equals(value, entry.getValue())){
				return entry.getKey();
			}
		}
		return null;
	}
	public static <T, E> Set<T> getMapKeySet(Map<T, E> map, E value){
		return 
			 map.entrySet()
			.stream()
			.filter(entry->Objects.equals(entry.getValue(), value))
			.map(Map.Entry::getKey)
			.collect(Collectors.toSet());
	}
	
	public static Object objFromString(String s)throws IOException,ClassNotFoundException{
		byte[] data=Base64.getDecoder().decode(s);
		ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(data));
		Object o=ois.readObject();
		ois.close();
		return o;
	}
	public static String objToString(Serializable o)throws IOException{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream( baos );
		oos.writeObject(o);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray()); 
	}
	public static Vec2i[] arrangeStrings(final String[]strings,int lines,int marginX,int marginY){
		//FontRenderer fr=Font.FR();
		Vec2i[] result=new Vec2i[strings.length];
		int columns=(int)Math.floor(strings.length/(float)lines)+1;
		String[][] formattedStrings=new String[columns][lines];
		
		
		int[] longestInColumn=new int[columns],columnOffsets=new int[columns];
		for(int i=0;i<columns;i++){
			for(int j=0;j<lines;j++){
				int id=i*(columns+1)+j;
				if(id<strings.length)formattedStrings[i][j]=strings[id];
			}
		}
		for(int i=0;i<formattedStrings.length;i++)while(ArrayUtils.contains(formattedStrings[i], null))formattedStrings[i]=ArrayUtils.removeElement(formattedStrings[i], null);
		//for(int i=0;i<columns;i++)for(int j=0;j<formattedStrings[i].length;j++)longestInColumn[i]=Math.max(longestInColumn[i], fr.getStringWidth(formattedStrings[i][j]));
		for(int i=0;i<columns;i++){
			columnOffsets[i]=marginX;
			for(int j=0;j<i;j++)columnOffsets[i]+=longestInColumn[j]+marginX;
		}
		
		//for(int i=0;i<strings.length;i++)result[i]=new Vec2i(columnOffsets[(i/lines)%columns], (i%lines)*(fr.FONT_HEIGHT+marginY)+marginY);
		return result;
	}

	public static boolean playerEqual(EntityPlayer player, EntityPlayer player2){
		if(player==null||player2==null)return false;
		return player.getGameProfile().getId().equals(player2.getGameProfile().getId());
	}
	public static float[] countedArray(float start, float end){
		float[] result=new float[(int)(end-start)];
		for(int i=0;i<result.length;i++)result[i]=i;
		return null;
	}
	public static int[] countedArray(int start, int end){
		int[] result=new int[end-start];
		for(int i=0;i<result.length;i++)result[i]=i+start;
		return result;
	}
	
	public static List<Vec3M> dotsOnRay(Vec3M start, Vec3M end, float differenceBetweenDots){
		List<Vec3M> result=new ArrayList<>();
		
		Vec3M 
			difference=start.sub(end),
			direction=difference.normalize();
		
		float 
			lenght=difference.length(),
			posMul=differenceBetweenDots;
		
		result.add(start);
		while(posMul<lenght){
			result.add(direction.mul(posMul).add(end));
			posMul+=differenceBetweenDots;
		}
		
		result.add(end);
		
		return result;
	}
	public static double slowlyEqualize(double variable, double goal, double speed){
		return slowlyEqualize((float)variable, (float)goal, (float)speed);
	}
	public static float slowlyEqualize(float variable, float goal, float speed){
		if(speed==0)return variable;
		speed=Math.abs(speed);
		if(variable+speed>goal&&(Math.abs((variable+speed)-goal)<speed*1.001))return goal;
		if(variable-speed<goal&&(Math.abs((variable-speed)-goal)<speed*1.001))return goal;
		
		if(variable<goal)variable+=speed;
		else if(variable>goal)variable-=speed;
		return variable;
	}
	
	public static boolean isInvEmpty(IInventory inv){
		return isInvEmpty(inventoryToList(inv));
	}
	public static boolean isInvEmpty(List<ItemStack> inv){
		return inv.stream().allMatch(stack->stack==null||stack.stackSize==0);
	}
	public static List<ItemStack> inventoryToList(IInventory inv){
		List<ItemStack> result=new ArrayList<>();
		int size=inv.getSizeInventory();
		for(int i=0;i<size;i++)result.add(inv.getStackInSlot(i));
		return result;
	}
	
	public static String toString(Object... objs){
		StringBuilder print=new StringBuilder();
		
		if(objs!=null)for(int i=0;i<objs.length;i++){
			Object a=objs[i];
			if(isArray(a))print.append(arrayToString(a));
			else print.append(toString(a)+(i==objs.length-1?"":" "));
		}else print.append("null");
		
		return print.toString();
	}
	public static String toString(Object obj){
		StringBuilder print=new StringBuilder();
		
		if(obj!=null){
			if(isArray(obj))print.append(arrayToString(obj));
			else print.append(obj.toString());
		}else print.append("null");
		
		return print.toString();
	}
	
	public static StringBuilder arrayToString(Object array){
		StringBuilder print=new StringBuilder();
		
		print.append("[");
		
		if(array instanceof boolean[]){
			boolean[] b=(boolean[])array;
			for(int i=0;i<b.length;i++){
				boolean c=b[i];
				if(isArray(c))print.append(arrayToString(c));
				else print.append(c+(i==b.length-1?"":" "));
			}
		}
		else if(array instanceof float[]){
			float[] b=(float[])array;
			for(int i=0;i<b.length;i++){
				float c=b[i];
				if(isArray(c))print.append(arrayToString(c));
				else print.append(c+(i==b.length-1?"":" "));
			}
		}
		else if(array instanceof int[]){
			int[] b=(int[])array;
			for(int i=0;i<b.length;i++){
				int c=b[i];
				if(isArray(c))print.append(arrayToString(c));
				else print.append(c+(i==b.length-1?"":" "));
			}
		}
		else if(array instanceof double[]){
			double[] b=(double[])array;
			for(int i=0;i<b.length;i++){
				double c=b[i];
				if(isArray(c))print.append(arrayToString(c));
				else print.append(c+(i==b.length-1?"":" "));
			}
		}
		else if(array instanceof Object[]){
			Object[] b=(Object[])array;
			for(int i=0;i<b.length;i++){
				Object c=b[i];
				if(isArray(c))print.append(arrayToString(c));
				else print.append(toString(c)+(i==b.length-1?"":" "));
			}
		}else throw new IllegalStateException("Given object is not an array!");
		
		print.append("]");
		
		return print;
	}
	public static boolean isTileInWorld(TileEntity tile){
		if(!tile.hasWorldObj())return false;
		return tile.getWorld().getTileEntity(tile.getPos())==tile;
	}
}
