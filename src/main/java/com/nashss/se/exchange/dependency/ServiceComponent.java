package com.nashss.se.exchange.dependency;

import com.nashss.se.exchange.dynamodb.models.ItemDao;
import dagger.Component;

import javax.inject.Singleton;


/**
 * Dagger component for providing dependency injection in the exchange service.
 */
@Singleton
@Component(modules = DaoModule.class)
public interface ServiceComponent {

    /**
     * Provides the relevantActivity
     * @return SearchItemsActivity
     */
//    SearchItemsActivity providesSearchActivity();
//TODO: ^^ UNCOMMENT OUT THIS METHOD SO IT WORKS WHEN ACTIVITY IS MADE.
}
