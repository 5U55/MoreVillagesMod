package com.ejs.morevillagesmod;

import com.ejs.morevillagesmod.structure.FortifiedVillage;
import com.ejs.morevillagesmod.structure.FortifiedVillageGenerator;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

@SuppressWarnings("deprecation")
public class MoreVillagesMod implements ModInitializer {
	  public static final StructurePieceType FORT_VILLAGE_PIECE = FortifiedVillageGenerator.Piece::new;
	  private static final StructureFeature<DefaultFeatureConfig> FORT_STRUCTURE = new FortifiedVillage(DefaultFeatureConfig.CODEC);
	  private static final ConfiguredStructureFeature<?, ?> MY_CONFIGURED = FORT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	 
	  @Override
	  public void onInitialize() {
	    Registry.register(Registry.STRUCTURE_PIECE, new Identifier("morevillagesmod", "fort_village_piece"), FORT_VILLAGE_PIECE);
	    FabricStructureBuilder.create(new Identifier("morevillagesmod", "fortified_village"), FORT_STRUCTURE)
	      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
	      .defaultConfig(32, 8, 12345)
	      .adjustsSurface()
	      .register();
	 
	    RegistryKey<ConfiguredStructureFeature<?, ?>> fortConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("morevillagesmod", "fortified_village"));
	    BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("morevillagesmod", "fortified_village"), MY_CONFIGURED);
	    BiomeModifications.addStructure(BiomeSelectors.all(), fortConfigured);
	  }
}
