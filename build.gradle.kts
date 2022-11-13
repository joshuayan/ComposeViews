/*
 * Copyright lt 2022
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    repositories {
//        maven { url "https://mirrors.tencent.com/nexus/repository/maven-public/" }
//        google()
//        mavenCentral()
//        maven { url "https://maven.pkg.jetbrains.space/public/p/compose/dev" }
//    }
//    dependencies {
//        classpath "com.android.tools.build:gradle:7.1.2"
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:${VersionsKt.kotlinVersion}"
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle files
//    }
//}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}


group = "com.github.ltttttttttttt"
version = "1.0.0"

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//        maven("https://mirrors.tencent.com/nexus/repository/maven-public/")
//    }
//}

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
}