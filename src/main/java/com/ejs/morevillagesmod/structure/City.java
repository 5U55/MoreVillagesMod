package com.ejs.morevillagesmod.structure;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class City extends StructureFeature<DefaultFeatureConfig> {
	public City(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return City.Start::new;
	}

	public static class Start extends StructureStart<DefaultFeatureConfig> {
		public Start(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
			super(structureFeature, chunkPos, i, l);
		}

		public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator,
				StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig FeatureConfig,
				HeightLimitView heightLimitView) {
			BlockRotation blockRotation = BlockRotation.random(this.random);
			int i = chunkGenerator.getHeight(chunkPos.getStartX(), chunkPos.getStartZ(), Heightmap.Type.WORLD_SURFACE,
					heightLimitView);
			BlockPos blockPos = new BlockPos(chunkPos.getStartX(), i, chunkPos.getStartZ());
			BlockPos[] offsets = new BlockPos[25];
			for (int j = 0; j < offsets.length; j++) {
				int addX = 0, addZ = 0;
				addX=(j%5)*30;
				addZ=Math.floorDiv(j, 5)*30;
				int y = chunkGenerator.getHeight(chunkPos.getStartX() + addX, chunkPos.getStartZ() + addZ,
						Heightmap.Type.WORLD_SURFACE, heightLimitView);
				offsets[j] = new BlockPos(chunkPos.getStartX() + addX, y, chunkPos.getStartZ() + addZ);
			}
			CityGenerator.addParts(structureManager, blockPos, blockRotation, this, this.random, FeatureConfig,
					offsets);
		}
	}
}