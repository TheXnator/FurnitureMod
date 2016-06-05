package thexnator.furnituremod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;

public class Coord implements Cloneable {
	public int x;
	public int y;
	public int z;
	
	protected static Minecraft mc = Minecraft.getMinecraft();
	/** Get Player Coordinates */
	public static int GetXCoordinate()
	{
		return (int) Math.floor(mc.thePlayer.posX);
	}
	public static int GetYCoordinate()
	{
		return (int) Math.floor(mc.thePlayer.posY);
	}
	public static int GetZCoordinate()
	{
		return (int) Math.floor(mc.thePlayer.posZ);
	}

	public Coord(int x, int y, int z) {
		int chunkX = GetXCoordinate() / 16;
		int chunkY = GetYCoordinate() / 16;
		int chunkZ = GetZCoordinate() / 16;
		int inChunkX = GetXCoordinate() % 16;
		int inChunkY = GetYCoordinate() % 16;
		int inChunkZ = GetZCoordinate() % 16;
		String daChunk = ("" + Integer.toString(inChunkX) + " " + Integer.toString(inChunkY) + " " + Integer.toString(inChunkZ) + " in " + Integer.toString(chunkX) + " " + Integer.toString(chunkY) + " " + Integer.toString(chunkZ)); 
		mc.fontRendererObj.drawString("C: " + daChunk + "", 2, 32, 0xffffff);
	}

	public Coord(double x, double y, double z) {
		this.x = GetXCoordinate();
		this.y = GetYCoordinate();
		this.z = GetZCoordinate();
	}

	public Coord(EnumFacing direction) {
		this(direction.getFrontOffsetX(), direction.getFrontOffsetY(), direction.getFrontOffsetZ());
	}

	public Coord(int[] coords) {
		this(coords[0], coords[1], coords[2]);
	}

	public Coord(Chunk pos) {
		this(GetXCoordinate(), GetYCoordinate(), GetZCoordinate());
	}

	public Coord(Vec3 vec) {
		this(vec.xCoord, vec.yCoord, vec.zCoord);
	}

	public Coord offset(EnumFacing direction) {
		return new Coord(x + direction.getFrontOffsetX(), y + direction.getFrontOffsetY(), z + direction.getFrontOffsetZ());
	}

	public Coord offset(int ox, int oy, int oz) {
		return new Coord(x + ox, y + oy, z + oz);
	}

	@Override
	public int hashCode() {
		return (x + 128) << 16 | (y + 128) << 8 | (z + 128);
	}

	@Override
	public boolean equals(Object that) {
		if (!(that instanceof Coord)) { return false; }
		Coord otherCoord = (Coord)that;
		return otherCoord.x == x && otherCoord.y == y && otherCoord.z == z;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", x, y, z);
	}

	@Override
	public Coord clone() {
		return new Coord(x, y, z);
	}

	public Coord asChunkPosition() {
		return new Coord(x, y, z);
	}

	public Coord asChunkCoordinate() {
		return new Coord(x, y, z);
	}

	public Coord add(Coord other) {
		return new Coord(x + other.x, y + other.y, z + other.z);
	}

	public Coord substract(Coord other) {
		return new Coord(x - other.x, y - other.y, z - other.z);
	}

	public int lengthSq() {
		return x * x + y * y + z * z;
	}

	public double length() {
		return Math.sqrt(lengthSq());
	}

	public Coord getAdjacentCoord(EnumFacing fd) {
		return getOffsetCoord(fd, 1);
	}

	public Coord getOffsetCoord(EnumFacing fd, int distance) {
		return new Coord(x + (fd.getFrontOffsetX() * distance), y + (fd.getFrontOffsetY() * distance), z + (fd.getFrontOffsetZ() * distance));
	}

	public Coord[] getDirectlyAdjacentCoords() {
		return getDirectlyAdjacentCoords(true);
	}

	public Coord[] getDirectlyAdjacentCoords(boolean includeBelow) {
		Coord[] adjacents;
		if (includeBelow) adjacents = new Coord[6];
		else adjacents = new Coord[5];

		adjacents[0] = getAdjacentCoord(EnumFacing.UP);
		adjacents[1] = getAdjacentCoord(EnumFacing.NORTH);
		adjacents[2] = getAdjacentCoord(EnumFacing.EAST);
		adjacents[3] = getAdjacentCoord(EnumFacing.SOUTH);
		adjacents[4] = getAdjacentCoord(EnumFacing.WEST);

		if (includeBelow) adjacents[5] = getAdjacentCoord(EnumFacing.DOWN);

		return adjacents;
	}

	public Coord[] getAdjacentCoords() {
		return getAdjacentCoords(true, true);
	}

	public Coord[] getAdjacentCoords(boolean includeBelow, boolean includeDiagonal) {
		if (!includeDiagonal) return getDirectlyAdjacentCoords(includeBelow);

		Coord[] adjacents = new Coord[(includeBelow? 26 : 17)];

		int index = 0;

		for (int xl = -1; xl < 1; xl++)
			for (int zl = -1; zl < 1; zl++)
				for (int yl = (includeBelow? -1 : 0); yl < 1; yl++)
					if (xl != 0 || zl != 0 || yl != 0) adjacents[index++] = new Coord(x + xl, y + yl, z + zl);

		return adjacents;
	}

	public boolean isAbove(Coord pos) {
		return pos != null? y > pos.y : false;
	}

	public boolean isBelow(Coord pos) {
		return pos != null? y < pos.y : false;
	}

	public boolean isNorthOf(Coord pos) {
		return pos != null? z < pos.z : false;
	}

	public boolean isSouthOf(Coord pos) {
		return pos != null? z > pos.z : false;
	}

	public boolean isEastOf(Coord pos) {
		return pos != null? x > pos.x : false;
	}

	public boolean isWestOf(Coord pos) {
		return pos != null? x < pos.x : false;
	}

	public boolean isXAligned(Coord pos) {
		return pos != null? x == pos.x : false;
	}

	public boolean isYAligned(Coord pos) {
		return pos != null? y == pos.y : false;
	}

	public boolean isZAligned(Coord pos) {
		return pos != null? z == pos.z : false;
	}
}