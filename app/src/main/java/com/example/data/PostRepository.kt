package com.example.data

import kotlinx.coroutines.flow.Flow

class PostRepository(private val postDao: PostDao) {
    val allPosts: Flow<List<PostEntity>> = postDao.getAllPosts()

    suspend fun insert(post: PostEntity) {
        postDao.insertPost(post)
    }

    suspend fun deleteById(id: Int) {
        postDao.deletePostById(id)
    }

    suspend fun deleteAll() {
        postDao.deleteAllPosts()
    }
}
