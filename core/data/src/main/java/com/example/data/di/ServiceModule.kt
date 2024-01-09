package com.example.data.di
import com.example.network.NetworkType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Provides
    @Singleton
    fun provideKakaoSearchService(
        @Named(NetworkType.NewsClient) ktorClient: HttpClient
    ) = retrofit.create<KakaoSearchService>()
}