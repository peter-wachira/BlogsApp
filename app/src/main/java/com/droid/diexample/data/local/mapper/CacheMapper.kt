package com.droid.diexample.data.local.mapper

import com.droid.diexample.data.local.entity.BlogCacheEntity
import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor():
        EntityMapper<BlogCacheEntity, BlogDomain> {

    override fun mapFromEntity(entity: BlogCacheEntity): BlogDomain {
        return BlogDomain(
                id = entity.id,
                title = entity.title,
                body = entity.body,
                image = entity.image,
                category = entity.category
        )
    }

    override fun mapToEntity(domainModel: BlogDomain): BlogCacheEntity {
        return BlogCacheEntity(
                id = domainModel.id,
                title = domainModel.title,
                body = domainModel.body,
                image = domainModel.image,
                category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<BlogDomain>{
        return entities.map { mapFromEntity(it) }
    }
}

