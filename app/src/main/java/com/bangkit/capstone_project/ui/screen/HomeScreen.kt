package com.bangkit.capstone_project.ui.screen

import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.capstone_project.R
import com.bangkit.capstone_project.network.weather.WeatherViewModel
import com.bangkit.capstone_project.ui.component.cards.OwnPlantCard
import com.bangkit.capstone_project.ui.component.cards.WeatherCards
import com.bangkit.capstone_project.ui.theme.BlackLight
import com.bangkit.capstone_project.ui.theme.CapstoneProjectTheme
import com.bangkit.capstone_project.ui.theme.GreenMed
import com.bangkit.capstone_project.ui.theme.Ivory
import com.bangkit.capstone_project.viewmodel.Injection
import com.bangkit.capstone_project.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,

    weatherViewModel: WeatherViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    currentLocation: Location?
) {

    currentLocation?.latitude?.let {lat->
        currentLocation.longitude.let { long ->
            weatherViewModel.getWeather(lat,
                long
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Ivory)
            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Friday,19 May 2023",
                    style = MaterialTheme.typography.titleMedium,
                    color = BlackLight
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Hello,", fontSize = 20.sp)
                    Text(text = "Username", fontSize = 20.sp, color = GreenMed)
                }
            }
            Icon(painter = painterResource(id = R.drawable.bell), contentDescription = null)
        }

        WeatherCards(weatherViewModel = weatherViewModel,currentLocation=currentLocation, context = LocalContext.current)
        Text(text = "Your Plant", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(bottom = 16.dp)) {

            items(5) {

                OwnPlantCard(isSick = false)
            }


        }
    }
}

@Preview
@Composable
fun HomePreview() {
    CapstoneProjectTheme {
        HomeScreen(currentLocation = null,)
    }
}