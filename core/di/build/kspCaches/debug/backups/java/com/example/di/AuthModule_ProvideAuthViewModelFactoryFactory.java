package com.example.di;

import com.example.domain.LogInUseCase;
import com.example.domain.SignInUseCase;
import com.example.impl.AuthViewModel;
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
public final class AuthModule_ProvideAuthViewModelFactoryFactory implements Factory<AuthViewModel.Factory> {
  private final AuthModule module;

  private final Provider<LogInUseCase> logInUseCaseProvider;

  private final Provider<SignInUseCase> signInUseCaseProvider;

  private AuthModule_ProvideAuthViewModelFactoryFactory(AuthModule module,
      Provider<LogInUseCase> logInUseCaseProvider, Provider<SignInUseCase> signInUseCaseProvider) {
    this.module = module;
    this.logInUseCaseProvider = logInUseCaseProvider;
    this.signInUseCaseProvider = signInUseCaseProvider;
  }

  @Override
  public AuthViewModel.Factory get() {
    return provideAuthViewModelFactory(module, logInUseCaseProvider.get(), signInUseCaseProvider.get());
  }

  public static AuthModule_ProvideAuthViewModelFactoryFactory create(AuthModule module,
      Provider<LogInUseCase> logInUseCaseProvider, Provider<SignInUseCase> signInUseCaseProvider) {
    return new AuthModule_ProvideAuthViewModelFactoryFactory(module, logInUseCaseProvider, signInUseCaseProvider);
  }

  public static AuthViewModel.Factory provideAuthViewModelFactory(AuthModule instance,
      LogInUseCase logInUseCase, SignInUseCase signInUseCase) {
    return Preconditions.checkNotNullFromProvides(instance.provideAuthViewModelFactory(logInUseCase, signInUseCase));
  }
}
