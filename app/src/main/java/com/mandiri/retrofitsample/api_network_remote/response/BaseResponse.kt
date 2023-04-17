package com.mandiri.retrofitsample.api_network_remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("support")
	val support: Support? = null
)

data class Support(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class DataItem(

//	@field:SerializedName("last_name")
	val last_name: String? = "",

	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("avatar")
	val avatar: String? = "",

	@field:SerializedName("first_name")
	val firstName: String? = "",

	@field:SerializedName("email")
	val email: String? = ""
)
