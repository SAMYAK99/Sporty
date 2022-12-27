package com.projects.trending.sporty.data


import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: remoteDataSource,
    localDataSource: localDataSource
) {


    val remote = remoteDataSource
    val local = localDataSource
}