package com.example.one.configuration.application;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.HOURS;


/**4
 * Created by Dimitrios Stefos on 5/18/2018.
 */

@Profile("redis")
@Configuration
public class ApplicationCacheConfiguration {

    @Component
    public static class CachingSetup implements JCacheManagerCustomizer {

        @Override
        public void customize(javax.cache.CacheManager cacheManager) {
            cacheManager.createCache("actors", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(HOURS, 10)))
                    .setStoreByValue(false)
                    .setStatisticsEnabled(true));

            cacheManager.createCache("cities", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(HOURS, 10)))
                    .setStoreByValue(false)
                    .setStatisticsEnabled(true));

            cacheManager.createCache("cities.byCityId", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(HOURS, 10)))
                    .setStoreByValue(false)
                    .setStatisticsEnabled(true));
        }

    }
}
