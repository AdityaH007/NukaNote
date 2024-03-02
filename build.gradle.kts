// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    //alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    id ("androidx.navigation.safeargs") version "2.7.7" apply false
}