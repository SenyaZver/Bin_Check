package com.example.bin_check.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bin_check.data.entity.CardDataDTO
import com.example.bin_check.presentation.theme.smallSpacerSize
import com.example.bin_check.presentation.theme.standartSpacerSize
import com.example.bin_check.R
import com.example.bin_check.domain.entity.CardData

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
                text = stringResource(R.string.bin_inn_number, cardData.bin),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.bank),
                color = MaterialTheme.colors.secondary
            )


            if (cardData.bank == null) {
                Text(
                    text = stringResource(R.string.no_bank_found_message)
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

            Spacer(Modifier.height(standartSpacerSize))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column() {

                    if (cardData.scheme!= null) {
                        Text(
                            text = stringResource(R.string.scheme_network),
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.scheme,
                        )

                        Spacer(Modifier.height(standartSpacerSize))
                    }

                    if (cardData.brand!= null) {
                        Text(
                            text = stringResource(R.string.brand),
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.brand,
                        )

                        Spacer(Modifier.height(20.dp))
                    }


                    if (cardData.number!= null) {
                        Text(
                            text = stringResource(R.string.card_number),
                            color = MaterialTheme.colors.secondary
                        )

                        Spacer(Modifier.height(smallSpacerSize))

                        Row() {
                            Column() {
                                Text(
                                    text = stringResource(R.string.length),
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 12.sp
                                )

                                Text(
                                    text = cardData.number.length.toString(),
                                )
                            }

                            Spacer(Modifier.width(smallSpacerSize))

                            Column() {
                                Text(
                                    text = stringResource(R.string.luhn),
                                    color = MaterialTheme.colors.secondary,
                                    fontSize = 12.sp
                                )

                                val luhnString = if (cardData.number.luhn) { stringResource(R.string.yes) } else { stringResource(R.string.no) }

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
                            text = stringResource(R.string.type),
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = cardData.type,
                        )

                        Spacer(Modifier.height(20.dp))
                    }

                    if (cardData.prepaid!= null) {
                        Text(
                            text = stringResource(R.string.prepaid),
                            color = MaterialTheme.colors.secondary
                        )

                        val prepaidString = if (cardData.prepaid) { stringResource(R.string.yes) } else { stringResource(R.string.no) }


                        Text(
                            text = prepaidString,
                        )

                        Spacer(Modifier.height(20.dp))
                    }

                    if (cardData.country!= null) {
                        Text(
                            text = stringResource(R.string.country),
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
                                    text = stringResource(R.string.latitude_arg, cardData.country.latitude)
                                )
                                Text(
                                    text = stringResource(R.string.longtitude_arg, cardData.country.longitude)
                                )

                            }
                        }
                    }



                }
            }



        }
    }
}