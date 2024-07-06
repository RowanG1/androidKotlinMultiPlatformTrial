package com.rlg.consumekmm

import com.rlg.play_kotlin_multi_plat.data.networking.interfaces.DummyTokenStorage
import com.rlg.play_kotlin_multi_plat.data.networking.interfaces.TokenStorage
import com.rlg.play_kotlin_multi_plat.data.networking.mockEngineExp
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module


val androidModuleConsumer = module {
    single<TokenStorage> { DummyTokenStorage() }
    single<HttpClientEngine> { mockEngineExp }
}
