/*
 * Copyright lt 2023
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


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("com.google.devtools.ksp")
    kotlin("native.cocoapods")
}

group = "com.lt.ltttttttttttt"

android {
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 31

        var testIndex = "-1"
        try {
            testIndex = File(project.rootDir, "test_index.txt").readText()
        } catch (e: Exception) {
        }
        buildConfigField("int", "TEST_INDEX", testIndex)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lint {
        checkDependencies = true//开启 lint 性能优化
        abortOnError = false//忽略Lint检查
        checkReleaseBuilds = false//压制警告,打包的时候有时候会有莫名其妙的警告
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    ios()
    iosSimulatorArm64()

    js(IR) {
        browser()
    }

//    macosX64 {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }
//    macosArm64 {
//        binaries {
//            executable {
//                entryPoint = "main"
//            }
//        }
//    }
    cocoapods {
        version = "0.0.1"
        summary = "Jatpack(JetBrains) Compose views"
        homepage = "https://github.com/ltttttttttttt/ComposeViews"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "ComposeViews"
            isStatic = true
        }
//        extraSpecAttributes["resources"] =
//            "['../ComposeViews/resources/**', '../desktop_app/src/desktopMain/resources/**']"
    }
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                api(project(":ComposeViews"))
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.animation)
                api(compose.ui)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.activity:activity-compose:1.4.0")
                implementation("androidx.appcompat:appcompat:1.2.0")
                implementation("io.coil-kt:coil-compose:1.4.0")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                //desktop图片加载器
                api("com.github.ltttttttttttt:load-the-image:1.0.5")
            }
        }
        val desktopTest by getting

        val iosMain by getting
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }

//        val macosMain by creating {
//            dependsOn(commonMain)
//        }
//        val macosX64Main by getting {
//            dependsOn(macosMain)
//        }
//        val macosArm64Main by getting {
//            dependsOn(macosMain)
//        }
    }
    ksp {
        arg("packageListWithVirtualReflection", "com.lt.common_app")
    }
}

dependencies {
    add("kspCommonMainMetadata", "com.github.ltttttttttttt:VirtualReflection:1.0.6")
}