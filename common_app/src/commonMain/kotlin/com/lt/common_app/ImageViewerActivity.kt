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

package com.lt.common_app

import M
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.lt.common_app.base.BaseComposeActivity
import com.lt.common_app.base.click
import com.lt.compose_views.zoom.ImageViewer
import com.lt.compose_views.zoom.ZoomLayout
import resourcePainter

/**
 * creator: lt  2022/7/16  lt.dygzs@qq.com
 * effect :
 * warning:
 */
class ImageViewerActivity : BaseComposeActivity() {
    @Composable
    override fun ComposeContent() {
        Column(M.fillMaxSize()) {
            Box(M.weight(1f)) {
                ImageViewer(
                    resourcePainter("top_bar_bk"),
                    M.fillMaxSize().background(Color.Black),
                    userCanRotation = true,
                )
                Image(
                    Icons.Filled.Close,
                    "close",
                    M.padding(10.dp)
                        .click { mFinish() }
                        .padding(10.dp),
                    colorFilter = ColorFilter.tint(Color.White),
                )
            }
            ZoomLayout(M.weight(1f).fillMaxWidth()) {
                Column {
                    Row {
                        repeat(10) {
                            Text(it.toString(), M.size(50.dp))
                        }
                    }
                    repeat(20) {
                        Text(it.toString(), M.size(50.dp))
                    }
                }
            }
        }
    }
}