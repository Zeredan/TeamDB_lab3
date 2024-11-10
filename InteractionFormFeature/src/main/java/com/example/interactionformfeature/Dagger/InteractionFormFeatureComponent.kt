package com.example.interactionformfeature.Dagger

import com.example.interactionformfeature.Dagger.Modules.RetrofitModule
import com.example.interactionformfeature.Retrofit.BackendCommunicationService
import com.example.interactionformfeature.ViewModels.InteractionFormViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [RetrofitModule::class])
interface InteractionFormFeatureComponent {

    fun injectViewModel(vm: InteractionFormViewModel)
    fun resolveRetrofitApi() : BackendCommunicationService

    @Component.Builder
    interface Builder{
        fun build() : InteractionFormFeatureComponent

        @BindsInstance
        fun addUrl(@Named("url") url: String) : Builder
    }
}