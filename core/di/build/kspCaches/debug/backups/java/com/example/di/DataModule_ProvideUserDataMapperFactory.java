package com.example.di;

import com.example.data.mapper.UserDataMapper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DataModule_ProvideUserDataMapperFactory implements Factory<UserDataMapper> {
  private final DataModule module;

  private DataModule_ProvideUserDataMapperFactory(DataModule module) {
    this.module = module;
  }

  @Override
  public UserDataMapper get() {
    return provideUserDataMapper(module);
  }

  public static DataModule_ProvideUserDataMapperFactory create(DataModule module) {
    return new DataModule_ProvideUserDataMapperFactory(module);
  }

  public static UserDataMapper provideUserDataMapper(DataModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideUserDataMapper());
  }
}
