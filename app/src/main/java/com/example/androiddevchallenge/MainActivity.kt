/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.models.PuppiesListModel
import com.example.androiddevchallenge.ui.theme.MyTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                ScreenNavigation()
            }
        }
    }
}

@Composable
fun ScreenNavigation() {
    val paramPuppyId = "puppyId"
    val navDestinationList = "ListScreen"
    val navDestinationDetail = "DetailScreen/{$paramPuppyId}"

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = navDestinationList) {
        composable(route = navDestinationList) { MyApp(navController) }
        composable(
            route = navDestinationDetail,
            arguments = listOf(navArgument(paramPuppyId) { type = NavType.IntType })
        )
        { backStackEntry -> PuppyDetail(backStackEntry.arguments?.getInt(paramPuppyId)) }
    }
}

@Composable
fun PuppyDetail(puppyId: Int?) {

    val nameTitle = "Name"
    val heightMaleTitle = "Height Male"
    val heightFemaleTitle = "Height Female"
    val weightFemaleTitle = "Weight Female"
    val weightMaleTitle = "Weight Male"
    val lifeTitle = "Life"

    puppyId?.let {
        val puppy = DataProvider().obtainPuppyDetainWithId(puppyId, LocalContext.current)

        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {

            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 8.dp
            ) {
                Image(
                    painter = painterResource(id = puppy?.imageResource ?: 0),
                    contentDescription = puppy?.name
                )
            }

            DetailRow(title = nameTitle, value = puppy?.name ?: "")
            DetailRow(title = heightMaleTitle, value = puppy?.heightMale ?: "")
            DetailRow(title = heightFemaleTitle, value = puppy?.heightFemale ?: "")
            DetailRow(title = weightMaleTitle, value = puppy?.weightMale ?: "")
            DetailRow(title = weightFemaleTitle, value = puppy?.weightFemale ?: "")
            DetailRow(title = lifeTitle, value = puppy?.life ?: "")
        }

    }
}

@Composable
fun DetailRow(title: String, value: String) {

    Row(modifier = Modifier.padding(top = 5.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 5.dp, start = 4.dp)
        )

        Text(
            text = value,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 5.dp)
        )
    }
}

// Start building your app here!
@Composable
fun MyApp(navController: NavController) {
    Surface(color = Color.White) {
        PuppiesList(
            puppiesList = DataProvider().getPuppiesList(LocalContext.current),
            navController
        )
    }
}

@Composable
fun PuppiesList(puppiesList: List<PuppiesListModel>, navController: NavController) {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(puppiesList) { puppy ->
                PuppiesListItem(model = puppy, navController = navController)
            }
        }
    }
}

@Composable
fun PuppiesListItem(model: PuppiesListModel, navController: NavController) {
    val navDestinationDetail = "DetailScreen/${model.index}"

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 18.dp
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    enabled = true,
                    onClick = { navController.navigate(route = navDestinationDetail) })
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)

        ) {
            Image(
                painter = painterResource(id = model.resourceId),
                contentDescription = model.name
            )

            Text(
                text = model.name,
                textAlign = TextAlign.Right,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }

}

@Composable
fun LightPreview() {
    MyTheme {
        ScreenNavigation()
    }
}

@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        ScreenNavigation()
    }
}





