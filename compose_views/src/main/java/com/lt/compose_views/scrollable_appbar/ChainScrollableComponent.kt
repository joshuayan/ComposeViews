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

package com.lt.compose_views.scrollable_appbar

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.lt.compose_views.util.ComposePosition
import kotlin.math.roundToInt

/**
 * creator: lt  2022/9/28  lt.dygzs@qq.com
 * effect : 链式(联动)滚动组件
 * warning:
 * @param minScrollPosition 最小滚动位置(距离指定方向的顶点)
 * @param maxScrollPosition 最大滚动位置(距离指定方向的顶点)
 * @param chainContent 链式(联动)滚动的compose组件,scrollOffset: 滚动位置(位于最小和最大之间)
 * @param modifier 修饰
 * @param composePosition 设置bar布局所在的位置,并且间接指定了滑动方向
 * @param isSupportCanNotScrollCompose 是否需要支持无法滚动的组件,为true的话内部会套一层可滚动组件
 * @param content compose内容区域
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChainScrollableComponent(
    minScrollPosition: Dp,
    maxScrollPosition: Dp,
    chainContent: @Composable (scrollPosition: Float) -> Unit,
    modifier: Modifier = Modifier,
    composePosition: ComposePosition = ComposePosition.Top,
    isSupportCanNotScrollCompose: Boolean = false,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val minPx = remember(key1 = minScrollPosition, key2 = density) {
        density.run { minScrollPosition.roundToPx() }
    }
    val maxPx = remember(key1 = maxScrollPosition, key2 = density) {
        density.run { maxScrollPosition.roundToPx() }
    }
    var scrollPosition by remember(key1 = minPx, key2 = maxPx) {
        mutableStateOf(maxPx.toFloat())
    }
    val orientationIsHorizontal = remember(composePosition) {
        composePosition.isHorizontal()
    }
    val nestedScrollState = remember(
        key1 = composePosition,
        key2 = minScrollPosition,
        key3 = maxScrollPosition
    ) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val offset = if (orientationIsHorizontal) available.x else available.y
                val position = scrollPosition
                if (offset > 0 && position < maxPx) {
                    //如果可以向max位置滑动
                    val diff = minOf(maxPx - position, offset)
                    scrollPosition = position + diff
                    return Offset(
                        if (orientationIsHorizontal) diff else 0f,
                        if (orientationIsHorizontal) 0f else diff
                    )
                } else if (offset < 0 && position > minPx) {
                    //如果可以向min位置滑动
                    val diff = maxOf(minPx - position, offset)
                    scrollPosition = position + diff
                    return Offset(
                        if (orientationIsHorizontal) diff else 0f,
                        if (orientationIsHorizontal) 0f else diff
                    )
                }
                return Offset.Zero
            }
        }
    }
    Layout(
        content = {
            if (isSupportCanNotScrollCompose) {
                Box(
                    if (orientationIsHorizontal)
                        Modifier.horizontalScroll(rememberScrollState())
                    else
                        Modifier.verticalScroll(rememberScrollState())
                ) {
                    content()
                }
            } else {
                content()
            }
            // TODO by lt 2022/9/28 22:39 顶部无法带动content
            Box(
                if (orientationIsHorizontal)
                    Modifier.horizontalScroll(rememberScrollState())
                else
                    Modifier.verticalScroll(rememberScrollState())
            ) {
                chainContent(scrollPosition)
            }
        },
        modifier = modifier
            .nestedScroll(nestedScrollState)
            .clipScrollableContainer(composePosition.orientation)
    ) { measurableList, constraints ->
        val mConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val contentPlaceable = measurableList[0].measure(mConstraints)
        val chainContentPlaceable = measurableList[1].measure(
            if (orientationIsHorizontal)
                mConstraints.copy(maxWidth = maxPx)
            else
                mConstraints.copy(maxHeight = maxPx)
        )

        layout(contentPlaceable.width, contentPlaceable.height) {
            val offset = scrollPosition.roundToInt()
            when (composePosition) {
                ComposePosition.Start -> {
                    contentPlaceable.placeRelative(offset, 0)
                    chainContentPlaceable.placeRelative(
                        (-chainContentPlaceable.width) + offset,
                        0
                    )
                }
                ComposePosition.End -> {
                    contentPlaceable.placeRelative(offset, 0)
                    chainContentPlaceable.placeRelative(
                        contentPlaceable.width + offset,
                        0
                    )
                }
                ComposePosition.Top -> {
                    contentPlaceable.placeRelative(0, offset)
                    chainContentPlaceable.placeRelative(
                        0,
                        (-chainContentPlaceable.height) + offset
                    )
                }
                ComposePosition.Bottom -> {
                    contentPlaceable.placeRelative(0, offset)
                    chainContentPlaceable.placeRelative(
                        0,
                        contentPlaceable.height + offset
                    )
                }
            }
        }
    }
}