package com.lt.compose_views.util


/**
 * 获取当前时间戳(ms)
 */
actual fun _currentTimeMillis(): Long = (Date().timeIntervalSince1970 * 1000).toLong