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

public class MushroomHouse extends StructureFeature<DefaultFeatureConfig> {
	public MushroomHouse(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return MushroomHouse.Start::new;
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
			BlockPos[] offsets = new BlockPos[new Random().nextInt((6) + 1) + 1];
			for (int j = 0; j < offsets.length; j++) {
				int addX = 0, addZ = 0;
				if (j == 0) {
					addX = 20;
					addZ = 20;
				} else if (j == 1) {
					addX = -20;
					addZ = 20;
				} else if (j == 2) {
					addX = 0;
					addZ = 0;
				} else if (j == 3) {
					addX = -20;
					addZ = -20;
				} else if (j == 4) {
					addX = 20;
					addZ = -20;
				}
				int y = chunkGenerator.getHeight(chunkPos.getStartX() + addX, chunkPos.getStartZ() + addZ,
						Heightmap.Type.WORLD_SURFACE, heightLimitView);
				offsets[j] = new BlockPos(chunkPos.getStartX() + addX, y, chunkPos.getStartZ() + addZ);
			}
			MushroomHouseGenerator.addParts(structureManager, blockPos, blockRotation, this, this.random, FeatureConfig,
					offsets);
		}
	}
}