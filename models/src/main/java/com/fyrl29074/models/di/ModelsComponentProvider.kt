package com.fyrl29074.models.di

import dagger.Module

@Module
interface ModelsComponentProvider {

    fun provideModelsComponent(): ModelsComponent
}