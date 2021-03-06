package thexnator.furnituremod.util.utilclasses;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thexnator.furnituremod.util.utilclasses.UtilM.U;

public class SideUtil{
	
	static int[] x={0,0,0,1,0,-1};
	static int[] y={1,-1,0,0,0,0};
	static int[] z={0,0,-1,0,1,0};
	static Random rand = new Random();
	
//	switch(EnumFacing){
//	case 0:{y+=1;}break;
//	case 1:{y-=1;}break;
//	case 2:{z-=1;}break;
//	case 3:{x+=1;}break;
//	case 4:{z+=1;}break;
//	case 5:{x-=1;}break;
//	}
	
	public static int getOppositeSide(int side){
		int result=-1;
		switch(side){
		case 0:result=1;break;
		case 1:result=0;break;
		case 2:result=3;break;
		case 3:result=2;break;
		case 4:result=5;break;
		case 5:result=4;break;
		}
//		Helper.printInln(EnumFacing.getFront(5));
		return result;
	}
	
	
	public static BlockPos offset(int side,BlockPos pos){
		return pos.add(x[side],y[side],z[side]);
	}
	public static BlockPos offsetNew(int side,BlockPos pos){
		return pos.offset(EnumFacing.getFront(side));
	}
	public static int[] randomizeSides(){
		int[] side=new int[6];
		side[0]=rand.nextInt(6);
		do{side[1]=rand.nextInt(6);}while(side[1]==side[0]);
		do{side[2]=rand.nextInt(6);}while(side[2]==side[0]
										||side[2]==side[1]);
		do{side[3]=rand.nextInt(6);}while(side[3]==side[0]
										||side[3]==side[1]
										||side[3]==side[2]);
		do{side[4]=rand.nextInt(6);}while(side[4]==side[0]
										||side[4]==side[1]
										||side[4]==side[2]
										||side[4]==side[3]);
		do{side[5]=rand.nextInt(6);}while(side[5]==side[0]
										||side[5]==side[1]
										||side[5]==side[2]
										||side[5]==side[3]
										||side[5]==side[4]);
		return side;
	}


	public static int enumFacingOrientation(EnumFacing fDir){
		if(fDir==null)return -1;
		return fDir.getIndex();
	}
	public static int DOWN(){
		return enumFacingOrientation(EnumFacing.DOWN);
	}
	public static int UP(){
		return enumFacingOrientation(EnumFacing.UP);
	}
	public static int NORTH(){
		return enumFacingOrientation(EnumFacing.NORTH);
	}
	public static int SOUTH(){
		return enumFacingOrientation(EnumFacing.SOUTH);
	}
	public static int WEST(){
		return enumFacingOrientation(EnumFacing.WEST);
	}
	public static int EAST(){
		return enumFacingOrientation(EnumFacing.EAST);
	}
	public static TileEntity[] getTilesOnSides(TileEntity tileEntity){
		return getTilesOnSides(tileEntity.getWorld(), tileEntity.getPos());
	}
	public static TileEntity[] getTilesOnSides(World world, BlockPos pos){
		TileEntity[] result=new TileEntity[6];
		if(!U.isNull(world,pos))for(int i=0;i<6;i++)result[i]=world.getTileEntity(offsetNew(i, pos));
		return result;
	}
}
