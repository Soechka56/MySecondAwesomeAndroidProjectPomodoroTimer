package com.example.di;

import com.example.data.mapper.UserDataMapper;
import com.example.domain.repository.UserRepository;
import com.example.network.PomodoroApi;
import com.example.network.mapper.NetworkErrorMapper;
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
public final class DataModule_ProvideUserRepositoryFactory implements Factory<UserRepository> {
  private final DataModule module;

  private final Provider<PomodoroApi> pomodoroApiProvider;

  private final Provider<UserDataMapper> userDataMapperProvider;

  private final Provider<NetworkErrorMapper> networkErrorMapperProvider;

  private DataModule_ProvideUserRepositoryFactory(DataModule module,
      Provider<PomodoroApi> pomodoroApiProvider, Provider<UserDataMapper> userDataMapperProvider,
      Provider<NetworkErrorMapper> networkErrorMapperProvider) {
    this.module = module;
    this.pomodoroApiProvider = pomodoroApiProvider;
    this.userDataMapperProvider = userDataMapperProvider;
    this.networkErrorMapperProvider = networkErrorMapperProvider;
  }

  @Override
  public UserRepository get() {
    return provideUserRepository(module, pomodoroApiProvider.get(), userDataMapperProvider.get(), networkErrorMapperProvider.get());
  }

  public static DataModule_ProvideUserRepositoryFactory create(DataModule module,
      Provider<PomodoroApi> pomodoroApiProvider, Provider<UserDataMapper> userDataMapperProvider,
      Provider<NetworkErrorMapper> networkErrorMapperProvider) {
    return new DataModule_ProvideUserRepositoryFactory(module, pomodoroApiProvider, userDataMapperProvider, networkErrorMapperProvider);
  }

  public static UserRepository provideUserRepository(DataModule instance, PomodoroApi pomodoroApi,
      UserDataMapper userDataMapper, NetworkErrorMapper networkErrorMapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideUserRepository(pomodoroApi, userDataMapper, networkErrorMapper));
  }
}
