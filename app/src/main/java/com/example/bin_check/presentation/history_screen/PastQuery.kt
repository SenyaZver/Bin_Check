package com.example.bin_check.presentation.history_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bin_check.data.entity.CardData
import com.example.bin_check.presentation.main_screen.BankCard
import com.example.bin_check.R

@Composable
fun PastQuery(
    modifier: Modifier = Modifier,
    cardData: CardData,
    onCoordinatesClick: (Long, Long) -> Unit,
    onLinkClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit
) {
    val show = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.animateContentSize(
            animationSpec = tween(durationMillis = 200, easing = FastOutLinearInEasing)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.clickable {
                show.value = !show.value
            },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = cardData.bin.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                val imageModifier = if (show.value) { Modifier.rotate(180f) } else { Modifier }


                Image(
                    modifier = imageModifier.size(40.dp),
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

            }

        }

        AnimatedVisibility(
            visible = show.value,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            BankCard(
                cardData = cardData,
                onPhoneClick = onPhoneClick,
                onLinkClick = onLinkClick,
                onCoordinatesClick = onCoordinatesClick
            )
        }

    }

}