package com.example.binlist_check.presentation.history_screen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.binlist_check.common.Utils.getGeoUri
import com.example.binlist_check.common.Utils.getLinkUri
import com.example.binlist_check.common.Utils.getTelephoneUriUri
import com.example.binlist_check.presentation.main_screen.BankCard
import com.example.binlist_check.R

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    val activityNotFoundExceptionMessage = stringResource(R.string.activity_not_found_exception_message)

    if (state.value.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                text = stringResource(R.string.queries_history),
                fontSize = 20.sp
            )
        }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.clearHistory()
            }
        ) {
            Text(
                text = stringResource(R.string.clear),
                fontSize = 24.sp
            )
        }

        Spacer(Modifier.height(20.dp))



        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(state.value.queriesList.size) { index ->

                BankCard(
                    cardData = state.value.queriesList[index],
                    onCoordinatesClick = {latitide, longtitude ->
                        val geoUri = getGeoUri(latitide, longtitude)
                        val intent = Intent(Intent.ACTION_VIEW, geoUri)

                        try {
                            startActivity(context, intent, null)
                        } catch (e: ActivityNotFoundException) {

                            Toast.makeText(context, activityNotFoundExceptionMessage, Toast.LENGTH_SHORT).show()
                        }

                    },
                    onLinkClick = { link ->
                        val linkUri = getLinkUri(link)

                        val intent = Intent(Intent.ACTION_VIEW, linkUri)
                        try {
                            startActivity(context, intent, null)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, activityNotFoundExceptionMessage, Toast.LENGTH_SHORT).show()
                        }


                    },
                    onPhoneClick = { phoneNumber ->
                        val telephoneUri = getTelephoneUriUri(phoneNumber)

                        val intent = Intent(Intent.ACTION_DIAL, telephoneUri);
                        try {
                            startActivity(context, intent, null)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, activityNotFoundExceptionMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                )

                Spacer(Modifier.height(20.dp))
            }

        }




    }

}