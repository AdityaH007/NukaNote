package com.example.nukanote.com.example.nukanote.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize


@Entity(tableName = "notes")
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)val id:Int=0,
    val title:String?,
    val content:String?,
    val date:Long
):Parcelable
