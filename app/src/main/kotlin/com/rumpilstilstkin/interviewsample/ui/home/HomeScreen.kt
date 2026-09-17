package com.rumpilstilstkin.interviewsample.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val paper = Color(0xFFFAF7F0)
    val ink = Color(0xFF302E28)
    val muted = Color(0xFF78766C)
    val orange = Color(0xFFB64B2C)
    val line = Color(0xFFE6E1D7)
    val blue = Color(0xFFDCE8EF)
    val pink = Color(0xFFF2DCD8)
    val green = Color(0xFFE4E9D7)
    val yellow = Color(0xFFF4E7BE)
    var query by rememberSaveable { mutableStateOf("") }
    var showAllTasks by rememberSaveable { mutableStateOf(false) }
    var showAllDays by rememberSaveable { mutableStateOf(false) }
    var addedDays by rememberSaveable { mutableStateOf(listOf<String>()) }
    var savedTasks by rememberSaveable { mutableStateOf(listOf<String>()) }
    var destination by rememberSaveable { mutableStateOf("Home") }
    var openedTask by rememberSaveable { mutableStateOf<String?>(null) }
    val tasks = remember {
        listOf("Morning rituals" to "A softer start", "Little joys" to "Make room for happy",
            "Step outside" to "Find a new perspective", "Quiet moments" to "Come back to yourself")
    }
    val holidays = remember {
        listOf(Triple("SEP", "21", "International Day of Peace"),
            Triple("SEP", "22", "Autumn Equinox"),
            Triple("OCT", "01", "International Coffee Day"),
            Triple("OCT", "10", "World Mental Health Day"))
    }
    val popular = remember {
        listOf("Take the scenic route" to "15 MIN · OUTDOORS",
            "A moment of gratitude" to "5 MIN · MINDFULNESS")
    }

    // Small, dependency-free line icons share one 24 × 24 coordinate system.
    val icon: @Composable (String, Color, Modifier) -> Unit = { name, tint, iconModifier ->
        Canvas(iconModifier) {
            scale(size.width / 24f, size.height / 24f, pivot = Offset.Zero) {
                val stroke = Stroke(width = 1.6f, cap = StrokeCap.Round)
                val path = Path()
                when (name) {
                    "search" -> {
                        drawCircle(tint, 6.5f, Offset(10.5f, 10.5f), style = stroke)
                        drawLine(tint, Offset(15.5f, 15.5f), Offset(21f, 21f), 1.6f, StrokeCap.Round)
                    }
                    "plus", "check" -> {
                        if (name == "plus") {
                            drawLine(tint, Offset(6f, 12f), Offset(18f, 12f), 1.6f, StrokeCap.Round)
                            drawLine(tint, Offset(12f, 6f), Offset(12f, 18f), 1.6f, StrokeCap.Round)
                        } else {
                            path.moveTo(5f, 12f); path.lineTo(10f, 17f); path.lineTo(19f, 7f)
                            drawPath(path, tint, style = stroke)
                        }
                    }
                    "arrow" -> {
                        path.moveTo(6f, 18f); path.lineTo(18f, 6f)
                        path.moveTo(6f, 6f); path.lineTo(18f, 6f); path.lineTo(18f, 18f)
                        drawPath(path, tint, style = stroke)
                    }
                    "Home" -> {
                        path.moveTo(3f, 11f); path.lineTo(12f, 3f); path.lineTo(21f, 11f)
                        path.moveTo(5f, 10f); path.lineTo(5f, 21f); path.lineTo(10f, 21f)
                        path.lineTo(10f, 14f); path.lineTo(14f, 14f); path.lineTo(14f, 21f)
                        path.lineTo(19f, 21f); path.lineTo(19f, 10f)
                        drawPath(path, tint, style = stroke)
                    }
                    "Street" -> {
                        path.moveTo(3f, 21f); path.lineTo(8f, 3f)
                        path.moveTo(21f, 21f); path.lineTo(16f, 3f)
                        drawPath(path, tint, style = stroke)
                        repeat(3) { drawLine(tint, Offset(12f, 4f + it * 7), Offset(12f, 7f + it * 7), 1.6f, StrokeCap.Round) }
                    }
                    "Forest" -> {
                        path.moveTo(12f, 2f); path.lineTo(5f, 12f); path.lineTo(8f, 12f)
                        path.lineTo(3f, 18f); path.lineTo(21f, 18f); path.lineTo(16f, 12f)
                        path.lineTo(19f, 12f); path.close()
                        drawPath(path, tint, style = stroke)
                        drawLine(tint, Offset(12f, 18f), Offset(12f, 22f), 1.6f, StrokeCap.Round)
                    }
                    "Cave" -> {
                        path.moveTo(2f, 21f); path.lineTo(7f, 7f); path.lineTo(14f, 3f)
                        path.lineTo(22f, 21f); path.lineTo(15f, 21f)
                        path.lineTo(12f, 13f); path.lineTo(9f, 21f); path.close()
                        drawPath(path, tint, style = stroke)
                    }
                    else -> {
                        drawCircle(tint, 4f, Offset(12f, 12f), style = stroke)
                        repeat(8) { ray ->
                            rotate(ray * 45f, Offset(12f, 12f)) {
                                drawLine(tint, Offset(12f, 2f), Offset(12f, 5f), 1.6f, StrokeCap.Round)
                            }
                        }
                    }
                }
            }
        }
    }

    MaterialTheme(colorScheme = lightColorScheme(
        primary = orange, background = paper, surface = paper,
        onBackground = ink, onSurface = ink, onSurfaceVariant = muted,
    )) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = paper,
            topBar = {
                Row(
                    Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 24.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Home", fontSize = 25.sp, fontWeight = FontWeight.SemiBold,
                        letterSpacing = (-1).sp, modifier = Modifier.weight(1f))
                    icon("sun", orange, Modifier.size(18.dp))
                    Text("  YOUR DAILY SPACE", color = muted, fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold, letterSpacing = 1.3.sp)
                    Spacer(Modifier.width(14.dp))
                    Box(Modifier.size(38.dp).background(Color(0xFFEED8C9), CircleShape), contentAlignment = Alignment.Center) {
                        Text("J", fontFamily = FontFamily.Serif, fontSize = 20.sp, color = orange)
                    }
                }
            },
            bottomBar = {
                Column(Modifier.background(paper).navigationBarsPadding()) {
                    Box(Modifier.fillMaxWidth().height(1.dp).background(line))
                    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)) {
                        listOf("Home", "Street", "Forest", "Cave").forEach { label ->
                            val active = destination == label
                            val tint by animateColorAsState(if (active) orange else muted, label = "Navigation tint")
                            Column(
                                Modifier.weight(1f).clip(RoundedCornerShape(18.dp))
                                    .semantics { selected = active }
                                    .clickable(role = Role.Tab) { destination = label }
                                    .padding(vertical = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                Box(Modifier.size(48.dp, 29.dp)
                                    .background(if (active) Color(0xFFF3E3D8) else Color.Transparent, RoundedCornerShape(12.dp)),
                                    contentAlignment = Alignment.Center) {
                                    icon(label, tint, Modifier.size(22.dp))
                                }
                                Text(label, color = tint, fontSize = 11.sp, fontWeight = if (active) FontWeight.Bold else FontWeight.Medium)
                            }
                        }
                    }
                }
            },
        ) { insets ->
            LazyColumn(
                Modifier.fillMaxSize().padding(insets),
                contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp),
            ) {
                item {
                    Column(Modifier.padding(top = 16.dp, bottom = 23.dp)) {
                        Text("Small steps,", fontFamily = FontFamily.Serif, fontSize = 38.sp,
                            lineHeight = 42.sp, letterSpacing = (-1.5).sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("brighter days.", fontFamily = FontFamily.Serif, fontStyle = FontStyle.Italic,
                                color = orange, fontSize = 38.sp, lineHeight = 44.sp, letterSpacing = (-1.5).sp)
                            Spacer(Modifier.width(14.dp))
                            icon("sun", orange, Modifier.size(32.dp))
                        }
                        Text("A little inspiration for a life well lived.", color = muted,
                            fontSize = 12.sp, modifier = Modifier.padding(top = 9.dp))
                    }
                    BasicTextField(
                        value = query,
                        onValueChange = { query = it },
                        singleLine = true,
                        textStyle = TextStyle(color = ink, fontSize = 13.sp),
                        cursorBrush = SolidColor(orange),
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(17.dp))
                            .background(Color(0xFFF0EDE5)).semantics { contentDescription = "Search tasks and special days" },
                        decorationBox = { field ->
                            Row(Modifier.padding(horizontal = 17.dp, vertical = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                                icon("search", muted, Modifier.size(19.dp))
                                Spacer(Modifier.width(12.dp))
                                Box(Modifier.weight(1f)) {
                                    if (query.isEmpty()) Text("Write something here", color = muted, fontSize = 13.sp)
                                    field()
                                }
                            }
                        },
                    )
                }

                item {
                    Row(Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Task Cards", fontFamily = FontFamily.Serif, fontSize = 24.sp,
                            letterSpacing = (-0.6).sp, modifier = Modifier.weight(1f).semantics { heading() })
                        TextButton(onClick = { showAllTasks = !showAllTasks }) {
                            Text(if (showAllTasks) "Show Less" else "See All", color = orange, fontSize = 12.sp)
                            Text("  ↗", color = orange, fontSize = 16.sp)
                        }
                    }
                    val matchingTasks = tasks.filterIndexed { index, task ->
                        (destination == "Home" || index == when (destination) { "Street" -> 2; "Forest" -> 1; else -> 3 }) &&
                            (task.first.contains(query, true) || task.second.contains(query, true))
                    }
                    val visibleTasks = if (showAllTasks || query.isNotBlank()) matchingTasks else matchingTasks.take(2)
                    Column(Modifier.animateContentSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        visibleTasks.chunked(2).forEach { pair ->
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                pair.forEach { task ->
                                    val index = tasks.indexOf(task)
                                    val cardColor = listOf(blue, pink, green, yellow)[index]
                                    val artColor = listOf(Color(0xFF496E8A), Color(0xFFB05A4D), Color(0xFF69805D), Color(0xFFAA8434))[index]
                                    Box(
                                        Modifier.weight(1f).aspectRatio(1f).clip(RoundedCornerShape(22.dp))
                                            .background(Brush.linearGradient(listOf(cardColor, cardColor.copy(alpha = 0.7f))))
                                            .clickable(role = Role.Button) { openedTask = task.first },
                                    ) {
                                        Canvas(Modifier.align(Alignment.TopCenter).padding(top = 6.dp).fillMaxWidth(0.6f).aspectRatio(1f)) {
                                            val center = Offset(size.width / 2, size.height * 0.48f)
                                            val radius = size.minDimension * 0.24f
                                            if (index % 2 == 0) {
                                                repeat(12) { ray ->
                                                    rotate(ray * 30f, center) {
                                                        drawRoundRect(artColor, Offset(center.x - 3.dp.toPx(), center.y - radius * 1.8f),
                                                            Size(6.dp.toPx(), radius * 0.56f), CornerRadius(3.dp.toPx()))
                                                    }
                                                }
                                                drawCircle(Color(0xFFF9EDC9), radius, center)
                                                drawCircle(artColor, radius, center, style = Stroke(1.5.dp.toPx()))
                                                drawArc(artColor, 0f, 180f, false, center - Offset(radius * 0.38f, radius * 0.12f),
                                                    Size(radius * 0.76f, radius * 0.5f), style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round))
                                                drawCircle(artColor, 1.5.dp.toPx(), center + Offset(-radius * 0.32f, -radius * 0.16f))
                                                drawCircle(artColor, 1.5.dp.toPx(), center + Offset(radius * 0.32f, -radius * 0.16f))
                                            } else {
                                                repeat(8) { petal ->
                                                    rotate(petal * 45f, center) {
                                                        drawOval(artColor, Offset(center.x - radius * 0.42f, center.y - radius * 1.75f),
                                                            Size(radius * 0.84f, radius * 1.6f))
                                                    }
                                                }
                                                drawCircle(Color(0xFFF7E9B7), radius * 0.56f, center)
                                                drawCircle(artColor, radius * 0.18f, center)
                                            }
                                        }
                                        Text("0${index + 1}", color = artColor.copy(alpha = 0.75f), fontSize = 9.sp,
                                            modifier = Modifier.padding(14.dp))
                                        if (task.first in savedTasks) {
                                            icon("check", artColor, Modifier.align(Alignment.TopEnd).padding(12.dp).size(16.dp))
                                        }
                                        Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                                            Text(task.first, fontSize = 17.sp, fontFamily = FontFamily.Serif,
                                                letterSpacing = (-0.4).sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            Text(task.second, color = ink.copy(alpha = 0.65f), fontSize = 10.sp,
                                                modifier = Modifier.padding(top = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
                                        }
                                    }
                                }
                                if (pair.size == 1) Spacer(Modifier.weight(1f))
                            }
                        }
                        if (visibleTasks.isEmpty()) Text("No task cards found. Try another search.", color = muted, fontSize = 13.sp)
                    }
                }

                item {
                    Row(Modifier.fillMaxWidth().padding(top = 18.dp, bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Special Days", fontFamily = FontFamily.Serif, fontSize = 24.sp,
                            letterSpacing = (-0.6).sp, modifier = Modifier.weight(1f).semantics { heading() })
                        TextButton(onClick = { showAllDays = !showAllDays }) {
                            Text(if (showAllDays) "Show Less" else "See All", color = orange, fontSize = 12.sp)
                            Text("  ↗", color = orange, fontSize = 16.sp)
                        }
                    }
                    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).border(1.dp, line, RoundedCornerShape(20.dp))
                        .animateContentSize().padding(horizontal = 14.dp)) {
                        val matchingDays = holidays.filter { it.third.contains(query, true) }
                        val visibleDays = if (showAllDays || query.isNotBlank()) matchingDays else matchingDays.take(2)
                        visibleDays.forEachIndexed { index, (month, day, title) ->
                            if (index > 0) Box(Modifier.fillMaxWidth().height(1.dp).background(line))
                            Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.width(43.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(month, color = orange, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
                                    Text(day, fontFamily = FontFamily.Serif, fontSize = 26.sp, lineHeight = 29.sp)
                                }
                                Spacer(Modifier.width(14.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(title, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                    Text(if (title in addedDays) "Added to your day" else if (index == 0) "A little kindness goes a long way" else "Welcome a new season",
                                        color = muted, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                                }
                                val added = title in addedDays
                                IconButton(
                                    onClick = { addedDays = if (added) addedDays - title else addedDays + title },
                                    modifier = Modifier.size(48.dp).semantics { contentDescription = if (added) "Remove $title" else "Add $title" },
                                ) {
                                    Box(Modifier.size(30.dp).background(if (added) green else Color.Transparent, CircleShape)
                                        .border(1.dp, if (added) green else line, CircleShape), contentAlignment = Alignment.Center) {
                                        icon(if (added) "check" else "plus", if (added) Color(0xFF526745) else ink, Modifier.size(17.dp))
                                    }
                                }
                            }
                        }
                        if (visibleDays.isEmpty()) Text("No special days found.", color = muted, fontSize = 13.sp, modifier = Modifier.padding(16.dp))
                    }
                }

                item {
                    Row(Modifier.fillMaxWidth().padding(top = 26.dp, bottom = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Popular Tasks", fontFamily = FontFamily.Serif, fontSize = 24.sp,
                            letterSpacing = (-0.6).sp, modifier = Modifier.weight(1f).semantics { heading() })
                        Text("WORTH A MOMENT", color = muted, fontSize = 8.sp, letterSpacing = 1.sp)
                    }
                    val visiblePopular = popular.filter { it.first.contains(query, true) }
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        visiblePopular.forEach { (title, subtitle) ->
                            val outdoors = title == popular.first().first
                            Row(
                                Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(if (outdoors) green else yellow)
                                    .clickable(role = Role.Button) { openedTask = title }.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(paper.copy(alpha = 0.55f)),
                                    contentAlignment = Alignment.Center) {
                                    icon(if (outdoors) "Forest" else "sun", if (outdoors) Color(0xFF63734E) else Color(0xFF9A7830), Modifier.size(30.dp))
                                }
                                Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                                    Text(subtitle, color = muted, fontSize = 8.sp, letterSpacing = 0.8.sp, fontWeight = FontWeight.Medium)
                                    Text(title, fontFamily = FontFamily.Serif, fontSize = 18.sp, modifier = Modifier.padding(top = 5.dp))
                                }
                                icon(if (title in savedTasks) "check" else "arrow", ink, Modifier.size(19.dp))
                            }
                        }
                        if (visiblePopular.isEmpty()) Text("No popular tasks found.", color = muted, fontSize = 13.sp)
                    }
                    Row(Modifier.fillMaxWidth().padding(top = 24.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        icon("sun", orange.copy(alpha = 0.7f), Modifier.size(13.dp))
                        Text("  A little better, every day.", color = muted, fontFamily = FontFamily.Serif,
                            fontStyle = FontStyle.Italic, fontSize = 12.sp)
                    }
                }
            }
        }

        openedTask?.let { task ->
            AlertDialog(
                onDismissRequest = { openedTask = null },
                containerColor = paper,
                shape = RoundedCornerShape(26.dp),
                title = { Text(task, fontFamily = FontFamily.Serif, fontSize = 28.sp) },
                text = { Text(when (task) {
                    "Morning rituals" -> "Let the day begin gently. Drink a glass of water, stretch, and choose one intention for today."
                    "Little joys" -> "Notice three small things that make you smile. A favorite song, warm sunlight, or a kind word is a lovely place to start."
                    "Step outside", "Take the scenic route" -> "Take fifteen minutes to wander. Pick a different path, put your phone away, and notice what is growing around you."
                    "Quiet moments" -> "Find a comfortable spot. Relax your shoulders and take five slow breaths. Give yourself permission to pause."
                    else -> "Think of three things you are grateful for today. Write them down, and let yourself linger on one."
                }, lineHeight = 23.sp) },
                confirmButton = {
                    TextButton(onClick = {
                        savedTasks = if (task in savedTasks) savedTasks - task else savedTasks + task
                        openedTask = null
                    }) { Text(if (task in savedTasks) "Remove from my day" else "Add to my day") }
                },
                dismissButton = { TextButton(onClick = { openedTask = null }) { Text("Maybe later", color = muted) } },
            )
        }
    }
}

@Preview(name = "Home · daily inspiration", showBackground = true, widthDp = 412, heightDp = 1080)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
