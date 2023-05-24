package com.nashss.se.exchange.dependency;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
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
    "rawtypes"
})
public final class MetricsModule_ProvideCloudWatchFactory implements Factory<AmazonCloudWatch> {
  @Override
  public AmazonCloudWatch get() {
    return provideCloudWatch();
  }

  public static MetricsModule_ProvideCloudWatchFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AmazonCloudWatch provideCloudWatch() {
    return Preconditions.checkNotNullFromProvides(MetricsModule.provideCloudWatch());
  }

  private static final class InstanceHolder {
    private static final MetricsModule_ProvideCloudWatchFactory INSTANCE = new MetricsModule_ProvideCloudWatchFactory();
  }
}
