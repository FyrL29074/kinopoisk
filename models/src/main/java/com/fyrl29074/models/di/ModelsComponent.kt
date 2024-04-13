package com.fyrl29074.models.di

import dagger.Subcomponent

@Subcomponent
interface ModelsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ModelsComponent
    }
}