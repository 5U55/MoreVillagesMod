package com.ejs.morevillagesmod.structure;

import java.util.Random;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class FortifiedVillageGenerator {
   static final BlockPos DEFAULT_POSITION = new BlockPos(4, 0, 15);
   private static final Identifier[] REGULAR_TEMPLATES = new Identifier[]{new Identifier("morevillagesmod","fort_village/village1")};

   public static void addParts(StructureManager structureManager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder structurePiecesHolder, Random random, DefaultFeatureConfig config) {
      Identifier identifier = (Identifier)Util.getRandom(REGULAR_TEMPLATES, random);
      structurePiecesHolder.addPiece(new FortifiedVillageGenerator.Piece(structureManager, identifier, pos, rotation, false));
   }

   public static class Piece extends SimpleStructurePiece {

      public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation, boolean grounded) {
         super(StructurePieceType.SWAMP_HUT, 0, manager, identifier, identifier.toString(), createPlacementData(rotation), pos);
      }

      public Piece(ServerWorld world, NbtCompound nbt) {
         super(StructurePieceType.SWAMP_HUT, nbt, world, (identifier) -> {
            return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")));
         });
      }

      protected void writeNbt(ServerWorld world, NbtCompound nbt) {
         super.writeNbt(world, nbt);
         nbt.putString("Rot", this.placementData.getRotation().name());
      }

      private static StructurePlacementData createPlacementData(BlockRotation rotation) {
         return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).setPosition(FortifiedVillageGenerator.DEFAULT_POSITION).addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
      }

      public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
         int i = chunkGenerator.getHeight(this.pos.getX(), this.pos.getZ(), Heightmap.Type.WORLD_SURFACE, world);
         this.pos = new BlockPos(this.pos.getX(), i, this.pos.getZ());
         return super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
      }

	@Override
	protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random,
			BlockBox boundingBox) {
	}
   }
}
