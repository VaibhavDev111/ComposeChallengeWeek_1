package com.example.androiddevchallenge.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Puppy {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("heightMale")
    @Expose
    var heightMale: String? = null

    @SerializedName("heightFemale")
    @Expose
    var heightFemale: String? = null

    @SerializedName("weightMale")
    @Expose
    var weightMale: String? = null

    @SerializedName("weightFemale")
    @Expose
    var weightFemale: String? = null

    @SerializedName("life")
    @Expose
    var life: String? = null

    var imageResource: Int = 0
}