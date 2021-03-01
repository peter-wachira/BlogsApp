package com.droid.diexample.presenter.mappers

import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.presenter.models.BlogPresentation

internal fun BlogDomain.toPresenter():BlogPresentation =
    BlogPresentation(
        this.id,
        this.title,
        this.image,
        this.body,
        this.category
    )