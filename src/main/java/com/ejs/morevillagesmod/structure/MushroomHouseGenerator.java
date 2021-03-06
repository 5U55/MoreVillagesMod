package com.ejs.morevillagesmod.structure;

import java.util.Random;

import com.ejs.morevillagesmod.MoreVillagesMod;

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

public class MushroomHouseGenerator {
   static final BlockPos DEFAULT_POSITION = new BlockPos(4, 0, 15);
   private static final Identifier[] REGULAR_TEMPLATES = new Identifier[]{new Identifier("morevillagesmod","mushroom_house/mushroom1"), new Identifier("morevillagesmod","mushroom_house/mushroom12"), new Identifier("morevillagesmod","mushroom_house/mushroom13"), new Identifier("morevillagesmod","mushroom_house/mushroom2"), new Identifier("morevillagesmod","mushroom_house/mushroom3")};

   public static void addParts(StructureManager structureManager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder structurePiecesHolder, Random random, DefaultFeatureConfig config, BlockPos[] offsets) {
       for(int i=0; i<offsets.length;i++) {
    	   Identifier identifier = (Identifier)Util.getRandom(REGULAR_TEMPLATES, random);
    	   structurePiecesHolder.addPiece(new MushroomHouseGenerator.Piece(structureManager, identifier, offsets[i], rotation, false));
       }
   }

   public static class Piece extends SimpleStructurePiece {

      public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation, boolean grounded) {
         super(StructurePieceType.JIGSAW, 0, manager, identifier, identifier.toString(), createPlacementData(rotation), pos);
      }

      public Piece(ServerWorld world, NbtCompound nbt) {
         super(MoreVillagesMod.MUSHROOM_PIECE, nbt, world, (identifier) -> {
            return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")));
         });
      }

      protected void writeNbt(ServerWorld world, NbtCompound nbt) {
         super.writeNbt(world, nbt);
         nbt.putString("Rot", this.placementData.getRotation().name());
      }

      private static StructurePlacementData createPlacementData(BlockRotation rotation) {
         return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).setPosition(MushroomHouseGenerator.DEFAULT_POSITION).addProcessor(BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS);
      }


	@Override
	protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random,
			BlockBox boundingBox) {
	}
   }
}
