package com.example.di;

import com.example.network.PomodoroApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvidePomodoroApiFactory implements Factory<PomodoroApi> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  private NetworkModule_ProvidePomodoroApiFactory(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PomodoroApi get() {
    return providePomodoroApi(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvidePomodoroApiFactory create(NetworkModule module,
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePomodoroApiFactory(module, retrofitProvider);
  }

  public static PomodoroApi providePomodoroApi(NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(instance.providePomodoroApi(retrofit));
  }
}
