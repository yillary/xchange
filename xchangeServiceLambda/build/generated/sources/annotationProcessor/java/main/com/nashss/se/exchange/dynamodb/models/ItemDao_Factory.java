package com.nashss.se.exchange.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ItemDao_Factory implements Factory<ItemDao> {
  private final Provider<DynamoDBMapper> dynamoDBMapperProvider;

  public ItemDao_Factory(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    this.dynamoDBMapperProvider = dynamoDBMapperProvider;
  }

  @Override
  public ItemDao get() {
    return newInstance(dynamoDBMapperProvider.get());
  }

  public static ItemDao_Factory create(Provider<DynamoDBMapper> dynamoDBMapperProvider) {
    return new ItemDao_Factory(dynamoDBMapperProvider);
  }

  public static ItemDao newInstance(DynamoDBMapper dynamoDBMapper) {
    return new ItemDao(dynamoDBMapper);
  }
}
