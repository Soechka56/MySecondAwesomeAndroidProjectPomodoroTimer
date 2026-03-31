package com.example.di;

import com.example.domain.SignInUseCase;
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
public final class DomainModule_ProvideSignInUseCaseFactory implements Factory<SignInUseCase> {
  private final DomainModule module;

  private final Provider<UserRepository> userRepositoryProvider;

  private DomainModule_ProvideSignInUseCaseFactory(DomainModule module,
      Provider<UserRepository> userRepositoryProvider) {
    this.module = module;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SignInUseCase get() {
    return provideSignInUseCase(module, userRepositoryProvider.get());
  }

  public static DomainModule_ProvideSignInUseCaseFactory create(DomainModule module,
      Provider<UserRepository> userRepositoryProvider) {
    return new DomainModule_ProvideSignInUseCaseFactory(module, userRepositoryProvider);
  }

  public static SignInUseCase provideSignInUseCase(DomainModule instance,
      UserRepository userRepository) {
    return Preconditions.checkNotNullFromProvides(instance.provideSignInUseCase(userRepository));
  }
}
