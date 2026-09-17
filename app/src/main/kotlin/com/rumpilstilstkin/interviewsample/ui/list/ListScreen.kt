package com.rumpilstilstkin.interviewsample.ui.list

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen() {
    val ink = Color(0xFF20223A)
    val mutedInk = Color(0xFF77788C)
    val paper = Color(0xFFFFF9F2)
    val coral = Color(0xFFFF6B5F)
    val peach = Color(0xFFFFB38A)
    val violet = Color(0xFF7B70F4)
    val mint = Color(0xFF62CDB4)
    val lemon = Color(0xFFFFD46A)
    val cardShape = RoundedCornerShape(28.dp)
    val displayFont = FontFamily.SansSerif

    val taskCards = listOf(
        Triple("01", "Morning\nfocus", listOf(violet, Color(0xFF9C93FF))),
        Triple("02", "Move &\nbreathe", listOf(coral, peach)),
        Triple("03", "Read\n20 min", listOf(mint, Color(0xFF8CE2C5))),
    )
    val specialDays = listOf(
        Triple("SEP", "21", "World Gratitude Day"),
        Triple("SEP", "27", "World Tourism Day"),
        Triple("OCT", "01", "International Coffee Day"),
    )
    val popularTasks = listOf(
        Triple("Slow Sunday", "4 gentle rituals", listOf(Color(0xFF2F3256), Color(0xFF4E5080))),
        Triple("Tiny wins", "Build a 7-day streak", listOf(Color(0xFFEF665B), Color(0xFFFF9F72))),
        Triple("Fresh start", "Reset your space", listOf(Color(0xFF269C86), Color(0xFF65C8A8))),
    )
    val navItems = listOf("Home", "Street", "Forest", "Cave")

    var searchText by remember { mutableStateOf("") }
    val pulseTransition = rememberInfiniteTransition(label = "home pulse")
    val homePulse by pulseTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "home icon scale",
    )

    val sectionHeader: @Composable (String, Boolean) -> Unit = { title, showAction ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                color = ink,
                fontFamily = displayFont,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                letterSpacing = (-0.5).sp,
            )
            Spacer(Modifier.weight(1f))
            if (showAction) {
                Text(
                    text = "See All",
                    modifier = Modifier
                        .clickable { }
                        .background(Color.White.copy(alpha = 0.7f), CircleShape)
                        .padding(horizontal = 14.dp, vertical = 8.dp),
                    color = violet,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }
        }
    }

    val navIcon: @Composable (Int, Color, Modifier) -> Unit = { index, color, modifier ->
        Box(
            modifier = modifier
                .size(25.dp)
                .drawBehind {
                    val stroke = 2.2.dp.toPx()
                    when (index) {
                        0 -> {
                            val roof = Path().apply {
                                moveTo(size.width * .12f, size.height * .48f)
                                lineTo(size.width * .5f, size.height * .15f)
                                lineTo(size.width * .88f, size.height * .48f)
                            }
                            drawPath(roof, color, style = Stroke(stroke, cap = StrokeCap.Round))
                            drawRoundRect(
                                color = color,
                                topLeft = Offset(size.width * .23f, size.height * .43f),
                                size = Size(size.width * .54f, size.height * .43f),
                                cornerRadius = CornerRadius(4.dp.toPx()),
                                style = Stroke(stroke),
                            )
                        }
                        1 -> {
                            drawLine(color, Offset(size.width * .2f, size.height * .12f), Offset(size.width * .38f, size.height * .88f), stroke, StrokeCap.Round)
                            drawLine(color, Offset(size.width * .8f, size.height * .12f), Offset(size.width * .62f, size.height * .88f), stroke, StrokeCap.Round)
                            drawLine(color, Offset(size.width * .5f, size.height * .16f), Offset(size.width * .5f, size.height * .3f), stroke, StrokeCap.Round)
                            drawLine(color, Offset(size.width * .5f, size.height * .48f), Offset(size.width * .5f, size.height * .62f), stroke, StrokeCap.Round)
                            drawLine(color, Offset(size.width * .5f, size.height * .78f), Offset(size.width * .5f, size.height * .9f), stroke, StrokeCap.Round)
                        }
                        2 -> {
                            drawLine(color, Offset(size.width * .5f, size.height * .38f), Offset(size.width * .5f, size.height * .88f), stroke, StrokeCap.Round)
                            drawCircle(color, size.width * .25f, Offset(size.width * .5f, size.height * .32f), style = Stroke(stroke))
                            drawCircle(color, size.width * .17f, Offset(size.width * .28f, size.height * .48f), style = Stroke(stroke))
                            drawCircle(color, size.width * .17f, Offset(size.width * .72f, size.height * .48f), style = Stroke(stroke))
                        }
                        else -> {
                            val cave = Path().apply {
                                moveTo(size.width * .1f, size.height * .85f)
                                cubicTo(size.width * .12f, size.height * .3f, size.width * .32f, size.height * .12f, size.width * .5f, size.height * .12f)
                                cubicTo(size.width * .7f, size.height * .12f, size.width * .9f, size.height * .32f, size.width * .9f, size.height * .85f)
                                lineTo(size.width * .1f, size.height * .85f)
                            }
                            drawPath(cave, color, style = Stroke(stroke, cap = StrokeCap.Round))
                            drawCircle(color, size.width * .16f, Offset(size.width * .5f, size.height * .72f))
                        }
                    }
                },
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(paper),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(peach.copy(alpha = .22f), Color.Transparent),
                            center = Offset(size.width * .92f, size.height * .08f),
                            radius = size.width * .55f,
                        ),
                        radius = size.width * .55f,
                        center = Offset(size.width * .92f, size.height * .08f),
                    )
                    drawCircle(
                        color = violet.copy(alpha = .06f),
                        radius = size.width * .34f,
                        center = Offset(-size.width * .08f, size.height * .58f),
                    )
                },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 14.dp,
                end = 20.dp,
                bottom = 128.dp,
            ),
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "Home",
                            color = ink,
                            fontFamily = displayFont,
                            fontWeight = FontWeight.Black,
                            fontSize = 36.sp,
                            letterSpacing = (-1.4).sp,
                        )
                        Text(
                            text = "Make today feel lighter",
                            color = mutedInk,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                brush = Brush.linearGradient(listOf(lemon, peach)),
                                shape = RoundedCornerShape(17.dp),
                            )
                            .border(3.dp, Color.White.copy(alpha = .9f), RoundedCornerShape(17.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("A", color = ink, fontWeight = FontWeight.Black, fontSize = 16.sp)
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(11.dp)
                                .background(coral, CircleShape)
                                .border(2.dp, paper, CircleShape),
                        )
                    }
                }
                Spacer(Modifier.height(26.dp))
            }

            item {
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    cursorBrush = SolidColor(violet),
                    textStyle = TextStyle(
                        color = ink,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = .92f), RoundedCornerShape(22.dp))
                        .border(1.dp, Color.White, RoundedCornerShape(22.dp))
                        .padding(horizontal = 18.dp, vertical = 16.dp),
                    decorationBox = { innerTextField ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.weight(1f)) {
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "Write something here",
                                        color = mutedInk.copy(alpha = .75f),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                    )
                                }
                                innerTextField()
                            }
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .background(ink, CircleShape)
                                    .drawBehind {
                                        drawCircle(
                                            color = Color.White,
                                            radius = 6.5.dp.toPx(),
                                            center = Offset(size.width * .45f, size.height * .43f),
                                            style = Stroke(2.dp.toPx()),
                                        )
                                        drawLine(
                                            color = Color.White,
                                            start = Offset(size.width * .61f, size.height * .59f),
                                            end = Offset(size.width * .75f, size.height * .73f),
                                            strokeWidth = 2.dp.toPx(),
                                            cap = StrokeCap.Round,
                                        )
                                    },
                            )
                        }
                    },
                )
                Spacer(Modifier.height(30.dp))
            }

            item {
                sectionHeader("Task Cards", true)
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    taskCards.forEachIndexed { index, task ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(Brush.linearGradient(task.third), cardShape)
                                .clickable { }
                                .padding(13.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = task.first,
                                    color = Color.White.copy(alpha = .7f),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                )
                                Spacer(Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(Color.White.copy(alpha = .85f), CircleShape),
                                )
                            }
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = task.second,
                                color = Color.White,
                                fontFamily = displayFont,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = if (index == 1) 15.sp else 16.sp,
                                lineHeight = 17.sp,
                                letterSpacing = (-0.4).sp,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            item {
                sectionHeader("Special Days", true)
                Spacer(Modifier.height(8.dp))
                Column {
                    specialDays.forEachIndexed { index, day ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(54.dp)
                                    .background(
                                        color = when (index) {
                                            0 -> violet.copy(alpha = .12f)
                                            1 -> coral.copy(alpha = .12f)
                                            else -> mint.copy(alpha = .16f)
                                        },
                                        shape = RoundedCornerShape(17.dp),
                                    )
                                    .padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(day.first, color = mutedInk, fontWeight = FontWeight.Bold, fontSize = 9.sp, letterSpacing = 1.sp)
                                Text(day.second, color = ink, fontWeight = FontWeight.Black, fontSize = 18.sp)
                            }
                            Spacer(Modifier.width(14.dp))
                            Text(
                                text = day.third,
                                modifier = Modifier.weight(1f),
                                color = ink,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.White.copy(alpha = .85f), CircleShape)
                                    .border(1.dp, ink.copy(alpha = .08f), CircleShape)
                                    .clickable { },
                                contentAlignment = Alignment.Center,
                            ) {
                                Text("+", color = ink, fontWeight = FontWeight.Medium, fontSize = 22.sp)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(27.dp))
            }

            item {
                sectionHeader("Popular Tasks", false)
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    popularTasks.forEachIndexed { index, task ->
                        Column(
                            modifier = Modifier
                                .width(232.dp)
                                .height(128.dp)
                                .background(Brush.linearGradient(task.third), cardShape)
                                .clickable { }
                                .padding(18.dp),
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .background(Color.White.copy(alpha = .16f), CircleShape),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = listOf("✦", "✓", "↻")[index],
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                    )
                                }
                                Spacer(Modifier.weight(1f))
                                Text("0${index + 1}", color = Color.White.copy(alpha = .55f), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            }
                            Spacer(Modifier.weight(1f))
                            Text(task.first, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, letterSpacing = (-0.4).sp)
                            Text(task.second, color = Color.White.copy(alpha = .72f), fontWeight = FontWeight.Medium, fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            color = Color(0xFF24263E).copy(alpha = .98f),
            shape = RoundedCornerShape(28.dp),
            shadowElevation = 18.dp,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                navItems.forEachIndexed { index, label ->
                    val active = index == 0
                    Column(
                        modifier = Modifier
                            .background(
                                brush = if (active) {
                                    Brush.horizontalGradient(listOf(coral, peach))
                                } else {
                                    Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent))
                                },
                                shape = CircleShape,
                            )
                            .clickable { }
                            .padding(
                                horizontal = if (active) 15.dp else 10.dp,
                                vertical = 8.dp,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        navIcon(
                            index,
                            if (active) Color.White else Color.White.copy(alpha = .55f),
                            if (active) Modifier.scale(homePulse) else Modifier,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = label,
                            color = if (active) Color.White else Color.White.copy(alpha = .55f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun ListScreenPreview() {
    ListScreen()
}
