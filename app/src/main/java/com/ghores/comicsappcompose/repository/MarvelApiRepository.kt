package com.ghores.comicsappcompose.repository

import com.ghores.comicsappcompose.model.CharactersApiResponse
import com.ghores.comicsappcompose.model.api.MarvelApi
import com.ghores.comicsappcompose.model.api.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelApiRepository(private val api: MarvelApi) {
    val characters = MutableStateFlow<NetworkResult<CharactersApiResponse>>(NetworkResult.Initial())

    fun query(query: String) {
        characters.value = NetworkResult.Loading()
        api.getCharacters(query)
            .enqueue(object : Callback<CharactersApiResponse> {
                override fun onResponse(
                    call: Call<CharactersApiResponse>,
                    response: Response<CharactersApiResponse>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            characters.value = NetworkResult.Success(it)
                        }
                    else {
                        characters.value = NetworkResult.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<CharactersApiResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        characters.value = NetworkResult.Error(it)
                    }
                }

            })
    }
}