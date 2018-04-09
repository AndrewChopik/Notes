package com.andrew.chopik.notepadkotlin.dagger

import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.andrew.chopik.notepadkotlin.activity.MainListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Andrew on 02.03.2018.
 */
@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainListActivity: MainListActivity)

    @Component.Builder
    interface Builder {

        fun build(): ActivityComponent

        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder

        @BindsInstance
        fun application(application: Application): Builder
    }
}