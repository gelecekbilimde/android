package org.gelecekbilimde.data.remote

import org.gelecekbilimde.BuildConfig
import org.gelecekbilimde.data.model.ArticleResult
import org.gelecekbilimde.data.model.CategoryResult
import org.gelecekbilimde.data.model.VideoResult
import org.gelecekbilimde.util.*
import retrofit2.Response
import retrofit2.http.*

/**
 *
 * @author ferhatozcelik
 * @since 2023-03-30
 */

interface AppApi {


    @GET("/wp-json/wp/v2/posts?orderby=date&order=desc&per_page=${PER_PAGE}")
    suspend fun getArticle(@Query("page") page: Int, @Query("categories") categories: String? = null,
                           @Query("search") search: String? = null): Response<List<ArticleResult>>

    @GET("/wp-json/wp/v2/categories?per_page=50")
    suspend fun getCategories(): Response<List<CategoryResult>>

    @GET("${BuildConfig.YOUTUBE_API}/v3/playlistItems?part=snippet&order=date&maxResults=${PER_PAGE}")
    suspend fun getVideos(@Query("key") key: String, @Query("playlistId") playlistId: String,
                          @Query("pageToken") pageToken: String? = null): Response<VideoResult>

}