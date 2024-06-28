package com.example.compose.jetchat.widget.composables

import android.graphics.drawable.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.IconImageProvider
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.Action
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.compose.jetchat.NavActivity
import com.example.compose.jetchat.R
import com.example.compose.jetchat.conversation.Message
import com.example.compose.jetchat.data.unreadMessages
import com.example.compose.jetchat.widget.MyAppWidget.Companion.ICON_SQUARE

@Composable
fun MessagesWidget() {
    val size = LocalSize.current
    if (size.width <= ICON_SQUARE.width) {
        SmallIcon()
    } else {
        LargeMessages()
    }
}

@Composable
fun SmallIcon() {
//    IconImageProvider(Icon(R.drawable.ic_jetchat))
}

@Composable
fun LargeMessages() {
    Scaffold(titleBar = {
        TitleBar(
            startIcon = ImageProvider(R.drawable.ic_jetchat),
            iconColor = null,
            title = LocalContext.current.getString(R.string.app_name_unreads)
        )
    }) {
        val unreadMessage = unreadMessages
        LazyColumn(modifier = GlanceModifier.fillMaxWidth()) {
            unreadMessage.forEach {
                item {
                    Column(modifier = GlanceModifier.fillMaxWidth()) {
                        MessageItem(it)
                        Spacer(modifier = GlanceModifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Column(modifier = GlanceModifier.clickable(actionStartActivity<NavActivity>()).fillMaxWidth()) {
        Text(
            text = message.author,
            style = TextStyle(fontWeight = FontWeight.Bold, color = ColorProvider(Color.White))
        )
        Text(
            text = message.content,
            style = TextStyle(fontWeight = FontWeight.Normal, color = ColorProvider(Color.White))
        )
    }
}