package com.ejs.morevillagesmod;

import com.ejs.morevillagesmod.structure.City;
import com.ejs.morevillagesmod.structure.CityGenerator;
import com.ejs.morevillagesmod.structure.FortifiedVillage;
import com.ejs.morevillagesmod.structure.FortifiedVillageGenerator;
import com.ejs.morevillagesmod.structure.Lighthouse;
import com.ejs.morevillagesmod.structure.LighthouseGenerator;
import com.ejs.morevillagesmod.structure.MushroomHouse;
import com.ejs.morevillagesmod.structure.MushroomHouseGenerator;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

@SuppressWarnings("deprecation")
public class MoreVillagesMod implements ModInitializer {
	  public static final StructurePieceType FORT_VILLAGE_PIECE = FortifiedVillageGenerator.Piece::new;
	  private static final StructureFeature<DefaultFeatureConfig> FORT_STRUCTURE = new FortifiedVillage(DefaultFeatureConfig.CODEC);
	  private static final ConfiguredStructureFeature<?, ?>FORT_CONFIGURED = FORT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	  
	  public static final StructurePieceType MUSHROOM_PIECE = MushroomHouseGenerator.Piece::new;
	  private static final StructureFeature<DefaultFeatureConfig> MUSHROOM_STRUCTURE = new MushroomHouse(DefaultFeatureConfig.CODEC);
	  private static final ConfiguredStructureFeature<?, ?> MUSHROOM_CONFIGURED = MUSHROOM_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	  
	  public static final StructurePieceType CITY_PIECE = CityGenerator.Piece::new;
	  private static final StructureFeature<DefaultFeatureConfig> CITY_STRUCTURE = new City(DefaultFeatureConfig.CODEC);
	  private static final ConfiguredStructureFeature<?, ?> CITY_CONFIGURED = CITY_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	  
	  public static final StructurePieceType LIGHTHOUSE_PIECE = LighthouseGenerator.Piece::new;
	  private static final StructureFeature<DefaultFeatureConfig> LIGHTHOUSE_STRUCTURE = new Lighthouse(DefaultFeatureConfig.CODEC);
	  private static final ConfiguredStructureFeature<?, ?> LIGHTHOUSE_CONFIGURED = LIGHTHOUSE_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	 
	  @Override
	  public void onInitialize() {
	    Registry.register(Registry.STRUCTURE_PIECE, new Identifier("morevillagesmod", "fort_village_piece"), FORT_VILLAGE_PIECE);
	    FabricStructureBuilder.create(new Identifier("morevillagesmod", "fortified_village"), FORT_STRUCTURE)
	      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
	      .defaultConfig(Integer.parseInt(Config.FORT_VILLAGE_SPACING), Integer.parseInt(Config.FORT_VILLAGE_SEPARATION), Integer.parseInt(Config.FORT_VILLAGE_SALT))
	      .adjustsSurface()
	      .register();
	    
	    Registry.register(Registry.STRUCTURE_PIECE, new Identifier("morevillagesmod", "mushroom_house_piece"), MUSHROOM_PIECE);
	    FabricStructureBuilder.create(new Identifier("morevillagesmod", "mushroom_house"), MUSHROOM_STRUCTURE)
	      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
	      .defaultConfig(Integer.parseInt(Config.MUSHROOM_SPACING), Integer.parseInt(Config.MUSHROOM_SEPARATION), Integer.parseInt(Config.MUSHROOM_SALT))
	      .adjustsSurface()
	      .register();
	    
	    Registry.register(Registry.STRUCTURE_PIECE, new Identifier("morevillagesmod", "city_piece"), CITY_PIECE);
	    FabricStructureBuilder.create(new Identifier("morevillagesmod", "city"), CITY_STRUCTURE)
	      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
	      .defaultConfig(Integer.parseInt(Config.CITY_SPACING), Integer.parseInt(Config.CITY_SEPARATION), Integer.parseInt(Config.CITY_SALT))
	      .adjustsSurface()
	      .register();
	    
	    Registry.register(Registry.STRUCTURE_PIECE, new Identifier("morevillagesmod", "lighthouse_piece"), LIGHTHOUSE_PIECE);
	    FabricStructureBuilder.create(new Identifier("morevillagesmod", "lighthouse"), LIGHTHOUSE_STRUCTURE)
	      .step(GenerationStep.Feature.SURFACE_STRUCTURES)
	      .defaultConfig(Integer.parseInt(Config.LIGHTHOUSE_SPACING), Integer.parseInt(Config.LIGHTHOUSE_SEPARATION), Integer.parseInt(Config.LIGHTHOUSE_SALT))
	      .adjustsSurface()
	      .register();
	 
	    RegistryKey<ConfiguredStructureFeature<?, ?>> fortConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("morevillagesmod", "fortified_village"));
	    BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("morevillagesmod", "fortified_village"), FORT_CONFIGURED);
	    BiomeModifications.addStructure(BiomeSelectors.categories(Category.TAIGA, Category.FOREST, Category.PLAINS), fortConfigured);
	    
	    RegistryKey<ConfiguredStructureFeature<?, ?>> mushroomConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("morevillagesmod", "mushroom_house"));
	    BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("morevillagesmod", "mushroom_house"), MUSHROOM_CONFIGURED);
	    BiomeModifications.addStructure(BiomeSelectors.categories(Category.MUSHROOM, Category.FOREST, Category.SWAMP), mushroomConfigured);
	    
	    RegistryKey<ConfiguredStructureFeature<?, ?>> cityConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("morevillagesmod", "city"));
	    BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("morevillagesmod", "city"), CITY_CONFIGURED);
	    BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), cityConfigured);
	    
	    RegistryKey<ConfiguredStructureFeature<?, ?>> lighthouseConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, new Identifier("morevillagesmod", "lighthouse"));
	    BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier("morevillagesmod", "lighthouse"), LIGHTHOUSE_CONFIGURED);
	    BiomeModifications.addStructure(BiomeSelectors.categories(Category.BEACH), lighthouseConfigured);
	  }
}
