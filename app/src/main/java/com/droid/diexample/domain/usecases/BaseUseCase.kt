package com.droid.diexample.domain.usecases

interface BaseUseCase<out Result> {
    suspend operator fun invoke(): Result
}