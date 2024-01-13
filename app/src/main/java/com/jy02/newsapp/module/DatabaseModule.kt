package com.jy02.newsapp.module

import com.jy02.newsapp.data.local.DatabaseNewsRepository
import com.jy02.newsapp.data.local.DatabaseNewsRepositoryImpl
import com.jy02.newsapp.data.models.RealmArticle
import com.jy02.newsapp.data.models.RealmSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                RealmArticle::class, RealmSource::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun providesDatabaseNewsRepository(realm: Realm): DatabaseNewsRepository {
        return DatabaseNewsRepositoryImpl(realm)
    }
}