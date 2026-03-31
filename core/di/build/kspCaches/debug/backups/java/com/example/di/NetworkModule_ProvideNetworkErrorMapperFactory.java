package com.example.di;

import com.example.network.mapper.NetworkErrorMapper;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
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
public final class NetworkModule_ProvideNetworkErrorMapperFactory implements Factory<NetworkErrorMapper> {
  private final NetworkModule module;

  private final Provider<Gson> gsonProvider;

  private NetworkModule_ProvideNetworkErrorMapperFactory(NetworkModule module,
      Provider<Gson> gsonProvider) {
    this.module = module;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public NetworkErrorMapper get() {
    return provideNetworkErrorMapper(module, gsonProvider.get());
  }

  public static NetworkModule_ProvideNetworkErrorMapperFactory create(NetworkModule module,
      Provider<Gson> gsonProvider) {
    return new NetworkModule_ProvideNetworkErrorMapperFactory(module, gsonProvider);
  }

  public static NetworkErrorMapper provideNetworkErrorMapper(NetworkModule instance, Gson gson) {
    return Preconditions.checkNotNullFromProvides(instance.provideNetworkErrorMapper(gson));
  }
}
