package com.nashss.se.exchange.dependency;

import com.nashss.se.exchange.activity.GetItemActivity;
import com.nashss.se.exchange.activity.SearchTypeZipActivity;
import dagger.Component;

import javax.inject.Singleton;


/**
 * Dagger component for providing dependency injection in the exchange service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevantActivity
     * @return SearchItemsActivity
     */
    GetItemActivity providesGetItemActivity();

    /**
     * Provides the relevantActivity
     * @return SearchItemsActivity
     */
    SearchTypeZipActivity providesSearchTypeZipActivity();

}
