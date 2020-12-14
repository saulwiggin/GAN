package com.saulwiggin.gan.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName ="BreakingBad")
data class BreakingBadCharacter(
        @PrimaryKey
        @SerializedName("char_id") var char_id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("nickname") var nickname: String,
        @SerializedName("img") var img: String,
        @SerializedName("status") var status: String,
        @SerializedName("occupation") var occupation: List<String>,
        @SerializedName("appearance") var appearance: List<String>
): Parcelable {}