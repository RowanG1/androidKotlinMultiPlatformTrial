package com.rlg.consumekmm

import com.rlg.play_kotlin_multi_plat.DummyTokenStorage
import com.rlg.play_kotlin_multi_plat.TokenStorage
import com.rlg.play_kotlin_multi_plat.mockEngineExp
import io.ktor.client.engine.HttpClientEngine
import org.koin.dsl.module


val androidModuleConsumer = module {
    single<TokenStorage> { DummyTokenStorage() }
    single<HttpClientEngine> { mockEngineExp }
}
