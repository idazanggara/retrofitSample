package com.mandiri.retrofitsample.api_network_remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.squareup.moshi.Json

@Parcelize
data class BaseResponseMoshi(

	@Json(name="data")
	val data: Data? = null,

) : Parcelable


@Parcelize
data class Data(

//	@Json(name="last_name")
	val last_name: String? = "",

	@Json(name="id")
	val id: Int? = 0,

	@Json(name="avatar")
	val avatar: String? = "",

//	@Json(name="first_name")
	val first_name: String? = "",

	@Json(name="email")
	val email: String? = ""
) : Parcelable

// di user ID gw coba contohin gimana kalau si propertynya json berbeda dengan poperty class post / class kita
// jadi kalau gw terima json userId, jadi di mapping ke myUserId
// kita sudah bikin responsenya jadi kita bisa mengcover Jsonenya

// nah sekarang ke JsonPlaceholderApi
// kita tinggal bikin requestnya
