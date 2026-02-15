package com.example.composition.domain.intity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Уровень
@Parcelize
enum class Level: Parcelable {

    TEST, EASY, NORMAL, HARD
 }