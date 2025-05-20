package com.example.di

import com.example.repository.InMemoryTaskRepository
import com.example.repository.TaskRepository
import com.example.services.task.TaskService
import com.example.services.task.impl.Mapper
import com.example.services.task.impl.TaskServiceImpl
import org.koin.dsl.module

val appModule = module {
    single<TaskRepository> { InMemoryTaskRepository() }

    single<TaskService> { TaskServiceImpl(get(), get()) }
    single<Mapper> { Mapper() }
}