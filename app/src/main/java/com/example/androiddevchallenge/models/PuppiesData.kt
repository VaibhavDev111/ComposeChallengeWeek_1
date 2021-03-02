package com.example.androiddevchallenge.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PuppiesData {
    @SerializedName("puppies")
    @Expose
    var puppies: List<Puppy>? = null
}