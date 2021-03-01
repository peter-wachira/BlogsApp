package com.droid.diexample.presenter.mappers

import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.presenter.models.BlogPresentation

internal fun BlogPresentation.toDomain():BlogDomain =
    BlogDomain(
        this.id,
        this.title,
        this.image,
        this.body,
        this.category
    )