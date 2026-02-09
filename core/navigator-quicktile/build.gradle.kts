plugins {
    alias(libs.plugins.filenavigator.library)
    alias(libs.plugins.filenavigator.hilt)
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.navigator)
    implementation(projects.core.navigatorDomain)

    implementation(libs.androidx.core.ktx)

    implementation(libs.w2sv.androidutils.core)
    implementation(libs.w2sv.kotlinutils)
    implementation(libs.slimber)
}
