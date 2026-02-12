plugins {
    alias(libs.plugins.filenavigator.library)
    alias(libs.plugins.filenavigator.hilt)
    alias(libs.plugins.kotlin.parcelize)
}

android.defaultConfig.consumerProguardFiles("consumer-proguard-rules.pro")

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(projects.core.datastoreProto)
    implementation(libs.androidx.core.ktx)
    implementation(libs.w2sv.kotlinutils)
    implementation(libs.w2sv.datastoreutils.preferences)
    implementation(libs.w2sv.datastoreutils.datastoreflow)
    implementation(libs.w2sv.androidutils.core)
    implementation(libs.slimber)

    testImplementation(projects.core.test)
}
