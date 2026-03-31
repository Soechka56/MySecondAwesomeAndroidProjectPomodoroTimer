package com.example.di;

import com.example.data.mapper.UserDataMapper;
import com.example.domain.LogInUseCase;
import com.example.domain.SignInUseCase;
import com.example.domain.repository.UserRepository;
import com.example.impl.AuthViewModel;
import com.example.network.PomodoroApi;
import com.example.network.mapper.NetworkErrorMapper;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private NetworkModule networkModule;

    private DataModule dataModule;

    private DomainModule domainModule;

    private AuthModule authModule;

    private Builder() {
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder dataModule(DataModule dataModule) {
      this.dataModule = Preconditions.checkNotNull(dataModule);
      return this;
    }

    public Builder domainModule(DomainModule domainModule) {
      this.domainModule = Preconditions.checkNotNull(domainModule);
      return this;
    }

    public Builder authModule(AuthModule authModule) {
      this.authModule = Preconditions.checkNotNull(authModule);
      return this;
    }

    public AppComponent build() {
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      if (dataModule == null) {
        this.dataModule = new DataModule();
      }
      if (domainModule == null) {
        this.domainModule = new DomainModule();
      }
      if (authModule == null) {
        this.authModule = new AuthModule();
      }
      return new AppComponentImpl(networkModule, dataModule, domainModule, authModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AuthModule authModule;

    private final DomainModule domainModule;

    private final AppComponentImpl appComponentImpl = this;

    Provider<Gson> provideGsonProvider;

    Provider<Retrofit> provideRetrofitProvider;

    Provider<PomodoroApi> providePomodoroApiProvider;

    Provider<UserDataMapper> provideUserDataMapperProvider;

    Provider<NetworkErrorMapper> provideNetworkErrorMapperProvider;

    Provider<UserRepository> provideUserRepositoryProvider;

    AppComponentImpl(NetworkModule networkModuleParam, DataModule dataModuleParam,
        DomainModule domainModuleParam, AuthModule authModuleParam) {
      this.authModule = authModuleParam;
      this.domainModule = domainModuleParam;
      initialize(networkModuleParam, dataModuleParam, domainModuleParam, authModuleParam);

    }

    LogInUseCase logInUseCase() {
      return DomainModule_ProvideLogInUseCaseFactory.provideLogInUseCase(domainModule, provideUserRepositoryProvider.get());
    }

    SignInUseCase signInUseCase() {
      return DomainModule_ProvideSignInUseCaseFactory.provideSignInUseCase(domainModule, provideUserRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final NetworkModule networkModuleParam,
        final DataModule dataModuleParam, final DomainModule domainModuleParam,
        final AuthModule authModuleParam) {
      this.provideGsonProvider = DoubleCheck.provider(NetworkModule_ProvideGsonFactory.create(networkModuleParam));
      this.provideRetrofitProvider = DoubleCheck.provider(NetworkModule_ProvideRetrofitFactory.create(networkModuleParam, provideGsonProvider));
      this.providePomodoroApiProvider = DoubleCheck.provider(NetworkModule_ProvidePomodoroApiFactory.create(networkModuleParam, provideRetrofitProvider));
      this.provideUserDataMapperProvider = DoubleCheck.provider(DataModule_ProvideUserDataMapperFactory.create(dataModuleParam));
      this.provideNetworkErrorMapperProvider = DoubleCheck.provider(NetworkModule_ProvideNetworkErrorMapperFactory.create(networkModuleParam, provideGsonProvider));
      this.provideUserRepositoryProvider = DoubleCheck.provider(DataModule_ProvideUserRepositoryFactory.create(dataModuleParam, providePomodoroApiProvider, provideUserDataMapperProvider, provideNetworkErrorMapperProvider));
    }

    @Override
    public AuthViewModel.Factory authViewModelFactory() {
      return AuthModule_ProvideAuthViewModelFactoryFactory.provideAuthViewModelFactory(authModule, logInUseCase(), signInUseCase());
    }
  }
}
