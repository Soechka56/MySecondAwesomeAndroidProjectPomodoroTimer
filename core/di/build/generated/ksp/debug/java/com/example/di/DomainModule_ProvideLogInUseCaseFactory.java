package com.example.di;

import com.example.domain.LogInUseCase;
import com.example.domain.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DomainModule_ProvideLogInUseCaseFactory implements Factory<LogInUseCase> {
  private final DomainModule module;

  private final Provider<UserRepository> userRepositoryProvider;

  private DomainModule_ProvideLogInUseCaseFactory(DomainModule module,
      Provider<UserRepository> userRepositoryProvider) {
    this.module = module;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public LogInUseCase get() {
    return provideLogInUseCase(module, userRepositoryProvider.get());
  }

  public static DomainModule_ProvideLogInUseCaseFactory create(DomainModule module,
      Provider<UserRepository> userRepositoryProvider) {
    return new DomainModule_ProvideLogInUseCaseFactory(module, userRepositoryProvider);
  }

  public static LogInUseCase provideLogInUseCase(DomainModule instance,
      UserRepository userRepository) {
    return Preconditions.checkNotNullFromProvides(instance.provideLogInUseCase(userRepository));
  }
}
