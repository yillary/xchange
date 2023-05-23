package com.nashss.se.exchange.dependency;

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
//    GetItemsActivity providesGetItemsActivity();
//TODO: ^^ UNCOMMENT OUT THIS METHOD SO IT WORKS WHEN ACTIVITY IS MADE.
}
