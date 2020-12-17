package com.droid.diexample.data.remote.mappers

import com.droid.diexample.data.remote.model.Blog
import com.droid.diexample.domain.model.BlogDomain

internal fun Blog.toDomain(): BlogDomain =
    BlogDomain(
        this.id,
        this.title,
        this.image,
        this.body,
        this.category,
    )