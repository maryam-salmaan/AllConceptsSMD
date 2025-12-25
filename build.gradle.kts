// Top-level build file where you can add configuration options common to all sub-projects/modules.
// D:/finals/build.gradle.kts

plugins {
    alias(libs.plugins.android.application) apply false
    // Use the modern ID syntax here. Version 4.4.2 is the current stable choice.
    id("com.google.gms.google-services") version "4.4.2" apply false
}

// Remove the entire 'buildscript' block entirely. It is not needed for this plugin.
