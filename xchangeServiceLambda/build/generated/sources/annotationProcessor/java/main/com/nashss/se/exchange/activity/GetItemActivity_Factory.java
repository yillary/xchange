package com.nashss.se.exchange.activity;

import com.nashss.se.exchange.ItemDao;
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
public final class GetItemActivity_Factory implements Factory<GetItemActivity> {
  private final Provider<ItemDao> itemDaoProvider;

  public GetItemActivity_Factory(Provider<ItemDao> itemDaoProvider) {
    this.itemDaoProvider = itemDaoProvider;
  }

  @Override
  public GetItemActivity get() {
    return newInstance(itemDaoProvider.get());
  }

  public static GetItemActivity_Factory create(Provider<ItemDao> itemDaoProvider) {
    return new GetItemActivity_Factory(itemDaoProvider);
  }

  public static GetItemActivity newInstance(ItemDao itemDao) {
    return new GetItemActivity(itemDao);
  }
}
