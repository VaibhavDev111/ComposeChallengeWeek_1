package com.example.androiddevchallenge

import android.content.Context
import com.example.androiddevchallenge.models.PuppiesListModel
import com.example.androiddevchallenge.models.Puppy
import com.example.androiddevchallenge.utilities.Utils

class DataProvider {

    private val puppiesImages = mutableListOf(
        R.drawable.siberian_husky,
        R.drawable.poodle_,
        R.drawable.wirehaired,
        R.drawable.french_bulldog,
        R.drawable.american_hairless_terrier,
        R.drawable.welsh_springer_spaniel,
        R.drawable.shih_tzu,
        R.drawable.havanese,
        R.drawable.irish_setter,
        R.drawable.old_english_sheepdogs,
        R.drawable.cane_corso,
        R.drawable.dogue_de_bordeaux,
        R.drawable.german_shorthaired,
        R.drawable.pembroke_welsh_corgi
    )

    fun getPuppiesList(context: Context): List<PuppiesListModel> {
        val dataList = mutableListOf<PuppiesListModel>()

        val puppiesNames = context.resources.getStringArray(R.array.puppies_names)

        for ((index, value) in puppiesImages.withIndex()) {
            dataList.add(PuppiesListModel(index, value, puppiesNames[index]))
        }
        return dataList
    }

    fun obtainPuppyDetainWithId(id: Int, context: Context): Puppy? {
        val puppy = Utils().loadJsonFromAssets(context)?.puppies?.get(id)
        puppy?.imageResource = puppiesImages[id]
        return puppy
    }


}