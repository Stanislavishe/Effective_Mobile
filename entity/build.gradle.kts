plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.google.devtools.ksp)
}

dependencies {
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
}