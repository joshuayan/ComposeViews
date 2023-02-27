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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lt.common_app.base.BaseComposeActivity
import com.lt.compose_views.menu_fab.MenuFabItem
import com.lt.compose_views.menu_fab.MenuFloatingActionButton
import com.lt.compose_views.other.FpsText
import resourcePainter


// Author: Vast Gui
// Email: guihy2019@gmail.com
// Date: 2022/7/23 15:54
// Description: 
// Documentation:

class MenuFabActivity : BaseComposeActivity() {

    private val menuItems = ArrayList<MenuFabItem>().apply {
        add(
            MenuFabItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = resourcePainter("ic_empty_delete"),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                "删除"
            )
        )
        add(
            MenuFabItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = resourcePainter("ic_empty_update"),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                "更新"
            )
        )
    }

    override fun getTitleText() = "带菜单的Fab"

    @Composable
    override fun ComposeContent() {
        FpsText(modifier = M)
        Box(M.fillMaxSize()) {
            MenuFloatingActionButton(
                srcIcon = Icons.Filled.Add,
                items = menuItems,
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 30.dp),
            ) {
                "点击了${it.label}".showToast()
            }
        }
    }
}