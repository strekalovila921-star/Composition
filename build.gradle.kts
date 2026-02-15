// Твой корневой билд-файл
plugins {
    // Здесь ОБЯЗАТЕЛЬНО должны быть версии (version), так как нет Version Catalog
    id("com.android.application") version "8.13.2" apply false
    id("com.android.library") version "8.13.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.0" apply false
}