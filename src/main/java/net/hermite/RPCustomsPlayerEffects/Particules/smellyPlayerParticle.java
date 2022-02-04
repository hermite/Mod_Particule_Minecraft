package net.hermite.RPCustomsPlayerEffects.Particules;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosionLarge;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
//Fonctions g�rant la particule qui apparait sur un personnage sale
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class smellyPlayerParticle extends Particle {
	private static final ResourceLocation PARTICLES_TEXTURE = new ResourceLocation("rpcpemod:textures/particles/particles.png");
	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	private float initialScale;
	private double initY;
	
	public smellyPlayerParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
				
		//super(Minecraft.getMinecraft().getTextureManager(), worldIn, posXIn, posYIn, posZIn, 0, 0, 0);
				super(worldIn, posXIn, posYIn, posZIn);
				initY = posYIn;
				//setParticleTextureIndex(0);
				//float f4 = (float)Math.random() * 0.2F + 0.3F;
				this.particleRed = 0.26f;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;
				this.particleGreen = 0.36f;//((float)(Math.random() * 0.20000000298023224D) + 1F) * f4;
				this.particleBlue = 0.21f;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;

				this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
				this.particleMaxAge = (int)((float)this.particleMaxAge * 0.9F * 2);
				this.particleAge = 0;
				this.motionX = 0.0D;
				this.motionY = -0.01D;
				this.motionZ = 0.0D;
				this.initialScale = this.particleScale;
				//this.posY += particleAge * -0.009000000078231096d * 10;
	}

	public smellyPlayerParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int ageOffset) {
		//super(Minecraft.getMinecraft().getTextureManager(), worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
				initY = yCoordIn;
				//setParticleTextureIndex(0);
				//float f4 = (float)Math.random() * 0.2F + 0.3F;
				this.particleRed = 0.26f;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;
				this.particleGreen = 0.36f;//((float)(Math.random() * 0.20000000298023224D) + 1F) * f4;
				this.particleBlue = 0.21f;//((float)(Math.random() * 0.20000000298023224D) + 0.8F) * f4;

				this.particleMaxAge = (int)(32.0D / (Math.random() * 0.8D + 0.2D));
				this.particleMaxAge = (int)((float)this.particleMaxAge * 0.9F);
				this.particleAge = 0 + ageOffset;
				this.motionX = 0.0D;
				this.motionY = -0.01D;
				this.motionZ = 0.0D;
				this.initialScale = this.particleScale;
				//this.posY += particleAge * -0.009000000078231096d * 10;
	}

	@Override
	public void renderParticle(BufferBuilder worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {

		if(this.particleAge < 0) return;
		GL11.glPushMatrix();
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, this.particleAlpha);
		GlStateManager.disableLighting();
		RenderHelper.disableStandardItemLighting();

		Minecraft.getMinecraft().getTextureManager().bindTexture(PARTICLES_TEXTURE);
		int i = MathHelper.clamp((int)(((float)this.particleAge + partialTicks) / (float)this.particleMaxAge * 32.0F), 2, 2);
		
		/*float f = (float)(i % 15) / 32.0F;
		float f1 = f + 0.03125f;
		float f2 = (float)(i / 15) / 32.0F + 0.15625f;
		float f3 = f2 + 0.03125f;*/
		
		float f = (float)(i % 16) / 16.0F;
		float f1 = f + 0.0625f;
		float f2 = (float)((i+80) / 16) / 16.0F;
		float f3 = f2 + 0.0625f;
		
		
		float f4 = .15f;//2.0F * this.size;
		float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
		float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
		float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableLighting();
		RenderHelper.disableStandardItemLighting();
		worldRendererIn.begin(7, VERTEX_FORMAT);
		worldRendererIn.pos(
				(double)(f5 - rotationX * f4 - rotationXY * f4),
				(double)(f6 - rotationZ * f4),
				(double)(f7 - rotationYZ * f4 - rotationXZ * f4))
				.tex((double)f1, (double)f3)
				.color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F)
				.lightmap(0, 240)
				.normal(0.0F, 1.0F, 0.0F)
				.endVertex();
		worldRendererIn.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4), (double)(f7 - rotationYZ * f4 + rotationXZ * f4)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldRendererIn.pos((double)(f5 + rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4), (double)(f7 + rotationYZ * f4 + rotationXZ * f4)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		worldRendererIn.pos((double)(f5 + rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4), (double)(f7 + rotationYZ * f4 - rotationXZ * f4)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.enableLighting();
		
		GL11.glPopMatrix();
		GlStateManager.enableLighting();
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge || this.posY > initY + 1 )
		{
			this.setExpired();
		}
		if(this.particleAge < 0) return;
		this.move(0, this.motionY, 0);
		this.motionY += 0.001500000026077032D;
		//this.motionY = Math.max(this.motionY, 0.004000000059604645D);
	}

	public int getFXLayer()
	{
		return 3;
	}
}
