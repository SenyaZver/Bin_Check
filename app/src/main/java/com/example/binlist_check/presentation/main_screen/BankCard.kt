package com.example.binlist_check.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.binlist_check.data.entity.CardData


@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    cardData: CardData,
    onCoordinatesClick: (Long, Long) -> Unit,
    onLinkClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BIN/INN: ${cardData.bin}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "BANK",
                color = MaterialTheme.colors.secondary
            )


            if (cardData.bank == null) {
                Text(
                    text = "No such bank found. Check your input!"
                )
            } else {
                if (cardData.bank.name!= null) {
                    Text(
                        text = cardData.bank.name,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (cardData.bank.url!= null) {
                    Button(
                        onClick = {
                            onLinkClick(cardData.bank.url)
                        }
                    ) {
                        Text(
                            text = cardData.bank.url,
                        )
                    }
                }

                if (cardData.bank.phone!= null) {
                    Button(
                        onClick = {
                            onPhoneClick(cardData.bank.phone)
                        }
                    ) {
                        Text(
                            text = cardData.bank.phone,
                        )
                    }
                }



            }

            Spacer(Modifier.height(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column() {

                    if (cardData.scheme!= null) {
                        Text(
                            text = "SCHEME / NETWORK",
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.scheme,
                        )

                        Spacer(Modifier.height(20.dp))
                    }

                    if (cardData.brand!= null) {
                        Text(
                            text = "BRAND",
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.brand,
                        )

                        Spacer(Modifier.height(20.dp))
                    }


                    if (cardData.number!= null) {
                        Text(
                            text = "CARD NUMBER",
                            color = MaterialTheme.colors.secondary
                        )
                        Row() {
                            Column() {
                                Text(
                                    text = "LENGTH",
                                    color = MaterialTheme.colors.secondary
                                )

                                Text(
                                    text = cardData.number.length.toString(),
                                )
                            }

                            Spacer(Modifier.width(10.dp))

                            Column() {
                                Text(
                                    text = "LUHN",
                                    color = MaterialTheme.colors.secondary
                                )

                                val luhnString = if (cardData.number.luhn) { "Yes" } else { "No"}

                                Text(
                                    text = luhnString,
                                )
                            }
                        }
                    }

                }


                Column() {
                    if (cardData.type!= null) {
                        Text(
                            text = "TYPE",
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.type,
                        )

                        Spacer(Modifier.height(20.dp))
                    }

                    if (cardData.prepaid!= null) {
                        Text(
                            text = "PREPAID",
                            color = MaterialTheme.colors.secondary
                        )

                        val prepaidString = if (cardData.prepaid) { "Yes" } else { "No"}


                        Text(
                            text = prepaidString,
                        )

                        Spacer(Modifier.height(20.dp))
                    }

                    if (cardData.country!= null) {
                        Text(
                            text = "COUNTRY",
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.country.name,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = {
                                onCoordinatesClick(cardData.country.latitude, cardData.country.longitude)
                            }
                        ) {
                            Column() {
                                Text(
                                    text = "Latitude: ${cardData.country.latitude}",
                                )
                                Text(
                                    text = "Longtitude: ${cardData.country.longitude}",
                                )
                            }
                        }
                    }



                }
            }



        }
    }
}