package com.nashss.se.exchange.metrics;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class MetricsPublisher_Factory implements Factory<MetricsPublisher> {
  private final Provider<AmazonCloudWatch> cloudWatchProvider;

  public MetricsPublisher_Factory(Provider<AmazonCloudWatch> cloudWatchProvider) {
    this.cloudWatchProvider = cloudWatchProvider;
  }

  @Override
  public MetricsPublisher get() {
    return newInstance(cloudWatchProvider.get());
  }

  public static MetricsPublisher_Factory create(Provider<AmazonCloudWatch> cloudWatchProvider) {
    return new MetricsPublisher_Factory(cloudWatchProvider);
  }

  public static MetricsPublisher newInstance(AmazonCloudWatch cloudWatch) {
    return new MetricsPublisher(cloudWatch);
  }
}
