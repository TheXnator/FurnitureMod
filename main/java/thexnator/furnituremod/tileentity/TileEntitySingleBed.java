package thexnator.furnituremod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySingleBed extends TileEntity
{
	private int colour = 0;

	public void setColour(int colour)
	{
		this.colour = 15 - colour;
	}

	public int getColour()
	{
		return colour;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.colour = par1NBTTagCompound.getInteger("colour");
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setInteger("colour", colour);
		NBTTagList var2 = new NBTTagList();
		par1NBTTagCompound.setTag("tag", var2);	
		super.writeToNBT(par1NBTTagCompound);
	}

	@SuppressWarnings("unused")
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound tagCom = pkt.getNbtCompound();
		//this.readFromNBT(tagCom);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tagCom = new NBTTagCompound();
		//this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
}
