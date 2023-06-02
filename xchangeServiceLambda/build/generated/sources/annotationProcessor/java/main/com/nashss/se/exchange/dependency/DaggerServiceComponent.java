package com.nashss.se.exchange.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.exchange.activity.CreateItemActivity;
import com.nashss.se.exchange.activity.GetItemActivity;
import com.nashss.se.exchange.activity.SearchTypeZipActivity;
import com.nashss.se.exchange.dynamodb.ItemDao;
import com.nashss.se.exchange.dynamodb.ItemDao_Factory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerServiceComponent {
  private DaggerServiceComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private DaoModule daoModule;

    private Builder() {
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder metricsModule(MetricsModule metricsModule) {
      Preconditions.checkNotNull(metricsModule);
      return this;
    }

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      return new ServiceComponentImpl(daoModule);
    }
  }

  private static final class ServiceComponentImpl implements ServiceComponent {
    private final ServiceComponentImpl serviceComponentImpl = this;

    private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

    private Provider<ItemDao> itemDaoProvider;

    private ServiceComponentImpl(DaoModule daoModuleParam) {

      initialize(daoModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final DaoModule daoModuleParam) {
      this.provideDynamoDBMapperProvider = DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(daoModuleParam));
      this.itemDaoProvider = DoubleCheck.provider(ItemDao_Factory.create(provideDynamoDBMapperProvider));
    }

    @Override
    public GetItemActivity providesGetItemActivity() {
      return new GetItemActivity(itemDaoProvider.get());
    }

    @Override
    public SearchTypeZipActivity providesSearchTypeZipActivity() {
      return new SearchTypeZipActivity(itemDaoProvider.get());
    }

    @Override
    public CreateItemActivity providesCreateItemActivity() {
      return new CreateItemActivity(itemDaoProvider.get());
    }
  }
}
