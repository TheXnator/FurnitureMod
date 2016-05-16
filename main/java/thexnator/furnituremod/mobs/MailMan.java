package thexnator.furnituremod.mobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unchecked")
public class MailMan extends EntityCreature 
{

	public MailMan(World worldIn) 
	{
		super(worldIn);
	}

	public static int mobid = 0;
	public Object instance;

    public void load(FMLInitializationEvent event)
    {
		RenderingRegistry.registerEntityRenderingHandler(MailMan.EntitymailMan.class, new RenderBiped(Minecraft.getMinecraft().getRenderManager(), new ModelBiped(), 0, 0){protected ResourceLocation getEntityTexture(Entity par1Entity){return new ResourceLocation("fm", "textures/MailMan.png");}});
	}

    public void generateNether(World world, Random random, int chunkX, int chunkZ)
    {
    	
    }
    
	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		
	}
	
	public void serverLoad(FMLServerStartingEvent event)
	{
		
	}
	
	public void preInit(FMLPreInitializationEvent event)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		mobid = entityID;
		EntityRegistry.registerGlobalEntityID(MailMan.EntitymailMan.class, "MailMan", entityID);
		EntityRegistry.registerModEntity(MailMan.EntitymailMan.class, "MailMan", entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, (102 << 16) + (0 << 8) + 0, (102 << 16) + (102 << 8) + 102));
		EntityRegistry.addSpawn(MailMan.EntitymailMan.class, 0, 3, 30, EnumCreatureType.CREATURE , clean(BiomeGenBase.getBiomeGenArray()));
	}
	
	public static BiomeGenBase[] clean(final BiomeGenBase[] v) 
	{
		List<BiomeGenBase> list = new ArrayList<BiomeGenBase>(Arrays.asList(v));
		list.removeAll(Collections.singleton(null));
		return list.toArray(new BiomeGenBase[list.size()]);
	}

   public static class EntitymailMan extends EntityMob 
	{
		World world = null;
	    public EntitymailMan(World var1)
	    {
	        super(var1);
	        world = var1;
	        experienceValue = 5;
	        this.isImmuneToFire = false;
	        addRandomArmor();
			setNoAI(!true);
        	
			this.tasks.addTask(1, new EntityAILookIdle(this));
			
			this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityBat.class, 6.0F));
			this.tasks.addTask(1, new EntityAISwimming(this));
			this.tasks.addTask(1, new EntityAIOpenDoor(this, true));
			this.tasks.addTask(1, new EntityAIOpenDoor(this, false));
			this.tasks.addTask(1, new EntityAITempt(this, 0.8D, Items.paper, false));
		}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
		if(this.getEntityAttribute(SharedMonsterAttributes.attackDamage)!=null)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1D);
	}
   
	protected void addRandomArmor()
	{
		this.setCurrentItemOrArmor(0, new ItemStack(Items.book));
	}
		
		@Override
		protected Item getDropItem()
		{
			return new ItemStack(Items.paper).getItem();
		}

	    @Override
	    protected String getLivingSound()
	    {
	        return null;
	    }

	    @Override
	    protected String getHurtSound()
	    {
	        return "game.neutral.hurt";
	    }
		
		@Override
	    protected String getDeathSound()
	    {
	        return "game.neutral.die";
	    }

		@Override
	    public void onStruckByLightning(EntityLightningBolt entityLightningBolt)
		{
			super.onStruckByLightning(entityLightningBolt);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
		}

		@Override
		public void fall(float l, float d)
		{
			super.fall(l,d);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			super.fall(l,d);
			Entity entity = this;
		}

		@Override
		public void onDeath(DamageSource source)
		{
			super.onDeath(source);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;			
		}

		@Override
		public boolean interact(EntityPlayer entity)
		{
			super.interact(entity);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			
			return true;
		}
		
		@Override
		protected float getSoundVolume()
		{
        return 1.0F;
		}
		
	}
}
